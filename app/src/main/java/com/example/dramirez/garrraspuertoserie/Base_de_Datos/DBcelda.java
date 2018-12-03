package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBcelda {
    public static final String TABLE_NAME_CELDA = "tcelda";

    //********* CAMPOS TABLA CERO ***********//
    public static final  String FCELDA_ID = "_id";
    public static final  String FCELDA_CELDA= "celda";


    //************ CREAR TABLA CELDA ***************//
    public  static final  String TAB_CELDA = "CREATE TABLE " + TABLE_NAME_CELDA + "(" +
            FCELDA_ID + " integer primary key autoincrement, " +
            FCELDA_CELDA  + " text"+");";

    private int idCelda;
    private String celda;

    public DBcelda(String celda)
    {
        this.celda = celda;
    }

    public String getCelda() {
        return celda;
    }

    public void setCelda(String celda) {
        this.celda = celda;
    }
}
