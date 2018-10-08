package com.example.dramirez.garrraspuertoserie;

/**
 * Created by dramirez on 17/11/2016.
 */

public class ListaEntradaPesadas {

    private String idPesada;
    private String fecha;
    private String hora;
    private String producto;
    private String cargio;
    private String patente;
    private String tara;
    private String volumen;
    private String codigo;
    private String kg;
    public ListaEntradaPesadas(String idPesada, String fecha, String hora, String producto, String cargio,
                               String patente, String tara, String volumen, String codigo, String kg)
    {
        this.idPesada = idPesada;
        this.fecha = fecha;
        this.hora = hora;
        this.producto = producto;
        this.cargio = cargio;
        this.patente = patente;
        this.tara = tara;
        this.volumen = volumen;
        this.codigo = codigo;
        this.kg = kg;

    }

    public String getIdPesada() {
        return idPesada;
    }

    public void setIdPesada(String idPesada) {
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

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCargio() {
        return cargio;
    }

    public void setCargio(String cargio) {
        this.cargio = cargio;
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

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }
}
