package com.example.dramirez.garrraspuertoserie;

import android.util.Log;

import com.example.dramirez.garrraspuertoserie.Interfaces.DriverCelda;

import java.io.IOException;
import java.io.PrintStream;
import java.net.PortUnreachableException;

class Balanza {

    private int pesoEstabilidad = 0,tiempoEstabilidad = 2000;
    Variables variables;
    private int cuentas, bateria;
    private int PesoAcumulado =0;
    private int PesoAcumuladoBancos =0;
    private int CantidadGarradas = 0;



    private int PesoAcumuladoCasis = 0;
    private DriverCelda celda = null;
    private static final Balanza ourInstance = new Balanza();
    private  static  float CONSTANTE = 0.000001f;
    private static float VxBITS = 0.000000014901218f;
   // private static int CAPACIDAD=40000;
   // private static int SENSIBILIDAD=2000;
   // private static int TOTALCELDAS=80000;
    private static int CERO=0;
   // private static int DIVISION=20;
    private  static  float MULTIPLICADOR;
    private  static  float CORRECCION = 0;
    private double pesoConCero;
    private double peso;
    private double correccion = 0;
    private int corregido = 0, redondeo, arriba, abajo, A, B;
    private boolean cominzoPesaje, flagPeso, conexionSerie;
    private int acumularPeso;
    private boolean semiAutomatico = true;
    private int ventanaMovilActual,indiceVentana;
    private double pesoFiltrado, pesoFisicoAnterior;
    private double[] vectorFiltro = new double[100];
    int tara;
    boolean guardado;




    int contador = 0;


    private boolean estable = false, fueAcumulado = false;

    static Balanza getInstance()
    {
        return ourInstance;
    }

    private Balanza() {
    }

    public int getCantidadGarradas() {
        return CantidadGarradas;
    }

    public void setCantidadGarradas(int cantidadGarradas) {
        CantidadGarradas = cantidadGarradas;
    }

    public boolean isSemiAutomatico() {
        boolean valor;
        if (Variables.getSEMIAUT().equals("1"))
        {
            valor = true;
        }else{
            valor = false;
        }

        return valor;
    }

