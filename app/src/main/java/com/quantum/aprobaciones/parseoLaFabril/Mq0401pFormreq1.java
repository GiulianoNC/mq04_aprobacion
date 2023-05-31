package com.quantum.aprobaciones.parseoLaFabril;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mq0401pFormreq1 {
    @SerializedName("ORDEN_NRO")
    @Expose
    private String ordenNro;
    @SerializedName("ORDEN_TIPO")
    @Expose
    private String ordenTipo;
    @SerializedName("ORDEN_CIA")
    @Expose
    private String ordenCia;
    @SerializedName("INICIADOR_NOMBRE")
    @Expose
    private String iniciadorNombre;
    @SerializedName("PROVEEDOR_COD")
    @Expose
    private String proveedorCod;
    @SerializedName("PROVEEDOR_DESC")
    @Expose
    private String proveedorDesc;
    @SerializedName("COSTO_TOTAL_DOM")
    @Expose
    private String costoTotalDom;
    @SerializedName("MONEDA_TRAN")
    @Expose
    private String monedaTran;
    @SerializedName("COSTO_TOTAL_FOR")
    @Expose
    private String costoTotalFor;
    @SerializedName("FECHA_ORDEN")
    @Expose
    private String fechaOrden;

    public String getOrdenNro() {
        return ordenNro;
    }

    public void setOrdenNro(String ordenNro) {
        this.ordenNro = ordenNro;
    }

    public String getOrdenTipo() {
        return ordenTipo;
    }

    public void setOrdenTipo(String ordenTipo) {
        this.ordenTipo = ordenTipo;
    }

    public String getOrdenCia() {
        return ordenCia;
    }

    public void setOrdenCia(String ordenCia) {
        this.ordenCia = ordenCia;
    }

    public String getIniciadorNombre() {
        return iniciadorNombre;
    }

    public void setIniciadorNombre(String iniciadorNombre) {
        this.iniciadorNombre = iniciadorNombre;
    }

    public String getProveedorCod() {
        return proveedorCod;
    }

    public void setProveedorCod(String proveedorCod) {
        this.proveedorCod = proveedorCod;
    }

    public String getProveedorDesc() {
        return proveedorDesc;
    }

    public void setProveedorDesc(String proveedorDesc) {
        this.proveedorDesc = proveedorDesc;
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

    public String getCostoTotalFor() {
        return costoTotalFor;
    }

    public void setCostoTotalFor(String costoTotalFor) {
        this.costoTotalFor = costoTotalFor;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

}

