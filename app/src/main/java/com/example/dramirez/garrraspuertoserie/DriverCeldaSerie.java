package com.example.dramirez.garrraspuertoserie;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.dramirez.garrraspuertoserie.DriverCeldaSerie.TIPO_OPERACION.*;

public class DriverCeldaSerie implements DriverCelda {

    public enum TIPO_OPERACION {
        CALIBRACION, ADC, CELDA, PRINT, VACIO
        }


    String DATO;
    TIPO_OPERACION miOperacion = VACIO;

    protected boolean CALIBRACION = false;
    protected boolean CELDA = false;
    protected boolean ADC = false;

    protected managerPort mSerialPort;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    //Crear constructor que inicialice todos lo necesario


    public DriverCeldaSerie(managerPort port)
    {
        SetPort(port);
    }

    @Override
    public int getDato()
    {
        try
        {
            int size;
            int indexrx = 0;
            int indexTotal = 0;
            byte[] Bufferrx = new byte[64];
            byte[] BufferTotal = new byte[64];
            byte[] buffer = new byte[64];
            size = mInputStream.read(buffer);

            for (int i = 0; (i < size) && (i < 64); i++)
            {
                if (buffer[i] != 0)
                {
                    BufferTotal[indexTotal] = buffer[i];
                    if (BufferTotal[indexTotal] == 10)
                    {
                        for (int j = 0; j <= indexTotal; j++)
                        {
                            Bufferrx[j] = BufferTotal[j];
                        }
                        indexrx = indexTotal;
                        indexTotal = 0;
                    }
                    else
                    {
                        indexTotal++;
                    }
                }
            }
            if (Bufferrx[indexrx] == 10)
            {
                indexrx++;
                String str1 = new String(Bufferrx);
                str1 = str1.substring(0, indexrx);
                indexrx = 0;
                String cadena = str1;
            }
        if (size > 0)
        {

            // Todo: Timer para esperar 5 segundos la llegada de un dato
            switch (miOperacion)
            {
                case PRINT:

                    break;
                case ADC:

                    break;
            }
            String parceado = null;
            int i;
            final String msg = new String(buffer, 0, size);

            parceado = msg.substring(7, msg.length()-2);
            String recortado[] = new String[2];
            recortado = parceado.split(",");
            if (!recortado[0].equals(""))
            {
                //Mostrar salida en consola!
                return Integer.valueOf(recortado[0]);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    return 0;
}

    @Override
    public void setCalibracion(String envio)
    {
        try
        {
            byte[] bites = envio.getBytes();
            mOutputStream.write(bites);
        }catch (Exception e){

        }
    }

    @Override
    public void pararPedido()
    {
        try
        {
            String envio = "AT+ADC=1";
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
            String envio = "AT+ADC=0";
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

    }
}
