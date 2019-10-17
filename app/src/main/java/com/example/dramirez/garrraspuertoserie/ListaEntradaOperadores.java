package com.example.dramirez.garrraspuertoserie;

public class ListaEntradaOperadores {

    private String id;
    private String cod_operador;
    private String nombre;


    public ListaEntradaOperadores(String id, String cod_operador,String nombre)
    {
        this.id = id;
        this.cod_operador = cod_operador;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod_operador() {
        return cod_operador;
    }

    public void setCod_operador(String cod_operador) {
        this.cod_operador = cod_operador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
