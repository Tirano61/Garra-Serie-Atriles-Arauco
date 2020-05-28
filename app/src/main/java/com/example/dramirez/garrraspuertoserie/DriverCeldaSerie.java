package com.example.dramirez.garrraspuertoserie;


import android.content.IntentFilter;
import android.util.Log;

import com.example.dramirez.garrraspuertoserie.Interfaces.DriverCelda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.dramirez.garrraspuertoserie.DriverCeldaSerie.TIPO_OPERACION.*;


public class DriverCeldaSerie implements DriverCelda {

    public enum TIPO_OPERACION {
        PARAM, ADC, PRINT, OK
        }
    /*public enum ESTADO_PEDIDO{
        PEDIDO_PARAM, PEDIDO_ADC, PEDIDO_PRINT, PEDIDO_CELDA
    }*/




    TIPO_OPERACION miOperacion = OK;

    int ok = -1;
    protected String mensajeBalanza;
    protected int cuentas, guardado, bateria;
    protected managerPort mSerialPort;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private String version ="";

    byte[] BufferTotal = new byte[64];
    int indexrx = 0;
    int indexTotal = 0;
    boolean datoRecibido = false;
    boolean dato = false, recibido = false;
    int contadorDatoRecibido = 0;

    ESTADO_PEDIDO estado_pedido;

    //Crear constructor que inicialice todos lo necesario


