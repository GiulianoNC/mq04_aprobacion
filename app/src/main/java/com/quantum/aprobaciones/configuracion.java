package com.quantum.aprobaciones;

import static com.quantum.aprobaciones.MainActivity.contraseñaGlobal;
import static com.quantum.aprobaciones.MainActivity.usuarioGlobal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.aprobaciones.LaFabrilClases.FabrilActivity;
import com.quantum.aprobaciones.conectividad.Conexion;
import com.quantum.aprobaciones.parseoLaFabril.Cuerpo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class configuracion extends AppCompatActivity {

    private TextView mone, direccion;

    public static String direc = null;
    public static String monedaGlobal = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        mone= findViewById(R.id.mone);
        direccion= findViewById(R.id.direccion);

        SharedPreferences preferences = getSharedPreferences("dato", Context.MODE_PRIVATE);
        mone.setText(preferences.getString("moneda",""));
        direccion.setText(preferences.getString("direcciones",""));

        direc = direccion.getText().toString();
        monedaGlobal = mone.getText().toString();



    }


    public void guardar (View view){
        SharedPreferences preferecias =  getSharedPreferences("dato",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();

        Obj_editor.putString("moneda", mone.getText().toString());
        Obj_editor.putString("direcciones", direccion.getText().toString());


        Obj_editor.commit();


        Intent siguiente = new Intent(configuracion.this, MainActivity.class);

        siguiente.putExtra("direcciones", direccion.getText().toString());
        siguiente.putExtra("moneda", mone.getText().toString());

        startActivity(siguiente);



       /* String direccionG = direccion.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://qcsys.servehttp.com:925/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);




        Cuerpo loguerse = new Cuerpo("abenza", "abenza");


        Call<Cuerpo> call = conexion.getData(loguerse);
        call.enqueue(new Callback<Cuerpo>() {
            @Override
            public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                int statusCode = response.code();

                if (response.isSuccessful()){
                    Toast.makeText(configuracion.this, "Conexión exitosa", Toast.LENGTH_LONG).show();


                    if(statusCode <= 200){

                        Intent siguiente = new Intent(configuracion.this, FabrilActivity.class);



                        startActivity(siguiente);


                    }

                    else{
                        Toast.makeText(configuracion.this, "algo falló", Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<Cuerpo> call, Throwable t) {
                Toast.makeText(configuracion.this, "failed", Toast.LENGTH_LONG).show();
            }
        });
*/


    }


     public void prueba2(){

    }
}