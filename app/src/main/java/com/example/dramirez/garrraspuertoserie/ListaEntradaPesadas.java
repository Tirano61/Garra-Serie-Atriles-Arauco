package com.example.dramirez.garrraspuertoserie;

/**
 * Created by dramirez on 17/11/2016.
 */

public class ListaEntradaPesadas {

    private String idPesada;
    private String fecha;
    private String hora;
    private String Idgrua;
    private String chasis;
    private String acoplado;
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
    private String bruto;
    private String tara;
    private String neto;
    private String tiempo_carga;

    public ListaEntradaPesadas(String idPesada,String fecha, String hora,String Idgrua,String chasis,String acoplado, String remito,
                               String tara,String destino,String producto,String medida_aserrable,String rodal,String fecha_corte,
                               String operador,String acta_intervencion,String tipo_intervencion,String predio, String umf,
                               String proveedor_elavoracion,String proveedor_carga, String raiz_remito, String bruto,

                               String neto, String tiempo_carga)
    {
        this.idPesada = idPesada;
        this.fecha = fecha;
        this.hora = hora;
        this.Idgrua = Idgrua;
        this.chasis = chasis;
        this.acoplado = acoplado;
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


        this.bruto = bruto;

        this.tara = tara;
        this.neto = neto;
        this.tiempo_carga = tiempo_carga;
    }

    public String getIdPesada() {
        return idPesada;
    }

    public void setIdPesada(String idPesada) {
        this.idPesada = idPesada;
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