    public DriverCeldaSerie(managerPort port)
    {
        SetPort(port);
    }


    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int getDato()
    {
        try
        {
            byte[] Bufferrx = new byte[64];

            byte[] buffer = new byte[64];

            int size;
            boolean mayor = false;


            size = mInputStream.read(buffer);
            contadorDatoRecibido = 0;
            datoRecibido = true;
            dato = true;
           // Log.d("TEST", "!!!!!!!!!!!! " + size);


            for (int i = 0; (i < size) && (i < 64); i++)
            {
                if (indexTotal < 64)
                {
                    BufferTotal[indexTotal] = buffer[i];
                    if (BufferTotal[indexTotal] == 10)
                    {
                        for (int e = 0; e <= indexTotal; e++)
                        {
                            Bufferrx[e] = BufferTotal[e];
                            indexrx = indexTotal;
                            if (size > indexTotal -1)
                            {
                                mayor = true;
                            }
                        }
                        indexTotal = 0;
                    }
                    else
                    {
                        indexTotal ++;
                    }
                }
                else
                {
                    indexTotal = 0;
                }
            }
        /**
         * Ingresa si la cadena se completo con el salto de linea
         */
            if (Bufferrx[indexrx] == 10)
            {
            /**
             * Para comprobar que la cadena no se sea mas de un paquete entero
             */
                if (!mayor)
                {
                    indexTotal = 0;
                }

                String[] Recibido = new String[indexrx];

                String str1 = new String(Bufferrx);
                //Recibido = str1.split("\r");
                str1 = str1.substring(0, indexrx-1);
                indexrx = 0;

                String cadena = str1;

                if (cadena.equals("OK"))
                {
                     ok = 1;
                    mensajeBalanza = "Envio de cuentas detenido";

                }else if (cadena.equals("FAIL")){
                    ok = 0;
                    mensajeBalanza = "El pedido de ADC a fallado";
                }
                else
                {
                    cadena = str1;
                    String recortado[] = new String[3];
                    recortado = cadena.split("=");
                    switch (recortado[0]){
                        case "AT+ADC":
                                miOperacion = ADC;
                                recortado = recortado[1].split(",");
                                cuentas = Integer.valueOf(recortado[0]);
                                guardado = Integer.valueOf(recortado[1]);
                                bateria = Integer.valueOf(recortado[2]);

                            break;
                        case "AT+PARAM":
                                miOperacion = PARAM;

                            break;
                        case "AT+PRINT":
                                miOperacion = PRINT;

                            break;
                        case "AT+INFO":
                                setVersion(recortado[1]);
                            break;
                    }


                    //recortado = recortado[1].split(",");

                    return 0;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getBateria() {
        return bateria;
    }

    public void ComprobacionDatoRecibido()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {

                        Thread.sleep(3000);
                        comprobarDato();
                    }catch (Exception e){

                    }
                }
            }
        }).start();
    }
    public  void comprobarDato()
    {
        if (dato)
        {
            recibido = true;
            dato = false;
        }else {
            recibido = false;
        }
    }
    public void TimerDatoRecibido()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {

                        Thread.sleep(100);
                        doWork();
                    }catch (Exception e){

                    }
                }
            }
        }).start();

    }

    public  void doWork()
    {
        if (datoRecibido){
            if (contadorDatoRecibido > 3)
            {
                datoRecibido = false;
                contadorDatoRecibido = 0;
            }
            contadorDatoRecibido ++;
        }else{
            indexTotal = 0;
        }
    }

    @Override
    public String getMensajeBalanza() {
        return mensajeBalanza ;
    }

    @Override
    public int getOK()
    {
        int i = 0;
        while (i <= 250)
        {
            if (ok == 1)
            {
                return ok;
            }else{
                i++;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return ok;
    }

    @Override
    public int getOKUSB() {
        int i = 0;
        while (i <= 2000)
        {
            if (ok == 1)
            {
                return ok;
            }
            else if(ok == 0)
            {
                return ok;
            }
            else{
                i++;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return ok;
    }

    @Override
    public boolean getConexionSerie() {
        return recibido;
    }

    @Override
    public ESTADO_PEDIDO getEstadoPedido() {
        return estado_pedido;
    }

    @Override
    public int getCuentas() {

        return cuentas;
    }

    @Override
    public int getGuardado() {
        return guardado;
    }

    @Override
    public void setCalibracion(String envio)
    {
        try
        {
            /**
             * Se vuelve ok = -1 para verificar que la placa responda y sea = 1
             */
            ok = -1;
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);


        }catch (Exception e){

        }
    }

    @Override
    public void setTipoCelda(String envio) throws IOException {
        try {
            ok = -1;
            byte[]bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void pararPedido()
    {
        try
        {
            /**
             * Se vuelve ok = -1 para verificar que la placa responda y sea = 1
             */
            ok = -1;
            String envio = "AT+ADC=1\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);

        }catch (Exception e){

        }
    }

    @Override
    public void getInfo()
    {
        try
        {
            /**
             * Se vuelve ok = -1 para verificar que la placa responda y sea = 1
             */
            ok = -1;
            String envio = "AT+INFO\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void pedirCuentas()
    {
        try
        {
            /**
             * Se vuelve ok = -1 para verificar que la placa responda y sea = 1
             */
            ok = -1;
            String envio = "AT+ADC=0\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void setImprimir(String linea)
    {
        try
        {
            ok = -1;
            String envio = "AT+PRINT=" + linea + "\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void setUSBMounted() {
        try{
            ok = -1;
            String envio = "AT+USB_MONTED" + "\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void setUSB(String linea) {
        try{
            ok = -1;
            String envio = "AT+USB_WRITE=" + linea +  "\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void setUSB_Open(String nombreArchivo) {
        try{
            ok = -1;
            String envio = "AT+USB_OPEN=" + "\"" + nombreArchivo +"\"" + "\r\n" ;
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void setUSB_Close() {
        try{
            ok = -1;
            String envio = "AT+USB_CLOSE"+ "\r\n";
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    public void SetPort(managerPort port)
    {
        mSerialPort = port;
        mOutputStream = mSerialPort.getmFileOutputStream();
        mInputStream = mSerialPort.getmFileInputStream();
        /**
         * Inicializa el timer para comprobar que se reciben datos
         */
        TimerDatoRecibido();
        ComprobacionDatoRecibido();

    }
}
