package com.example.dramirez.garrraspuertoserie;

import android.util.Log;

import com.example.dramirez.garrraspuertoserie.Interfaces.DriverCelda;

import java.io.IOException;
import java.io.PrintStream;

class Balanza {

    private int pesoEstabilidad = 0,tiempoEstabilidad = 2000;
    Variables variables;
    private int cuentas;
    private int PesoAcumulado =0;
    private DriverCelda celda = null;
    private static final Balanza ourInstance = new Balanza();
    private  static  float CONSTANTE = 0.000001f;
    private static float VxBITS = 0.000000014901218f;
    private static int CAPACIDAD=40000;
    private static int SENSIBILIDAD=2000;
    private static int TOTALCELDAS=80000;
    private static int CERO=0;
    private static int DIVISION=20;
    private  static  float MULTIPLICADOR;
    private  static  float CORRECCION = 0;
    private double pesoConCero;
    private double peso;
    private double correccion = 0;
    private int corregido = 0, redondeo, arriba, abajo, A, B;
    private boolean cominzoPesaje, flagPeso;
    private int acumularPeso;
    private boolean semiAutomatico = true;
    private int ventanaMovilActual,indiceVentana;
    private double pesoFiltrado, pesoFisicoAnterior;
    private double[] vectorFiltro = new double[100];



    int contador = 0;
    private boolean estable = false, fueAcumulado = false;

    static Balanza getInstance()
    {
        return ourInstance;
    }

    private Balanza() {
    }

    public boolean isFlagPeso() {
        return flagPeso;
    }

    public boolean isFueAcumulado() {
        return fueAcumulado;
    }

    public void setPesoAcumulado (){
        PesoAcumulado = 0;
    }
    public void setFlagPeso(boolean flagPeso) {
        this.flagPeso = flagPeso;
    }

    public int getCuentas() {
        return cuentas;
    }

    public boolean isEstable() {
        return estable;
    }

    public void setDriverCelda(DriverCelda driverCelda, Variables var, Principal pantallaPrincipal)
    {

        variables = var;
        celda = driverCelda;
        MULTIPLICADOR = obtenerMultiplicador();
    }

    public Variables getVariables() {
        return variables;
    }
    /**
     * isComienzoPesaje = true
     * Se activan de la pantalla cuando se cargan los datos de pesaje y presiona aceptar
     * @return
     */
    public boolean isCominzoPesaje() {
        return cominzoPesaje;
    }

    public void setCominzoPesaje(boolean cominzoPesaje)
    {
        this.cominzoPesaje = cominzoPesaje;

    }

    public int getPesoAcumulado() {
        return PesoAcumulado;
    }

    public int setAcumularPeso(){

        if (semiAutomatico)
        {
            if (isCominzoPesaje() && isFlagPeso() && (corregido > 50))
            {
                PesoAcumulado += (int)waitForPesoEstable();
                fueAcumulado = true;
                flagPeso = false;
            }
        }
        else
        {
            if (isCominzoPesaje() && isEstable() && isFlagPeso() && (corregido > 50))
            {
                PesoAcumulado += corregido;
                fueAcumulado = true;
                flagPeso = false;
            }
        }
        return PesoAcumulado;
    }

    private void buscarFlagPeso(int peso) {
        if (isCominzoPesaje()){
            if (!isFueAcumulado() && (peso < 50)) {
                flagPeso = true;
            }else if (isFueAcumulado() && (peso < 50) && !flagPeso){
                flagPeso = true;
            }
        }
    }
    /**
     * Se llama cuando la balanza manda un 1
     */
    public void guardarPeso() {

        setAcumularPeso();
    }

    /**
     * Obtiene el peso fisico, segun la calibracion guardada
     * @return
     */
    public double getPesoFisico()
    {
        double cero = CERO * MULTIPLICADOR;
        peso = pesoFiltrado * MULTIPLICADOR;
        pesoConCero = peso - cero;
        //Log.d("CERO","************** " + getCERO());
        correccion = CORRECCION * (pesoConCero / 100);
        corregido = (int)pesoConCero + (int) correccion;
        redondeo = corregido / DIVISION;
        A = redondeo * DIVISION;
        B = (redondeo + 1) * DIVISION;
        abajo = corregido - A;
        arriba = B - corregido;
        if (abajo < arriba) {
            corregido = A;
        } else if (arriba < abajo) {
            corregido = B;
        } else {
            corregido = redondeo * DIVISION;
        }
        buscarEstabilidad(corregido);
        buscarFlagPeso(corregido);
        return corregido ;
    }

