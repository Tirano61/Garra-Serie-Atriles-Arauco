package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBpesadas {

    public static final String TABLE_NAME_PESADAS = "tpesadas";

    //********* CAMPOS TABLA PESADAS ***********//
    public static final  String FPES_ID = "_id"; //0
    public static final  String FPES_FECHA = "fecha"; //1
    public static final  String FPES_HORA = "hora";  //2
    public static final  String FPES_CARGIO = "cargio"; //3
    public static final  String FPES_PRODUCTO = "producto"; //4
    public static final  String FPES_GRUA = "grua"; //5
    public static final  String FPES_OPERADOR = "operador"; //6
    public static final  String FPES_VEHICULO = "vehiculo"; //7
    public static final  String FPES_CODIGO = "codigo"; //8
    public static final  String FPES_BANCOS = "bancos"; //9
    public static final  String FPES_BANCO1 = "banco1"; //10
    public static final  String FPES_BANCO2 = "banco2"; //11
    public static final  String FPES_BANCO3 = "banco3"; //12
    public static final  String FPES_BANCO4 = "banco4"; //13
    public static final  String FPES_BANCO5 = "banco5"; //14
    public static final  String FPES_BANCO6 = "banco6"; //15
    public static final  String FPES_BANCO7 = "banco7"; //16
    public static final  String FPES_BANCO8 = "banco8"; //17
    public static final  String FPES_BANCO9 = "banco9"; //18
    public static final  String FPES_CARGAS = "cargas"; //19
    public static final  String FPES_BRUTO = "bruto";   //20
    public static final  String FPES_TARA = "tara";     //21
    public static final  String FPES_NETO = "neto";     //22


    //************ CREAR TABLA PESADAS ***************//
    public  static final  String TAB_PESADAS = "CREATE TABLE " + TABLE_NAME_PESADAS + "(" +
            FPES_ID + " integer primary key autoincrement, " +
            FPES_FECHA + " DATETIME," +
            FPES_HORA + " text," +
            FPES_CARGIO + " text," +
            FPES_PRODUCTO + " text," +
            FPES_GRUA + " text," +
            FPES_OPERADOR + " text," +
            FPES_VEHICULO + " text," +
            FPES_CODIGO + " text," +
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
            FPES_NETO + " text"+");";

    private int idPesada;
    private String fecha;
    private String hora;
    private String cargio;
    private String producto;

    private String grua;
    private String operador;
    private String vehiculo;
    private String codigo;
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

    public DBpesadas(String fecha, String hora,String cargio,String producto,String grua,
                      String operador,String vehiculo,String codigo,String bancos,String banco1,String banco2,
                     String banco3,String banco4,String banco5,String banco6,String banco7,String banco8,
                     String banco9,String cargas, String bruto, String tara,String neto)
    {
        this.fecha = fecha;
        this.hora = hora;
        this.cargio = cargio;
        this.producto = producto;

        this.grua = grua;

        this.tara = tara;
        this.operador = operador;
        this.codigo = codigo;
        this.vehiculo = vehiculo;
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
    }


    public String getCargas() {
        return cargas;
    }

    public void setCargas(String cargas) {
        this.cargas = cargas;
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
