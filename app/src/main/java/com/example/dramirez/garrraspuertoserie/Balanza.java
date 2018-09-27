package com.example.dramirez.garrraspuertoserie;

import android.print.PrinterId;
import android.util.Log;

import java.io.IOException;

class Balanza {

    Variables variables;
    private int cuentas;
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
    private float pesoConCero;
    private float peso;
    private float correccion = 0;
    private int corregido = 0, redondeo, arriba, abajo, A, B;


    static Balanza getInstance()
    {
        return ourInstance;
    }

    private Balanza() {
    }

    public int getCuentas() {
        return cuentas;
    }

    public void setDriverCelda(DriverCelda driverCelda, Variables var)
    {
        variables = var;
        celda = driverCelda;
        MULTIPLICADOR = obtenerMultiplicador();
    }

    public double getPesoFisico()
    {
        peso = cuentas * MULTIPLICADOR;
        pesoConCero = peso - getCERO();
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
        return corregido ;
    }

    private float obtenerMultiplicador()
    {
        float multiplicador=0;

        multiplicador = (TOTALCELDAS * VxBITS)/(SENSIBILIDAD* CONSTANTE);

        return multiplicador;
    }


    public void paraPedido()
    {
        celda.pararPedido();
    }

    public void pedirCuentas()
    {
        celda.pedirCuentas();
    }

    public void EnviarCalibracion(String envio) throws IOException
    {
        celda.setCalibracion(envio);
        Log.d("TAG","EN LA BALANZA ENVIARCALIBRACION");
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
                            cuentas = celda.getDato();

                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

    public static int getCAPACIDAD() {
        return CAPACIDAD;
    }

    public static void setCAPACIDAD(int CAPACIDAD) {
        Balanza.CAPACIDAD = CAPACIDAD;
    }

    public static int getSENSIBILIDAD() {
        return SENSIBILIDAD;
    }

    public static void setSENSIBILIDAD(int SENSIBILIDAD) {
        Balanza.SENSIBILIDAD = SENSIBILIDAD;
    }

    public static int getTOTALCELDAS() {
        return TOTALCELDAS;
    }

    public static void setTOTALCELDAS(int TOTALCELDAS) {
        Balanza.TOTALCELDAS = TOTALCELDAS;
    }

    public static int getCERO() {
        return CERO;
    }

    public void setCERO(int cero) {

        Balanza.CERO = cero;
    }
    public void setearCero(){
        Balanza.CERO = cuentas;
    }
    public static int getDIVISION() {
        return DIVISION;
    }

    public static void setDIVISION(int DIVISION) {
        Balanza.DIVISION = DIVISION;
    }

    public static float getCORRECCION() {

        return CORRECCION;
    }

    public static void setCORRECCION(float CORRECCION) {
        Balanza.CORRECCION = CORRECCION;
    }
}
