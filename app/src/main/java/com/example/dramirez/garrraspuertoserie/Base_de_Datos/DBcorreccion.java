package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBcorreccion {


    public static final String TABLE_NAME_CORRECCION= "tcorreccion";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FCO_ID = "_id";
    public static final  String FCO_PORC= "porcentaje";

    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_CORRECCION = "CREATE TABLE " + TABLE_NAME_CORRECCION + "(" +
            FCO_ID + " integer primary key autoincrement, " +
            FCO_PORC  + " text"+");";

    private int idCorreccion;
    private String porcentaje;

    public DBcorreccion(String porcentaje)
    {
        this.porcentaje = porcentaje;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getidCorreccion() { return idCorreccion;}

    public void setidCorreccion(int idCorreccion)
    { this.idCorreccion = idCorreccion; }

}
