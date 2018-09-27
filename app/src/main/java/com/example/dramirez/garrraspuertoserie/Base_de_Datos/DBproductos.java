package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBproductos {
    public static final String TABLE_NAME_PRODUCTOS = "tproductos";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FPRO_ID = "_id";
    public static final  String FPRO_ID_NUMERO= "numero";
    public static final  String FPRO_ID_NOMBRE = "nombre";

    // todo************ CREAR TABLA PRODUCTOS ***************//

    public  static final  String TAB_PRODUCTOS = "CREATE TABLE " + TABLE_NAME_PRODUCTOS + "(" +
            FPRO_ID + " integer primary key autoincrement, " +
            FPRO_ID_NUMERO + " text," +
            FPRO_ID_NOMBRE + " text"+");";

    private int idproducto;
    private String numero;
    private String nombre;

    public DBproductos(String numero, String nombre)
    {
        this.numero = numero;
        this.nombre = nombre;
    }
    public int getIdProductos() {
        return idproducto;
    }

    public void setIdProductos(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
