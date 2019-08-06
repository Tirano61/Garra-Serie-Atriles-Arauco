package com.example.dramirez.garrraspuertoserie.FragmentInterfaces;

import android.widget.TextView;

public interface EnvioDatos {
    boolean comprobarCero(TextView textView);
    void enviarCero(int peso);
    int recabarPeso(TextView textView);
    int lugarDeCarga(int lugar);

}
