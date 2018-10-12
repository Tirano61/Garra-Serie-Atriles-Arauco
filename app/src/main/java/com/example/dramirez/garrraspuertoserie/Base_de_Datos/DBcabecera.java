package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBcabecera
{
    public static final String TABLE_NAME_CABECERA= "tcabecera";

    public static final  String FCAB_ID = "_id";
    public static final  String FCAB_UNO = "uno";
    public static final  String FCAB_DOS = "dos";
    public static final  String FCAB_TRES = "tres";
    public static final  String FCAB_CUATRO = "cuatro";


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_CABECERA = "CREATE TABLE " + TABLE_NAME_CABECERA + "(" +
            FCAB_ID + " integer primary key autoincrement, " +
            FCAB_UNO + " text," +
            FCAB_DOS + " text," +
            FCAB_TRES + " text," +
            FCAB_CUATRO  + " text"+");";

    private int idCabecera;
    private String uno;
    private String dos;
    private String tres;
    private String cuatro;

    public DBcabecera(String uno,String dos, String tres,String cuatro)
    {
        this.uno = uno;
        this.dos = dos;
        this.tres = tres;
        this.cuatro = cuatro;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getTres() {
        return tres;
    }

    public void setTres(String tres) {
        this.tres = tres;
    }

    public String getCuatro() {
        return cuatro;
    }

    public void setCuatro(String cuatro) {
        this.cuatro = cuatro;
    }
}
