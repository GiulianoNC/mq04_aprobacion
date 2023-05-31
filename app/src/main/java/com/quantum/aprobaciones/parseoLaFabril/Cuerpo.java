package com.quantum.aprobaciones.parseoLaFabril;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Cuerpo {
    @SerializedName("MQ0401P_FORMREQ_1")
    @Expose
    private List<Mq0401pFormreq1> mq0401pFormreq1 = null;

    public List<Mq0401pFormreq1> getMq0401pFormreq1() {
        return mq0401pFormreq1;
    }

    public void setMq0401pFormreq1(List<Mq0401pFormreq1> mq0401pFormreq1) {
        this.mq0401pFormreq1 = mq0401pFormreq1;
    }

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;





    public Cuerpo(String username, String password) {
        this.username = username;
        this.password = password;
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