    public double convertToKg(double cuentas)
    {
        double pesoFisico = 0;

        double cero = CERO * MULTIPLICADOR;
        double peso = cuentas * MULTIPLICADOR;
        double pesoConCero = peso - cero;
        //Log.d("CERO","************** " + getCERO());
        double correccion = CORRECCION * (pesoConCero / 100);
        int corregido = (int)pesoConCero + (int) correccion;

        int redondeo = corregido / DIVISION;
        redondeo = corregido / DIVISION;
        int A = redondeo * DIVISION;
        int B = (redondeo + 1) * DIVISION;
        int abajo = corregido - A;
        int arriba = B - corregido;
        if (abajo < arriba) {
            corregido = A;
        } else if (arriba < abajo) {
            corregido = B;
        } else {
            corregido = redondeo * DIVISION;
        }


        return corregido;
    }

    public void filtroVentanaMovil(double cuentas)
    {
        double  salidaFiltro = 0;
        ventanaMovilActual ++;
        if (ventanaMovilActual > variables.getVENTANA())
        {
            ventanaMovilActual = variables.getVENTANA();
        }
        if (variables.getKGFILTRO() > 0)
        {
            salidaFiltro = Math.abs(cuentas - pesoFiltrado);
            if (convertToKg(salidaFiltro) > variables.getKGFILTRO())
            {
                ventanaMovilActual = 1;
                indiceVentana = 0;
            }
            pesoFisicoAnterior = cuentas;
        }
        vectorFiltro[indiceVentana] = cuentas;
        indiceVentana++;
        if (indiceVentana >= variables.getVENTANA())
        {
            indiceVentana = 0;
        }
        salidaFiltro = 0;
        for (int i = 0; i < ventanaMovilActual; i++)
        {
            salidaFiltro += vectorFiltro[i];
        }
        salidaFiltro /= ventanaMovilActual;


        pesoFiltrado = salidaFiltro;
    }

    public ESTADO_PEDIDO getEstadoPedido()
    {
       return celda.getEstadoPedido();
    }
    private float obtenerMultiplicador()
    {
        float multiplicador=0;

        multiplicador = (TOTALCELDAS * VxBITS)/(SENSIBILIDAD* CONSTANTE);

        return multiplicador;

      //  celda.getEstadoPedido();
    }

    public int getOK()
    {
        return celda.getOK();
    }
    public void paraPedido()
    {
        celda.pararPedido();
    }

    public String mensajeBalanza()
    {
        return celda.getMensajeBalanza();
    }
    public void pedirCuentas()
    {
        celda.pedirCuentas();
    }

    public void EnviarCalibracion(String envio) throws IOException
    {
        celda.setCalibracion(envio);
    }
    public void Loop()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (celda != null)
                {
                    while (true)
                    {
                        try
                        {
                            celda.getDato();
                            cuentas = celda.getCuentas();
                            filtroVentanaMovil(cuentas);

                            /**
                             * Se llama cuando el gruero presiona el jostick
                             */

                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

    public void  LoopAcumulador()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        if (celda.getGuardado() == 1){
                            guardarPeso();
                        }
                        Thread.sleep(100);
                    }catch (Exception e)
                    {

                    }
                }
            }
        }).start();
    }


    private double waitForPesoEstable()
    {
        double[] peso = new double[20];
        double pesoAux;
        boolean flag;
        double pesoFinal;

        if (isEstable())
        {
            return getPesoFisico();
        }
        else
        {
            for (int i = 0; i < 20; i++)
            {
                peso[i] = getPesoFisico();
                if (isEstable())
                {
                    return peso[i];
                }
                try {
                Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = true;
            while (flag)
            {
                flag = false;
                for (int i = 0; i < (20-1); i++)
                {
                    if (peso[i] > peso[i+1])
                    {
                        pesoAux = peso[i];
                        peso[i] = peso[i+1];
                        peso[i+1] = pesoAux;
                        flag = true;
                    }
                }
            }
            pesoFinal = 0;
            for (int i = 4; i < 16; i++)
            {
                pesoFinal += peso[i];
            }
            return (pesoFinal/12);
        }
    }


    public boolean buscarEstabilidad(int PesoFisico){
        estable = false;

        if (PesoFisico != pesoEstabilidad){
            contador = 0;

        }else if (contador > (tiempoEstabilidad/100)){
            estable = true;
        }else{
            contador ++;
            estable = false;
        }
        pesoEstabilidad = PesoFisico;

        return estable;
    }

    public  int getCERO() {
        return CERO;
    }

    public void setCERO(int cero) {

        CERO = cero;
    }
    public void setearCero(){
        CERO = cuentas;
    }

}
