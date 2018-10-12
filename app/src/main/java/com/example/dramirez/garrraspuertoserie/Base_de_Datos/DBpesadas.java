package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBpesadas {

    public static final String TABLE_NAME_PESADAS = "tpesadas";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FPES_ID = "_id";
    public static final  String FPES_FECHA = "fecha";
    public static final  String FPES_HORA = "hora";
    public static final  String FPES_PRODUCTO = "producto";
    public static final  String FPES_CARGIO = "cargio";
    public static final  String FPES_PATENTE = "patente";
    public static final  String FPES_VOLUMEN = "volumen";
    public static final  String FPES_CODIGO = "codigo";
    public static final  String FPES_CLIENTE = "cliente";
    public static final  String FPES_BRUTO = "bruto";
    public static final  String FPES_TARA = "tara";
    public static final  String FPES_NETO = "neto";


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_PESADAS = "CREATE TABLE " + TABLE_NAME_PESADAS + "(" +
            FPES_ID + " integer primary key autoincrement, " +
            FPES_FECHA + " DATETIME," +
            FPES_HORA + " text," +
            FPES_PRODUCTO + " text," +
            FPES_CARGIO + " text," +
            FPES_PATENTE + " text," +
            FPES_VOLUMEN + " text," +
            FPES_CODIGO + " text," +
            FPES_CLIENTE + " text," +
            FPES_BRUTO + " text," +
            FPES_TARA + " text," +
            FPES_NETO + " text"+");";

    private int idPesada;
    private String fecha;
    private String hora;
    private String producto;
    private String cargio;
    private String patente;
    private String volumen;
    private String codigo;
    private String cliente;
    private String bruto;
    private String tara;
    private String neto;

    public DBpesadas(String fecha, String hora, String producto, String cargio, String patente,
                      String volumen,String codigo,String cliente, String bruto, String tara,String neto)
    {
        this.fecha = fecha;
        this.hora = hora;
        this.producto = producto;
        this.cargio = cargio;
        this.patente = patente;
        this.tara = tara;
        this.volumen = volumen;
        this.codigo = codigo;
        this.cliente = cliente;
        this.bruto = bruto;
        this.tara = tara;
        this.neto = neto;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getNeto() {
        return neto;
    }

    public void setNeto(String neto) {
        this.neto = neto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
