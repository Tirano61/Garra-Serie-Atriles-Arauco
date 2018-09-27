package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBpesadas {

    public static final String TABLE_NAME_PESADAS = "tpesadas";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FPES_ID = "_id";
    public static final  String FPES_FECHA = "fecha";
    public static final  String FPES_HORA = "hora";
    public static final  String FPES_CARGIO = "cargio";
    public static final  String FPES_PRODUCTO = "producto";
    public static final  String FPES_PATENTE = "patente";
    public static final  String FPES_TARA = "tara";
    public static final  String FPES_VOLUMEN = "volumen";
    public static final  String FPES_CODIGO = "codigo";


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_PESADAS = "CREATE TABLE " + TABLE_NAME_PESADAS + "(" +
            FPES_ID + " integer primary key autoincrement, " +
            FPES_FECHA + " DATETIME," +
            FPES_HORA + " text," +
            FPES_CARGIO + " text," +
            FPES_PRODUCTO + " text," +
            FPES_PATENTE + " text," +
            FPES_TARA + " text," +
            FPES_VOLUMEN + " text," +
            FPES_CODIGO + " text"+");";

    private int idPesada;
    private String fecha;
    private String hora;
    private String cargio;
    private String producto;
    private String patente;
    private String tara;
    private String volumen;
    private String codigo;


    public DBpesadas(String fecha, String hora, String cargio,String producto, String patente, String tara, String volumen,String codigo)
    {
        this.fecha = fecha;
        this.hora = hora;
        this.cargio = cargio;
        this.producto = producto;
        this.patente = patente;
        this.tara = tara;
        this.volumen = volumen;
        this.codigo = codigo;

    }


    public int getIdPesada() {
        return idPesada;
    }

    public void setIdPesada(int idPesada) {
        this.idPesada = idPesada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCargio() {
        return cargio;
    }

    public void setCargio(String cargio) {
        this.cargio = cargio;
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