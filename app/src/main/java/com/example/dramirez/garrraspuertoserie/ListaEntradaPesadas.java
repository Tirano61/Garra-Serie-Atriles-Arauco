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
    private String volumen;
    private String codigo;
    private String cliente;
    private String bruto;
    private String tara;
    private String neto;

    public ListaEntradaPesadas(String idPesada, String fecha, String hora, String producto, String cargio,
                               String patente, String volumen, String codigo,String cliente, String bruto, String tara, String neto)
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
        this.cliente = cliente;
        this.bruto = bruto;
        this.tara = tara;
        this.neto = neto;
    }


    public String getBruto() {
        return bruto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

}
