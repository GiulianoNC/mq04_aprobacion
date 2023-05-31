package com.quantum.aprobaciones.LaFabrilClases;

import static com.quantum.Configuracion.direc;
import static com.quantum.Configuracion.monedaGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.companiaGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.numeroGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.precioGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.proveedorGlobal;
import static com.quantum.aprobaciones.LaFabrilClases.FabrilActivity.tipoGlobal;
import static com.quantum.aprobaciones.LoginActivity.contraseñaGlobal;
import static com.quantum.aprobaciones.LoginActivity.usuarioGlobal;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.FabrilActivity3;
import com.quantum.aprobaciones.Parseo2.Cuerpo2;
import com.quantum.aprobaciones.Parseo2.Mq0401cDatareq;
import com.quantum.aprobaciones.Parseo2.Rowset;
import com.quantum.aprobaciones.Parseo3.Cuerpo3;
import com.quantum.aprobaciones.R;
import com.quantum.aprobaciones.conectividad.Conexion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FabrilActivity2 extends AppCompatActivity {

    private TextView user2, contraseña2, intent, intent2, intent3, prueba, general, montoMoneda,qtm,numero ,numero2;



    private ProgressBar progresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabril2);

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        progresBar = findViewById(R.id.progressBar2);
        progresBar.setVisibility(View.INVISIBLE);

        //contra y user

        user2 = (TextView) findViewById(R.id.user2);
        String user1 = getIntent().getStringExtra("user");
        user2.setText(user1);




        contraseña2 = (TextView) findViewById(R.id.contraseña2);
        String password1 = getIntent().getStringExtra("contraseña");
        contraseña2.setText(password1);

        //datos
        general = (TextView) findViewById(R.id.generalSuplies);
        montoMoneda = (TextView) findViewById(R.id.monto);




        //parametros
        intent = (TextView) findViewById(R.id.intent);
        intent2 = (TextView) findViewById(R.id.intent2);
        intent3 = (TextView) findViewById(R.id.intent3);


        numero = (TextView) findViewById(R.id.Numero);
        numero2 = (TextView) findViewById(R.id.numero2);


        prueba = (TextView) findViewById(R.id.prueba);

        qtm = findViewById(R.id.QTM);
        qtm.setText("QTM - APROBACIÓN" + "\n" + "ORDEN DE COMPRA" );

        numero2.setText(precioGlobal);
        Busqueda();
        conversor();

    }
    public void Busqueda( ){



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        //autorizacion



        String userName ="basilisi";
        String password ="basilisi";
        String base = userName + ":" + password;



        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        String dato = getIntent().getStringExtra("dato");
        intent.setText(dato);

        String dato2 = getIntent().getStringExtra("dato2");
        intent2.setText(dato2);

        String dato3 = getIntent().getStringExtra("dato3");
        intent3.setText(dato3);


        String proveedor = getIntent().getStringExtra("dato4");
        String precio = getIntent().getStringExtra("dato5");
        String moneda = getIntent().getStringExtra("dato6");
        String comprador = getIntent().getStringExtra("dato7");

        numero.setText(numeroGlobal);

        general.setText(proveedorGlobal);


        numero2.setText(precioGlobal);


        Cuerpo2 loguerse = new Cuerpo2(usuarioGlobal,contraseñaGlobal, numeroGlobal, tipoGlobal, companiaGlobal);


        Call<Cuerpo2> call = conexion.getParama(loguerse);
        call.enqueue(new Callback<Cuerpo2>() {
            @Override
            public void onResponse(Call<Cuerpo2> call, Response<Cuerpo2> response) {
                 int statusCode = response.code();

               if(statusCode == 200) {
                Toast.makeText(FabrilActivity2.this, "Éxito " , Toast.LENGTH_LONG).show();


                    Mq0401cDatareq contactList =  response.body().getMq0401cDatareq();



                    for(int e = 0; e<contactList.getRowset().size(); e++) {


                        List<Rowset> rowset1 = (List<Rowset>) contactList.getRowset();
                        String LINEA = rowset1.get(e).getOrdenLinea();
                        LINEA += "";
                        String FECHASOLITUD = rowset1.get(e).getFechaSolicitud();
                        FECHASOLITUD+= "";
                        String FECHAPROMETIDA = rowset1.get(e).getFechaPrometida();
                        FECHAPROMETIDA+= "";
                        String DES1Y2 = rowset1.get(e).getDesc1()+ " " + rowset1.get(e).getDesc2();
                        DES1Y2+= "";
                        String CANTIDAD = rowset1.get(e).getCantidad();
                        CANTIDAD+= "";
                        String UM = rowset1.get(e).getUm();
                        UM += "";
                        String MONEDA = rowset1.get(e).getMonedaTran();
                        MONEDA+= "";



                        String MONEDA1 ="";
                        String monedaLocal = "ARS";
                        /*if(rowset1.get(e).getMonedaTran().equals(monedaLocal)){
                            MONEDA1 =rowset1.get(e).getCostoTotalDom() ;

                        }
                        else{
                            MONEDA1 =(rowset1.get(e).getCostoTotalFor());

                        }*/

                        MONEDA1 =rowset1.get(e).getCostoUnitDom() ;


                        //float value=Integer.parseInt(MONEDA1);

                        //float valorFinal = value /100;

                        String content ="";
                        String content1 ="";


                        content1 += "Fecha Prometida : "+ FECHAPROMETIDA  +"\n" + "Comprador : "+ comprador  +"\n" +
                                DES1Y2 + "\n" + "Cant: " + CANTIDAD + " " + UM + "\n" + "Precio Unit: " + MONEDA1 +" "+ MONEDA +"\n \n \n";

                        prueba.append(content1);



                    }




                }

            }
            @Override
            public void onFailure(Call<Cuerpo2> call, Throwable t) {
                Toast.makeText(FabrilActivity2.this, "error " , Toast.LENGTH_LONG).show();



            }
        });
    }
    public void Aprobar(View v ){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);

        //autorizacion

        String user3 = user2.getText().toString();
        String contraseña3 = contraseña2.getText().toString();

        String userName =usuarioGlobal;
        String password =contraseñaGlobal;
        String base = userName + ":" + password;



        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);


        String dato = intent.getText().toString();
        String dato2 = intent2.getText().toString();
        String dato3 = intent3.getText().toString();


        Cuerpo3 loguerse2 = new Cuerpo3(usuarioGlobal,contraseñaGlobal, numeroGlobal, tipoGlobal, companiaGlobal);

        Call<Cuerpo3> call = conexion.getAprobacion(loguerse2);
        call.enqueue(new Callback<Cuerpo3>() {
            @Override
            public void onResponse(Call<Cuerpo3> call, Response<Cuerpo3> response) {

                int statusCode = response.code();

                if(response.body() != null){

                    Cuerpo3 datareq = response.body();



                    if (statusCode == 200){

                        CharSequence[] opciones = { "cerrar"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(FabrilActivity2.this);
                        builder.setTitle(" Orden Aprobada");
                      //  builder.setPositiveButton("cerrar", null);

                        builder.setItems(opciones, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (opciones[i].equals("cerrar")){
                                    Intent e = new Intent(FabrilActivity2.this, FabrilActivity.class);
                                    startActivity(e);
                                }

                            }
                        });


                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                    else{
                        Toast.makeText(FabrilActivity2.this, "Error " , Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(FabrilActivity2.this, "Error " , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cuerpo3> call, Throwable t) {
                Toast.makeText(FabrilActivity2.this, "Error " , Toast.LENGTH_LONG).show();

            }
        });

    }

    public void Rechazar(View v ){

        Intent e = new Intent(this, FabrilActivity3.class);
        startActivity(e);


       /* e.putExtra("dato", intent.getText().toString());
        e.putExtra("dato2", intent2.getText().toString());
        e.putExtra("dato3", intent3.getText());*/


    }

    public void conversor ( ){


        montoMoneda.setText(precioGlobal + " " + monedaGlobal);

    }

    public void Siguiente2 (View view){

        Intent e = new Intent(this, FabrilActivity.class);

        startActivity(e);

    }

}