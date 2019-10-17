package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBdatos {
    public static final String TABLE_NAME_DATOS= "tdatos";

    public static final  String FDAT_ID = "_id";
    public static final  String FDAT_PRODUCTO = "producto";
    public static final  String FDAT_GRUA = "grua";
    public static final  String FDAT_OPERADOR = "operador";
    public static final  String FDAT_VEHICULO = "vehiculo";
    public static final  String FDAT_CODIGO = "codigo";
    public static final  String FDAT_TARA = "tara";




    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_DATOS = "CREATE TABLE " + TABLE_NAME_DATOS + "(" +
            FDAT_ID + " integer primary key autoincrement, " +
            FDAT_PRODUCTO + " text," +
            FDAT_GRUA + " text," +
            FDAT_OPERADOR + " text," +
            FDAT_VEHICULO + " text," +
            FDAT_CODIGO + " text," +
            FDAT_TARA  + " text"+");";

    private int idConexion;
    private String producto;
    private String grua;
    private String operador;
    private String vehiculo;
    private String codigo;
    private String tara;

    public DBdatos(String producto,String grua, String operador,String vehiculo,
                   String codigo,String tara)
    {
        this.producto = producto;
        this.grua = grua;
        this.operador = operador;
        this.vehiculo = vehiculo;
        this.codigo = codigo;
        this.tara = tara;

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

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrua() {
        return grua;
    }

    public void setGrua(String grua) {
        this.grua = grua;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }
}

