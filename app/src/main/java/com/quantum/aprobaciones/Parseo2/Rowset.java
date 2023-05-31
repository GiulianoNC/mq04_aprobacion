package com.quantum.aprobaciones.Parseo2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rowset {


    @SerializedName("ORDEN_LINEA")
    @Expose
    private String ordenLinea;
    @SerializedName("FECHA_SOLICITUD")
    @Expose
    private String fechaSolicitud;
    @SerializedName("FECHA_ORDEN")
    @Expose
    private String fechaOrden;
    @SerializedName("FECHA_PROMETIDA")
    @Expose
    private String fechaPrometida;
    @SerializedName("DESC1")
    @Expose
    private String desc1;
    @SerializedName("DESC2")
    @Expose
    private String desc2;
    @SerializedName("UM")
    @Expose
    private String um;
    @SerializedName("CANTIDAD")
    @Expose
    private String cantidad;
    @SerializedName("COSTO_UNIT_DOM")
    @Expose
    private String costoUnitDom;
    @SerializedName("COSTO_TOTAL_DOM")
    @Expose
    private String costoTotalDom;
    @SerializedName("MONEDA_TRAN")
    @Expose
    private String monedaTran;
    @SerializedName("COSTO_UNIT_FOR")
    @Expose
    private String costoUnitFor;
    @SerializedName("COSTO_TOTAL_FOR")
    @Expose
    private String costoTotalFor;

    public String getOrdenLinea() {
        return ordenLinea;
    }

    public void setOrdenLinea(String ordenLinea) {
        this.ordenLinea = ordenLinea;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaPrometida() {
        return fechaPrometida;
    }

    public void setFechaPrometida(String fechaPrometida) {
        this.fechaPrometida = fechaPrometida;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCostoUnitDom() {
        return costoUnitDom;
    }

    public void setCostoUnitDom(String costoUnitDom) {
        this.costoUnitDom = costoUnitDom;
    }

    public String getCostoTotalDom() {
        return costoTotalDom;
    }

    public void setCostoTotalDom(String costoTotalDom) {
        this.costoTotalDom = costoTotalDom;
    }

    public String getMonedaTran() {
        return monedaTran;
    }

    public void setMonedaTran(String monedaTran) {
        this.monedaTran = monedaTran;
    }

    public String getCostoUnitFor() {
        return costoUnitFor;
    }

    public void setCostoUnitFor(String costoUnitFor) {
        this.costoUnitFor = costoUnitFor;
    }

    public String getCostoTotalFor() {
        return costoTotalFor;
    }

    public void setCostoTotalFor(String costoTotalFor) {
        this.costoTotalFor = costoTotalFor;
    }
}
