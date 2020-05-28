package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBpesadas {

    public static final String TABLE_NAME_PESADAS = "tpesadas";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FPES_ID = "_id";                                     //0
    public static final  String FPES_FECHA = "fecha";                                //1
    public static final  String FPES_HORA = "hora";                                  //2
    public static final  String FPES_ARRIBO = "arribo";                              //3
    public static final  String FPES_ID_GRUA = "Idgrua";                             //4
    public static final  String FPES_CHASIS = "chasis";                              //5
    public static final  String FPES_ACOPLADO = "acoplado";                          //6
    public static final  String FPES_ACOPLADO2 = "acoplado2";                        //7
    public static final  String FPES_REMITO = "remito";                              //8
    public static final  String FPES_DESTINO = "destino";                            //9
    public static final  String FPES_PRODUCTO = "producto";                          //10
    public static final  String FPES_MEDIDA_ASERRABLE = "medida_aserrable";          //11
    public static final  String FPES_RODAL = "rodal";                                //12
    public static final  String FPES_FECHA_CORTE = "fecha_corte";                    //13
    public static final  String FPES_OPERADOR = "operador";                          //14
    public static final  String FPES_ACTA_INTERVENCION = "acta_intervencion";        //15
    public static final  String FPES_ITPO_INTERVENCION = "tipo_intervencion";        //16
    public static final  String FPES_PREDIO = "predio";                              //17
    public static final  String FPES_UMF = "umf";                                    //18
    public static final  String FPES_PROVEEDOR_ELAVORACION = "proveedor_elavoracion";//19
    public static final  String FPES_PROVEEDOR_CARGA = "proveedor_carga";            //20
    public static final  String FPES_RAIZ_REMITO = "raiz_remito";                    //21
    public static final  String FPES_BANCOS = "bancos";                              //22
    public static final  String FPES_BANCO1 = "banco1";                              //23
    public static final  String FPES_BANCO2 = "banco2";                              //24
    public static final  String FPES_BANCO3 = "banco3";                              //25
    public static final  String FPES_BANCO4 = "banco4";                              //26
    public static final  String FPES_BANCO5 = "banco5";                              //27
    public static final  String FPES_BANCO6 = "banco6";                              //28
    public static final  String FPES_BANCO7 = "banco7";                              //29
    public static final  String FPES_BANCO8 = "banco8";                              //30
    public static final  String FPES_BANCO9 = "banco9";                              //31
    public static final  String FPES_CARGAS = "cargas";                              //32
    public static final  String FPES_BRUTO = "bruto";                                //33
    public static final  String FPES_TARA = "tara";                                  //34
    public static final  String FPES_NETO = "neto";                                  //35
    public static final  String FPES_TIEMPO_CARGA = "tiempo_carga";                  //36


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_PESADAS = "CREATE TABLE " + TABLE_NAME_PESADAS + "(" +
            FPES_ID + " integer primary key autoincrement, " +
            FPES_FECHA + " DATETIME," +
            FPES_HORA + " text," +
            FPES_ARRIBO + " text," +
            FPES_ID_GRUA + " text," +
            FPES_CHASIS + " text," +
            FPES_ACOPLADO + " text," +
            FPES_ACOPLADO2 + " text," +
            FPES_REMITO + " text," +
            FPES_DESTINO + " text," +
            FPES_PRODUCTO + " text," +
            FPES_MEDIDA_ASERRABLE + " text," +
            FPES_RODAL + " text," +
            FPES_FECHA_CORTE + " text," +
            FPES_OPERADOR + " text," +
            FPES_ACTA_INTERVENCION + " text," +
            FPES_ITPO_INTERVENCION + " text," +
            FPES_PREDIO + " text," +
            FPES_UMF + " text," +
            FPES_PROVEEDOR_ELAVORACION + " text," +
            FPES_PROVEEDOR_CARGA + " text," +
            FPES_RAIZ_REMITO + " text," +
            FPES_BANCOS + " text," +
            FPES_BANCO1 + " text," +
            FPES_BANCO2 + " text," +
            FPES_BANCO3 + " text," +
            FPES_BANCO4 + " text," +
            FPES_BANCO5 + " text," +
            FPES_BANCO6 + " text," +
            FPES_BANCO7 + " text," +
            FPES_BANCO8 + " text," +
            FPES_BANCO9 + " text," +
            FPES_CARGAS + " text," +
            FPES_BRUTO + " text," +
            FPES_TARA + " text," +
            FPES_NETO + " text," +
            FPES_TIEMPO_CARGA + " text"+");";

    private int idPesada;
    private String fecha;
    private String hora;
    private String arribo;
    private String Idgrua;
    private String chasis;
    private String acoplado;
    private String acoplado2;
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
    private String bancos;
    private String banco1;
    private String banco2;
    private String banco3;
    private String banco4;
    private String banco5;
    private String banco6;
    private String banco7;
    private String banco8;
    private String banco9;
    private String cargas;
    private String bruto;
    private String tara;
    private String neto;
    private String tiempo_carga;


    public DBpesadas(String fecha, String hora,String arribo,String Idgrua,String chasis,String acoplado,String acoplado2, String remito,
                     String destino,String producto,String medida_aserrable,String rodal,String fecha_corte,
                     String operador,String acta_intervencion,String tipo_intervencion,String predio, String umf,
                     String proveedor_elavoracion,String proveedor_carga, String raiz_remito,
                     String bancos,String banco1, String banco2,String banco3,String banco4, String banco5,
                     String banco6,String banco7, String banco8,String banco9,String cargas, String bruto,
                     String tara,
                     String neto, String tiempo_carga)
    {
        this.fecha = fecha;
        this.hora = hora;
        this.arribo = arribo;
        this.Idgrua = Idgrua;
        this.producto = producto;
        this.chasis = chasis;
        this.acoplado = acoplado;
        this.acoplado2 = acoplado2;
        this.remito = remito;
        this.destino = destino;

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

        this.bancos = bancos;
        this.banco1 = banco1;
        this.banco2 = banco2;
        this.banco3 = banco3;
        this.banco4 = banco4;
        this.banco5 = banco5;
        this.banco6 = banco6;
        this.banco7 = banco7;
        this.banco8 = banco8;
        this.banco9 = banco9;
        this.cargas = cargas;
        this.bruto = bruto;

        this.tara = tara;
        this.neto = neto;
        this.tiempo_carga = tiempo_carga;
    }


    public String getArribo() {
        return arribo;
    }

    public void setArribo(String arribo) {
        this.arribo = arribo;
    }

    public String getAcoplado2() {
        return acoplado2;
    }

    public void setAcoplado2(String acoplado2) {
        this.acoplado2 = acoplado2;
    }

    public String getBancos() {
        return bancos;
    }

    public void setBancos(String bancos) {
        this.bancos = bancos;
    }

    public String getBanco1() {
        return banco1;
    }

    public void setBanco1(String banco1) {
        this.banco1 = banco1;
    }

    public String getBanco2() {
        return banco2;
    }

    public void setBanco2(String banco2) {
        this.banco2 = banco2;
    }

    public String getBanco3() {
        return banco3;
    }

    public void setBanco3(String banco3) {
        this.banco3 = banco3;
    }

    public String getBanco4() {
        return banco4;
    }
    public void setBanco4(String banco4) {
        this.banco4 = banco4;
    }

    public String getBanco5() {
        return banco5;
    }

    public void setBanco5(String banco5) {
        this.banco5 = banco5;
    }

    public String getBanco6() {
        return banco6;
    }

    public void setBanco6(String banco6) {
        this.banco6 = banco6;
    }

    public String getBanco7() {
        return banco7;
    }

    public void setBanco7(String banco7) {
        this.banco7 = banco7;
    }

    public String getBanco8() {
        return banco8;
    }

    public void setBanco8(String banco8) {
        this.banco8 = banco8;
    }

    public String getBanco9() {
        return banco9;
    }

    public void setBanco9(String banco9) {
        this.banco9 = banco9;
    }

    public String getCargas() {
        return cargas;
    }

    public void setCargas(String cargas) {
        this.cargas = cargas;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
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

    public String getIdgrua() {
        return Idgrua;
    }

    public void setIdgrua(String idgrua) {
        Idgrua = idgrua;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getAcoplado() {
        return acoplado;
    }

    public void setAcoplado(String acoplado) {
        this.acoplado = acoplado;
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

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getNeto() {
        return neto;
    }

    public void setNeto(String neto) {
        this.neto = neto;
    }

    public String getTiempo_carga() {
        return tiempo_carga;
    }

    public void setTiempo_carga(String tiempo_carga) {
        this.tiempo_carga = tiempo_carga;
    }
}
