package com.example.dramirez.garrraspuertoserie;

import java.io.IOException;

public interface DriverCelda {
    int getDato();
    void setCalibracion(String envio) throws IOException;
    void pararPedido();
    void pedirCuentas();

}
