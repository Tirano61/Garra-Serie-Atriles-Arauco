package com.example.dramirez.garrraspuertoserie;

public class Variables {

    private static String  CAPACIDAD,CELDAS,DIVISION, SENSIBILIDAD, CONVERSIONES,RECORTES, LOGICA;

    private static int VENTANA, KGFILTRO;
    private static boolean RELOJ = false;

    public static boolean isRELOJ() {
        return RELOJ;
    }

    public static void setRELOJ(boolean RELOJ) {
        Variables.RELOJ = RELOJ;
    }

    public static String getLOGICA() {
        return LOGICA;
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
