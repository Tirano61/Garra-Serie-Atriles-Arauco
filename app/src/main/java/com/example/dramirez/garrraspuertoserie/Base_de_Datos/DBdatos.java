package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBdatos {
    public static final String TABLE_NAME_DATOS= "tdatos";

    public static final  String FDAT_ID = "_id";
    public static final  String FDAT_PRODUCTO = "producto";
    public static final  String FDAT_PATENTE = "patente";
    public static final  String FDAT_TARA = "tara";
    public static final  String FDAT_VOLUMEN = "volumen";
    public static final  String FDAT_CODIGO = "codigo";


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_DATOS = "CREATE TABLE " + TABLE_NAME_DATOS + "(" +
            FDAT_ID + " integer primary key autoincrement, " +
            FDAT_PRODUCTO + " text," +
            FDAT_PATENTE + " text," +
            FDAT_TARA + " text," +
            FDAT_VOLUMEN + " text," +
            FDAT_CODIGO  + " text"+");";

    private int idConexion;
    private String producto;
    private String patente;
    private String tara;
    private String volumen;
    private String codigo;


    public DBdatos(String producto,String patente, String tara,String volumen,String codigo)
    {
        this.producto = producto;
        this.patente = patente;
        this.tara = tara;
        this.volumen = volumen;
        this.codigo = codigo;

    }

    public int getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(int idConexion) {
        this.idConexion = idConexion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