    public void setSemiAutomatico(boolean semiAutomatico) {
        this.semiAutomatico = semiAutomatico;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public int getBateria() {
        return celda.getBateria();
    }

    public  float getCORRECCION() {
        return CORRECCION;
    }

    public  void setCORRECCION(float CORRECCION) {
        Balanza.CORRECCION = CORRECCION;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean isConexionSerie() {
        return  celda.getConexionSerie();
    }

    public boolean isFlagPeso() {
        return flagPeso;
    }

    public boolean isFueAcumulado() {
        return fueAcumulado;
    }

    public void setPesoAcumulado (int tara){
        PesoAcumulado = tara;
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


    public  void setMULTIPLICADOR() {
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

    public int getPesoAcumulado()
    {
        return PesoAcumulado;
    }
    public int getPesoAcumuladoBancos(){
        return PesoAcumuladoBancos;
    }
    public void setPesoAcumuladoBancos(int peso){
        PesoAcumuladoBancos = peso;
    }
    public int getPesoAcumuladoCasis() {
        return PesoAcumuladoCasis;
    }

    public void setPesoAcumuladoCasis(int pesoAcumuladoCasis) {
        PesoAcumuladoCasis = pesoAcumuladoCasis;
    }

    public int setAcumularPeso()
    {
        int valor = 0;
        //Log.d("ENVIO Acumular = ",String.valueOf(celda.getGuardado()));
        if (isSemiAutomatico())
        {
            if (isCominzoPesaje() && isFlagPeso() && (corregido > 50))
            {
                if (Variables.isRESTAR())
                {
                    valor = (int)waitForPesoEstable();
                    PesoAcumuladoBancos -= valor;
                    PesoAcumulado -=  valor;
                    PesoAcumuladoCasis -=  valor;
                    Variables.setRESTAR(false);
                }else{
                    //Log.d("ENVIO Semiauto = ",String.valueOf(celda.getGuardado()));
                    valor = (int)waitForPesoEstable();
                    PesoAcumuladoBancos += valor;
                    PesoAcumulado += valor;
                    PesoAcumuladoCasis += valor;
                    CantidadGarradas ++;
                }
                fueAcumulado = true;
                flagPeso = false;
                guardado = true;
            }
        }
        else
        {
            if (isCominzoPesaje() && isEstable() && isFlagPeso() && (corregido > 50))
            {
                if (Variables.isRESTAR())
                {
                    valor = (int)waitForPesoEstable();
                    PesoAcumuladoBancos -= valor;
                    PesoAcumulado -= valor;
                    PesoAcumuladoCasis -= valor;
                    Variables.setRESTAR(false);
                }else{
                    //Log.d("ENVIO entro = ",String.valueOf(celda.getGuardado()));
                    valor = (int)waitForPesoEstable();
                    PesoAcumuladoBancos += valor;
                    PesoAcumulado += valor;
                    PesoAcumuladoCasis += valor;
                    CantidadGarradas ++;
                }
                fueAcumulado = true;
                flagPeso = false;
                guardado = true;
            }
        }

        return PesoAcumulado;
    }

    private void buscarFlagPeso(int peso)
    {
        if (isCominzoPesaje())
        {
            if (!isFueAcumulado() && (peso < 50))
            {
                flagPeso = true;
            }else if (isFueAcumulado() && (peso < 50) && !flagPeso)
            {
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
        redondeo = corregido / Integer.valueOf(Variables.getDIVISION());
        A = redondeo * Integer.valueOf(Variables.getDIVISION());
        B = (redondeo + 1) * Integer.valueOf(Variables.getDIVISION());
        abajo = corregido - A;
        arriba = B - corregido;
        if (abajo < arriba) {
            corregido = A;
        } else if (arriba < abajo) {
            corregido = B;
        } else {
            corregido = redondeo * Integer.valueOf(Variables.getDIVISION());
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

        int redondeo = corregido / Integer.valueOf(Variables.getDIVISION());
        redondeo = corregido / Integer.valueOf(Variables.getDIVISION());
        int A = redondeo * Integer.valueOf(Variables.getDIVISION());
        int B = (redondeo + 1) * Integer.valueOf(Variables.getDIVISION());
        int abajo = corregido - A;
        int arriba = B - corregido;
        if (abajo < arriba) {
            corregido = A;
        } else if (arriba < abajo) {
            corregido = B;
        } else {
            corregido = redondeo * Integer.valueOf(Variables.getDIVISION());
        }


        return corregido;
    }

    public void filtroVentanaMovil(double cuentas)
    {
        double  salidaFiltro = 0;
        ventanaMovilActual ++;
        if (ventanaMovilActual > Variables.getVENTANA())
        {
            ventanaMovilActual = Variables.getVENTANA();
        }
        if (Variables.getKGFILTRO() > 0)
        {
            salidaFiltro = Math.abs(cuentas - pesoFiltrado);
            if (convertToKg(salidaFiltro) > Variables.getKGFILTRO())
            {
                ventanaMovilActual = 1;
                indiceVentana = 0;
            }
            pesoFisicoAnterior = cuentas;
        }
        vectorFiltro[indiceVentana] = cuentas;
        indiceVentana++;
        if (indiceVentana >= Variables.getVENTANA())
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

        multiplicador = (Integer.valueOf(Variables.getCELDAS()) * VxBITS)/(Integer.valueOf(Variables.getSENSIBILIDAD())* CONSTANTE);

        return multiplicador;

      //  celda.getEstadoPedido();
    }

    public String getVersion()
    {
        return celda.getVersion();
    }

    public void getInfo(){
        celda.getInfo();
    }
    public int getOK()
    {
        return celda.getOK();
    }
    public void paraPedido()
    {
        celda.pararPedido();
    }
    public void ImprimirTicket(String linea)
    {
        celda.setImprimir(linea);
    }
    public  void USBWrite(String linea){
        celda.setUSB(linea);
    }

    public void USBMounted()
    {
        celda.setUSBMounted();
    }
    public  void USBOpen(String nombreArchivo)
    {
        celda.setUSB_Open(nombreArchivo);
    }
    public  void USBClose(){
        celda.setUSB_Close();
    }
    public void pedirCuentas()
    {
        celda.pedirCuentas();
    }

    public void EnviarCalibracion(String envio) throws IOException
    {
        celda.setCalibracion(envio);
    }

    public void EnviarTipoCelda(String envio) throws IOException {
        celda.setTipoCelda(envio);
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
                            bateria = celda.getBateria();

                            /**
                             * Se llama cuando el gruero presiona el jostick
                             */

                            Thread.sleep(10);
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
                        if (celda.getGuardado() == 1)
                        {
                            guardarPeso();
                            Log.d("ENVIO getGuardado = ",String.valueOf(celda.getGuardado()));
                        }
                        Thread.sleep(100);
                    }catch (Exception e)
                    {

                    }
                }
            }
        }).start();
    }


    public double waitForPesoEstable()
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
            int devolver;
            int pesada = ((int) (pesoFinal/12));
            int redondeo =pesada/ Integer.valueOf(Variables.getDIVISION());
            int A = redondeo * Integer.valueOf(Variables.getDIVISION());
            int B = (redondeo + 1) * Integer.valueOf(Variables.getDIVISION());
            int Arriba = redondeo - A ;
            int Abajo = B - redondeo;
            if (Abajo < Arriba) {
                devolver = A;
            } else if (Arriba < Abajo) {
                devolver = B;
            } else {
                devolver = pesada;
            }

           // Log.d("PESOINESTABLE =","********** *******  " + devolver);
            return devolver;

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

    public  int getCERO()
    {
        return CERO;
    }

    public void setCERO(int cero)
    {

        CERO = cero;
    }
    public void setearCero(){
        CERO = cuentas;
    }

}
