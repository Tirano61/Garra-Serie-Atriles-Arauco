package com.example.dramirez.garrraspuertoserie.Interfaces;

import com.example.dramirez.garrraspuertoserie.DriverCeldaSerie;
import com.example.dramirez.garrraspuertoserie.ESTADO_PEDIDO;

import java.io.IOException;

public interface DriverCelda {

    int getDato();
    int getCuentas();
    void setCalibracion(String envio) throws IOException;
    void pararPedido();
    void pedirCuentas();
    int getGuardado();
    String getMensajeBalanza();
    int getOK();
    ESTADO_PEDIDO getEstadoPedido();


}
