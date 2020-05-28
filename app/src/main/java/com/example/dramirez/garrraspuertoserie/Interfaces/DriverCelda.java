package com.example.dramirez.garrraspuertoserie.Interfaces;

import com.example.dramirez.garrraspuertoserie.DriverCeldaSerie;
import com.example.dramirez.garrraspuertoserie.ESTADO_PEDIDO;

import java.io.IOException;

public interface DriverCelda {

    int getDato();
    int getCuentas();
    void setCalibracion(String envio) throws IOException;
    void setTipoCelda(String envio) throws IOException;
    void pararPedido();
    void pedirCuentas();
    int getGuardado();
    int getBateria();
    void setImprimir(String linea);
    void setUSB(String linea);
    void setUSB_Open(String nombreArchivo);
    void setUSB_Close();
    void setUSBMounted();
    boolean  getConexionSerie();
    String getMensajeBalanza();
    int getOK();
    int getOKUSB();
    ESTADO_PEDIDO getEstadoPedido();
    void getInfo();
    String getVersion();


}
