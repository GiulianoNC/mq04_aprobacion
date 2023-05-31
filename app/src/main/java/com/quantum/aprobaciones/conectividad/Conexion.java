package com.quantum.aprobaciones.conectividad;

import com.quantum.aprobaciones.Parseo2.Cuerpo2;
import com.quantum.aprobaciones.Parseo3.Cuerpo3;
import com.quantum.aprobaciones.parseoLaFabril.Cuerpo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Conexion {

    @POST("jderest/v3/orchestrator/MQ0401P_ORCH")
    Call<Cuerpo> getData(@Body Cuerpo users );


    //para cuando selecciono los parametros
    @POST("jderest/v3/orchestrator/MQ0401C_ORCH")
    Call <Cuerpo2> getParama( @Body Cuerpo2 user);


    //aprobacion

    @POST("jderest/v3/orchestrator/MQ0401A_ORCH")
    Call <Cuerpo3> getAprobacion(@Body Cuerpo3 user);

    //rechazo

    @POST("jderest/v3/orchestrator/MQ0401R_ORCH")
    Call <Cuerpo3> getRechazo(@Body Cuerpo3 user);

}
