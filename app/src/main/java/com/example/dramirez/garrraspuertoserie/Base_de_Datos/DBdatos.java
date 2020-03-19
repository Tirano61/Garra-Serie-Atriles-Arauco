package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBdatos {
    public static final String TABLE_NAME_DATOS= "tdatos";

    public static final  String FDAT_ID = "_id";
    public static final  String FDAT_ID_GRUA = "id_grua";
    public static final  String FDAT_REMITO = "remito";
    public static final  String FDAT_DESTINO = "destino";
    public static final  String FDAT_PRODUCTO = "producto";
    public static final  String FDAT_MEDIDA = "medida_aserrable";
    public static final  String FDAT_RODAL = "rodal";
    public static final  String FDAT_FECHA_CORTE = "fecha_corte";
    public static final  String FDAT_OPERADOR = "operador";
    public static final  String FDAT_ACTA_INTERVENCION = "acta_intervencion";
    public static final  String FDAT_TIPO_INTERVENCION = "tipo_intervencion";
    public static final  String FDAT_PREDIO = "predio";
    public static final  String FDAT_UMF = "umf";
    public static final  String FDAT_PROVEEDOR_ELAVORACION = "proveedor_elavoracion";
    public static final  String FDAT_PROVEEDOR_CARGA = "proveedor_carga";
    public static final  String FDAT_RAIZ_REMITO = "raiz_remito";



    public  static final  String TAB_DATOS = "CREATE TABLE " + TABLE_NAME_DATOS + "(" +
            FDAT_ID + " integer primary key autoincrement, " +  //0
            FDAT_ID_GRUA + " text," +                           //1
            FDAT_REMITO + " text," +                            //2
            FDAT_DESTINO + " text," +                           //3
            FDAT_PRODUCTO + " text," +                          //4
            FDAT_MEDIDA + " text," +                            //5
            FDAT_RODAL + " text," +                             //6
            FDAT_FECHA_CORTE + " text," +                       //7
            FDAT_OPERADOR + " text," +                          //8
            FDAT_ACTA_INTERVENCION + " text," +                 //9
            FDAT_TIPO_INTERVENCION + " text," +                 //10
            FDAT_PREDIO + " text," +                            //11
            FDAT_UMF + " text," +                               //12
            FDAT_PROVEEDOR_ELAVORACION + " text," +             //13
            FDAT_PROVEEDOR_CARGA + " text," +                   //14
            FDAT_RAIZ_REMITO  + " text"+");";                   //15



    private int idConexion;
    private String id_grua;
    private String remito;
    private String destino;
    private String producto;
    private String medida_aserrable;
    private String rodal;
    private String fecha_corte;
    private String operador;
    private String acta_intervencion;
    private String tipo_intervencion;
    private String predio;
    private String umf;
    private String proveedor_elavoracion;
    private String proveedor_carga;
    private String raiz_remito;



    public DBdatos(String id_grua,String remito,String destino, String producto, String medida_aserrable, String rodal,String fecha_corte, String operador,String acta_intervencion,
                   String tipo_intervencion,String predio,String umf, String proveedor_elavoracion,String proveedor_carga,
                   String raiz_remito )
    {
        this.id_grua = id_grua;
        this.remito = remito;
        this.destino = destino;
        this.producto = producto;
        this.medida_aserrable = medida_aserrable;
        this.rodal = rodal;
        this.fecha_corte = fecha_corte;
        this.operador = operador;
        this.acta_intervencion = acta_intervencion;

        this.tipo_intervencion = tipo_intervencion;
        this.predio = predio;
        this.umf = umf;
        this.proveedor_elavoracion = proveedor_elavoracion;
        this.proveedor_carga = proveedor_carga;
        this.raiz_remito = raiz_remito;


    }


    public String getRemito() {
        return remito;
    }

    public void setRemito(String remito) {
        this.remito = remito;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public String getMedida_aserrable() {
        return medida_aserrable;
    }

    public void setMedida_aserrable(String medida_aserrable) {
        this.medida_aserrable = medida_aserrable;
    }

    public String getRodal() {
        return rodal;
    }

    public void setRodal(String rodal) {
        this.rodal = rodal;
    }

    public String getFecha_corte() {
        return fecha_corte;
    }

    public void setFecha_corte(String fecha_corte) {
        this.fecha_corte = fecha_corte;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getActa_intervencion() {
        return acta_intervencion;
    }

    public void setActa_intervencion(String acta_intervencion) {
        this.acta_intervencion = acta_intervencion;
    }

    public String getTipo_intervencion() {
        return tipo_intervencion;
    }

    public void setTipo_intervencion(String tipo_intervencion) {
        this.tipo_intervencion = tipo_intervencion;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getUmf() {
        return umf;
    }

    public void setUmf(String umf) {
        this.umf = umf;
    }

    public String getProveedor_elavoracion() {
        return proveedor_elavoracion;
    }

    public void setProveedor_elavoracion(String proveedor_elavoracion) {
        this.proveedor_elavoracion = proveedor_elavoracion;
    }

    public String getProveedor_carga() {
        return proveedor_carga;
    }

    public void setProveedor_carga(String proveedor_carga) {
        this.proveedor_carga = proveedor_carga;
    }

    public String getRaiz_remito() {
        return raiz_remito;
    }

    public void setRaiz_remito(String raiz_remito) {
        this.raiz_remito = raiz_remito;
    }

    public String getId_grua() {
        return id_grua;
    }

    public void setId_grua(String id_grua) {
        this.id_grua = id_grua;
    }
}

