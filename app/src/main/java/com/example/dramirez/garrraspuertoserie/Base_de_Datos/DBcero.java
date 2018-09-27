package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBcero {
    public static final String TABLE_NAME_CERO = "tcero";

    //********* CAMPOS TABLA CERO ***********//
    public static final  String FCERO_ID = "_id";
    public static final  String FCERO_CERO= "cero";


    //************ CREAR TABLA CERO ***************//
    public  static final  String TAB_CERO = "CREATE TABLE " + TABLE_NAME_CERO + "(" +
            FCERO_ID + " integer primary key autoincrement, " +
            FCERO_CERO  + " text"+");";

    private int idCero;
    private String cero;

    public DBcero(String cero)
    {

        this.cero = cero;
    }

    public int getIdCero() {
        return idCero;
    }

    public void setIdCero(int idCero) {
        this.idCero = idCero;
    }

    public String getCero() {
        return cero;
    }

    public void setCero(String cero) {
        this.cero = cero;
    }
}
