package com.quantum;

import static com.quantum.Configuracion.direc;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.companiaGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.numeroGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.tipoGlobal;
import static com.quantum.aprobaciones.LoginActivity.contraseñaGlobal;
import static com.quantum.aprobaciones.LoginActivity.usuarioGlobal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.quantum.aprobaciones.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.aprobaciones.LaFabrilClases.FabrilActivity;
import com.quantum.aprobaciones.LaFabrilClases.FabrilActivity2;
import com.quantum.aprobaciones.Parseo3.Cuerpo3;
import com.quantum.aprobaciones.conectividad.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FabrilActivity3 extends AppCompatActivity {

    private TextView user, contraseña, intent, intent2, intent3 ,qtm;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabril3);
        user = (TextView) findViewById(R.id.user3);
        contraseña = (TextView) findViewById(R.id.contraseña3);

        intent = (TextView) findViewById(R.id.intent4);
        intent2 = (TextView) findViewById(R.id.intent5);
        intent3 = (TextView) findViewById(R.id.intent6);



        String user1 = getIntent().getStringExtra("user");
        user.setText(user1);

        String password1 = getIntent().getStringExtra("contraseña");
        contraseña.setText(password1);

        String dato = getIntent().getStringExtra("dato");
        intent.setText(dato);

        String dato2 = getIntent().getStringExtra("dato2");
        intent2.setText(dato2);

        String dato3 = getIntent().getStringExtra("dato3");
        intent3.setText(dato3);
        qtm = findViewById(R.id.QTM2);

        qtm.setText("QTM - APROBACIÓN" + "\n" + "ORDEN DE COMPRA" );



        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color
    }
    public void Observaciones(View v ){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        //autorizacion


        String userName =usuarioGlobal;
        String password =contraseñaGlobal;
        String base = userName + ":" + password;


        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        String dato4 = intent.getText().toString();
        String dato5 = intent2.getText().toString();
        String dato6 = intent3.getText().toString();


        Cuerpo3 loguerse2 = new Cuerpo3(usuarioGlobal,contraseñaGlobal, numeroGlobal, tipoGlobal, companiaGlobal);
        Call<Cuerpo3> call = conexion.getAprobacion(loguerse2);
        call.enqueue(new Callback<Cuerpo3>() {
            @Override
            public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {

                int statusCode = response.code();

                if(response.body() != null){

                    Cuerpo3 datareq = response.body();

                    if (statusCode == 200){

                        CharSequence[] opciones = { "CERRAR"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(FabrilActivity3.this);
                        builder.setTitle(" Enviado exitosamente");
                        builder.setItems(opciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (opciones[i].equals("CERRAR")){
                                    Intent e = new Intent(FabrilActivity3.this, FabrilActivity.class);
                                    startActivity(e);
                                }

                            }
                        });


                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                    else{
                        Toast.makeText(FabrilActivity3.this, "Error " , Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(FabrilActivity3.this, "Error " , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cuerpo3> call, Throwable t) {
                Toast.makeText(FabrilActivity3.this, "Error " , Toast.LENGTH_LONG).show();

            }
        });



    }

    public void Siguiente2 (View view){


        Intent e = new Intent(this, FabrilActivity2.class);


        startActivity(e);

    }

}