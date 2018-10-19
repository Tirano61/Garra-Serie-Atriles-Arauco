package com.example.dramirez.garrraspuertoserie;

public class Variables {

    private static String  CAPACIDAD,CELDAS,DIVISION, SENSIBILIDAD, CONVERSIONES,RECORTES, LOGICA;
    private static String PATENTE, CODIGO,PRODUCTO, CLIENTE, TARA, VOLUMEN;
    private static int VENTANA, KGFILTRO, TICKETS;
    private static boolean RELOJ = false;
    private static boolean BATERIA = false;
    private static boolean TIEMPOCARGA = false;
    private  static String CABECERA_1,CABECERA_2,CABECERA_3,CABECERA_4;


    public static int getTICKETS() {
        return TICKETS;
    }

    public static void setTICKETS(int TICKETS) {
        Variables.TICKETS = TICKETS;
    }

    public static String getCabecera1() {
        return CABECERA_1;
    }

    public static void setCabecera1(String cabecera1) {
        CABECERA_1 = cabecera1;
    }

    public static String getCabecera2() {
        return CABECERA_2;
    }

    public static void setCabecera2(String cabecera2) {
        CABECERA_2 = cabecera2;
    }

    public static String getCabecera3() {
        return CABECERA_3;
    }

    public static void setCabecera3(String cabecera3) {
        CABECERA_3 = cabecera3;
    }

    public static String getCabecera4() {
        return CABECERA_4;
    }

    public static void setCabecera4(String cabecera4) {
        CABECERA_4 = cabecera4;
    }

    public static boolean isTIEMPOCARGA() {
        return TIEMPOCARGA;
    }

    public static void setTIEMPOCARGA(boolean TIEMPOCARGA) {
        Variables.TIEMPOCARGA = TIEMPOCARGA;
    }

    public static boolean isRELOJ() {
        return RELOJ;
    }

    public static String getPATENTE() {
        return PATENTE;
    }

    public static void setPATENTE(String PATENTE) {
        Variables.PATENTE = PATENTE;
    }

    public static String getCODIGO() {
        return CODIGO;
    }

    public static void setCODIGO(String CODIGO) {
        Variables.CODIGO = CODIGO;
    }

    public static String getPRODUCTO() {
        return PRODUCTO;
    }

    public static void setPRODUCTO(String PRODUCTO) {
        Variables.PRODUCTO = PRODUCTO;
    }

    public static String getCLIENTE() {
        return CLIENTE;
    }

    public static void setCLIENTE(String CLIENTE) {
        Variables.CLIENTE = CLIENTE;
    }

    public static String getTARA() {
        return TARA;
    }

    public static void setTARA(String TARA) {
        Variables.TARA = TARA;
    }

    public static String getVOLUMEN() {
        return VOLUMEN;
    }

    public static void setVOLUMEN(String VOLUMEN) {
        Variables.VOLUMEN = VOLUMEN;
    }

    public static void setRELOJ(boolean RELOJ) {
        Variables.RELOJ = RELOJ;
    }

    public static String getLOGICA() {
        return LOGICA;
    }

    public static boolean isBATERIA() {
        return BATERIA;
    }

    public static void setBATERIA(boolean BATERIA) {
        Variables.BATERIA = BATERIA;
    }

    public static void setLOGICA(String LOGICA) {
        Variables.LOGICA = LOGICA;
    }

    public static String getCAPACIDAD() {
        return CAPACIDAD;
    }

    public static void setCAPACIDAD(String CAPACIDAD) {
        Variables.CAPACIDAD = CAPACIDAD;
    }

    public static String getCELDAS() {
        return CELDAS;
    }

    public static void setCELDAS(String CELDAS) {
        Variables.CELDAS = CELDAS;
    }

    public static String getDIVISION() {
        return DIVISION;
    }

    public static void setDIVISION(String DIVISION) {
        Variables.DIVISION = DIVISION;
    }

    public static String getSENSIBILIDAD() {
        return SENSIBILIDAD;
    }

    public static void setSENSIBILIDAD(String SENSIBILIDAD) {
        Variables.SENSIBILIDAD = SENSIBILIDAD;
    }

    public int getVENTANA() {
        return VENTANA;
    }

    public void setVENTANA(int VENTANA) {
        this.VENTANA = VENTANA;
    }

    public int getKGFILTRO() {
        return KGFILTRO;
    }

    public void setKGFILTRO(int KGFILTRO) {
        this.KGFILTRO = KGFILTRO;
    }

    public static String getCONVERSIONES() {
        return CONVERSIONES;
    }

    public static void setCONVERSIONES(String CONVERSIONES) {
        Variables.CONVERSIONES = CONVERSIONES;
    }

    public static String getRECORTES() {
        return RECORTES;
    }

    public static void setRECORTES(String RECORTES) {
        Variables.RECORTES = RECORTES;
    }
}
