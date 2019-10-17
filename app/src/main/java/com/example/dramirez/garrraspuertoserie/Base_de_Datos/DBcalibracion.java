package com.example.dramirez.garrraspuertoserie.Base_de_Datos;

public class DBcalibracion
{
    public static final String TABLE_NAME_CALIBRACION = "tcalibracion";

    public static final  String FCAL_ID = "_id";
    public static final  String FCAL_CAPACIDAD = "capacidad";
    public static final  String FCAL_CELDAS = "celdas";
    public static final  String FCAL_DIVISION = "division";
    public static final  String FCAL_SENSIBILIDAD = "sensibilidad";
    public static final  String FCAL_VENTANA = "ventana";
    public static final  String FCAL_KGFILTRO = "kgfiltro";
    public static final  String FCAL_CONVERSIONES = "conversiones";
    public static final  String FCAL_RECORTES = "recortes";
    public static final  String FCAL_LOGICA = "logica";
    public static final  String FCAL_TICKET = "ticket";
    public static final  String FCAL_SEMI = "semiaut";

    public static final String TAB_CALIBRACION  = "CREATE TABLE " + TABLE_NAME_CALIBRACION + "(" +
            FCAL_ID + " integer primary key autoincrement, " +
            FCAL_CAPACIDAD + " text," +
            FCAL_CELDAS + " text," +
            FCAL_DIVISION + " text," +
            FCAL_SENSIBILIDAD + " text," +
            FCAL_VENTANA + " text," +
            FCAL_KGFILTRO + " text," +
            FCAL_CONVERSIONES + " text," +
            FCAL_RECORTES + " text," +
            FCAL_LOGICA + " text," +
            FCAL_TICKET + " text," +
            FCAL_SEMI  + " text"+");";


    private int idCalibracion;
    private String capacidad;
    private String celdas;
    private String division;
    private String sensibilidad;
    private String ventana;
    private String kgfiltro;
    private String conversiones;
    private String recortes;
    private String logica;
    private String ticket;
    private String semiaut;
    public DBcalibracion(String capacidad, String celdas,String division, String sensibilidad, String ventana,
                         String kgfiltro, String conversiones, String recortes,String logica, String ticket,String semiaut)
    {
        this.capacidad = capacidad;
        this.celdas = celdas;
        this.division = division;
        this.sensibilidad = sensibilidad;
        this.ventana = ventana;
        this.kgfiltro = kgfiltro;
        this.conversiones = conversiones;
        this.recortes = recortes;
        this.logica = logica;
        this.ticket = ticket;
        this.semiaut = semiaut;
    }

    public String getSemiaut() {
        return semiaut;
    }

    public void setSemiaut(String semiaut) {
        this.semiaut = semiaut;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getLogica() {
        return logica;
    }

    public void setLogica(String logica) {
        this.logica = logica;
    }

    public int getIdCalibracion() {
        return idCalibracion;
    }

    public void setIdCalibracion(int idCalibracion) {
        this.idCalibracion = idCalibracion;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getCeldas() {
        return celdas;
    }

    public void setCeldas(String celdas) {
        this.celdas = celdas;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSensibilidad() {
        return sensibilidad;
    }

    public void setSensibilidad(String sensibilidad) {
        this.sensibilidad = sensibilidad;
    }

    public String getVentana() {
        return ventana;
    }

    public void setVentana(String ventana) {
        this.ventana = ventana;
    }

    public String getKgfiltro() {
        return kgfiltro;
    }

    public void setKgfiltro(String kgfiltro) {
        this.kgfiltro = kgfiltro;
    }

    public String getConversiones() {
        return conversiones;
    }

    public void setConversiones(String conversiones) {
        this.conversiones = conversiones;
    }

    public String getRecortes() {
        return recortes;
    }

    public void setRecortes(String recortes) {
        this.recortes = recortes;
    }
}
