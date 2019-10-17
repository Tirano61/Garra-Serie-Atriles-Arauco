package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBoperadores {

    public static final String TABLE_NAME_OPERADORES= "toperadores";

    public static final  String FOP_ID = "_id";
    public static final  String FOP_CODIGO = "cod_operador";
    public static final  String FOP_NOMBRE = "nombre";


    //************ CREAR TABLA OPERADORES ***************//
    public  static final  String TAB_OPERADORES = "CREATE TABLE " + TABLE_NAME_OPERADORES + "(" +
            FOP_ID + " integer primary key autoincrement, " +
            FOP_CODIGO + " text," +
            FOP_NOMBRE  + " text"+");";

    private int idOperadores;
    private String cod_operador;
    private String nombre;


    public DBoperadores(String cod_operador,String nombre)
    {
        this.cod_operador = cod_operador;
        this.nombre = nombre;
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
