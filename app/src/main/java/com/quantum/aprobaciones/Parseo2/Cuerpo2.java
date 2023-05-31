package com.quantum.aprobaciones.Parseo2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuerpo2 {

    @SerializedName("MQ0401C_DATAREQ")
    @Expose
    private Mq0401cDatareq mq0401cDatareq;

    public Mq0401cDatareq getMq0401cDatareq() {
        return mq0401cDatareq;
    }

    public void setMq0401cDatareq(Mq0401cDatareq mq0401cDatareq) {
        this.mq0401cDatareq = mq0401cDatareq;
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


    public Cuerpo2(String username, String password, String ordenNro, String ordenTipo, String ordenCia) {
        this.username = username;
        this.password = password;
        this.ordenNro = ordenNro;
        this.ordenTipo = ordenTipo;
        this.ordenCia = ordenCia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

