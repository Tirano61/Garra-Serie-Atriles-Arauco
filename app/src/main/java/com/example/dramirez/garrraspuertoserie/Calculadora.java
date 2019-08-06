package com.example.dramirez.garrraspuertoserie;

public class Calculadora
{
    private int total = 0;
    private int tara = 0;

    public Calculadora(String total, String tara){
        this.total = Integer.valueOf(total);
        this.tara = Integer.valueOf(tara);
    }
    public Calculadora(){

    }
    public int Calcular(int bancos){
        int totalBancos =0;

        totalBancos =(total - tara) / bancos;
        return totalBancos;
    }

}
