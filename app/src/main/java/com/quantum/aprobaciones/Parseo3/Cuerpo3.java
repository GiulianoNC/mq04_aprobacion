package com.quantum.aprobaciones.Parseo3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo3 {

    @SerializedName("Numero orden")
    @Expose
    private Integer nMeroOrden;
    @SerializedName("Tp ord")
    @Expose
    private String tpOrd;
    @SerializedName("Compania orden")
    @Expose
    private String cAOrden;

    public Integer getNMeroOrden() {
        return nMeroOrden;
    }

    public void setNMeroOrden(Integer nMeroOrden) {
        this.nMeroOrden = nMeroOrden;
    }

    public String getTpOrd() {
        return tpOrd;
    }

    public void setTpOrd(String tpOrd) {
        this.tpOrd = tpOrd;
    }

    public String getCAOrden() {
        return cAOrden;
    }

    public void setCAOrden(String cAOrden) {
        this.cAOrden = cAOrden;
    }

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("ORDEN_NRO")
    @Expose
    private String ordenNro;
    @SerializedName("ORDEN_TIPO")
    @Expose
    private String ordenTipo;
    @SerializedName("ORDEN_CIA")
    @Expose
    private String ordenCia;


    public Cuerpo3(String username, String password, String ordenNro, String ordenTipo, String ordenCia) {
        this.username = username;
        this.password = password;
        this.ordenNro = ordenNro;
        this.ordenTipo = ordenTipo;
        this.ordenCia = ordenCia;
    }
}
