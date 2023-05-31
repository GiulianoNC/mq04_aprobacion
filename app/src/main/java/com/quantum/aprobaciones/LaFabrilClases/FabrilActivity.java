package com.quantum.aprobaciones.LaFabrilClases;


import static com.quantum.Configuracion.direc;
import static com.quantum.Configuracion.monedaGlobal;
import static com.quantum.aprobaciones.LoginActivity.contraseñaGlobal;
import static com.quantum.aprobaciones.LoginActivity.usuarioGlobal;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.aprobaciones.LoginActivity;
import com.quantum.aprobaciones.MainActivity;
import com.quantum.aprobaciones.R;
import com.quantum.aprobaciones.adaptador.AdapterDatos;
import com.quantum.aprobaciones.conectividad.Conexion;
import com.quantum.aprobaciones.parseoLaFabril.Cuerpo;
import com.quantum.aprobaciones.parseoLaFabril.Mq0401pFormreq1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FabrilActivity extends AppCompatActivity {

    private TextView compania,tipo, numero, fecha, comprador, proveedor, moneda, precioTotal,
            prueba, qtm, coment;

    private ProgressBar progresBar;

    Mq0401pFormreq1 reqRowset;

    ArrayList<String> listDatos;

    RecyclerView recycler;

    private ImageView quantumImangen, fabrilImagen;

    public static String companiaGlobal = null;
    public static String tipoGlobal = null;
    public static String numeroGlobal = null;
    public static String proveedorGlobal = null;
    public static String precioGlobal = null;
    public static String moneda2Global = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabril);

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        recycler= (RecyclerView) findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        prueba = findViewById(R.id.PRUEBA);


        progresBar = findViewById(R.id.progressBar);
        progresBar.setVisibility(View.INVISIBLE);
        compania = findViewById(R.id.compania);
        tipo = findViewById(R.id.tipo);
        numero = findViewById(R.id.numero);
        fecha = findViewById(R.id.fecha);
        comprador = findViewById(R.id.comprador);
        proveedor = findViewById(R.id.proveedor);
        moneda = findViewById(R.id.moneda);
        precioTotal = findViewById(R.id.precioTotal);


        qtm = findViewById(R.id.QTM);
        qtm.setText("QTM - APROBACIÓN" + "\n" + "ORDEN DE COMPRA" );

        Prueba();

        quantumImangen = (ImageView) findViewById(R.id.imageViewC);
    }


    public void Prueba( ){




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(direc)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Conexion conexion = retrofit.create(Conexion.class);


        Cuerpo login = new Cuerpo(usuarioGlobal, contraseñaGlobal);
        Call<Cuerpo> call = conexion.getData(login);
        call.enqueue(new Callback<Cuerpo>() {
            @Override
            public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                if(response.body() != null) {
                    Cuerpo datareq = response.body();

                    List<Mq0401pFormreq1> rowset = datareq.getMq0401pFormreq1();
                    listDatos =new ArrayList<>();

                    for(Mq0401pFormreq1 d : rowset){


                        if (monedaGlobal.equals(d.getMonedaTran())){

                        String content ="";
                        String content1 = d.getOrdenNro();


                        content += " " +d.getProveedorDesc() + " | " + "\n"  ;


                        String mon =getIntent().getStringExtra("moneda");

                        String monedaFinal ="";

                        if(d.getCostoTotalDom().equals("0")){
                            monedaFinal +=  d.getCostoTotalDom() ;
                        }
                        else{
                            monedaFinal += d.getCostoTotalFor() ;
                        }


                        content += d.getCostoTotalDom() +" " + d.getMonedaTran()  + "\n \n" ;

                        prueba.append(content );

                        listDatos.add( content1 );


                        AdapterDatos adapter = new AdapterDatos(listDatos);
                        recycler.setAdapter(adapter);

                        adapter.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                progresBar.setVisibility(View.VISIBLE);


                                // Toast.makeText(getApplicationContext(),"Ud ha seleccionado la órden de compra N°: " + listDatos.get(recycler.getChildAdapterPosition(view)), Toast.LENGTH_SHORT).show();

                                ;

                                Intent e = new Intent(FabrilActivity.this, FabrilActivity2.class);
                                e.putExtra("dato", compania.getText().toString());
                                e.putExtra("dato2", tipo.getText().toString());
                                e.putExtra("dato3", numero.getText());
                                e.putExtra("dato4", proveedor.getText());
                                e.putExtra("dato5", precioTotal.getText());
                                e.putExtra("dato6", moneda.getText());
                                e.putExtra("dato7", comprador.getText());


                                startActivity(e);

                                numero.setText(listDatos.get(recycler.getChildAdapterPosition(view)));

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(direc)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                Conexion conexion = retrofit.create(Conexion.class);



                                Cuerpo login = new Cuerpo(usuarioGlobal, contraseñaGlobal);
                                Call<Cuerpo> call = conexion.getData(login);
                                call.enqueue(new Callback<Cuerpo>() {
                                    @Override
                                    public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                                        if(response.body() != null) {
                                            Cuerpo datareq = response.body();


                                            List<Mq0401pFormreq1> rowset = datareq.getMq0401pFormreq1();

                                            for(Mq0401pFormreq1 d : rowset){

                                                String numeroOrden2 = numero.getText().toString();

                                                if (numeroOrden2 != "0" ){
                                                    if(d.getOrdenNro().equals(numeroOrden2)){
                                                        { reqRowset = d;


                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    compania.setText(reqRowset.getOrdenCia()) ;
                                                                    tipo.setText( reqRowset.getOrdenTipo() );
                                                                    numero.setText( reqRowset.getOrdenNro()  );
                                                                    fecha.setText( reqRowset.getFechaOrden() );
                                                                    comprador.setText( reqRowset.getIniciadorNombre() );
                                                                    proveedor.setText(reqRowset.getProveedorDesc()) ;
                                                                    moneda.setText(reqRowset.getMonedaTran()) ;

                                                                    String mon =getIntent().getStringExtra("moneda");

                                                                    //String monedaLocal = "ARS";
                                                                    /*if(reqRowset.getMonedaTran().equals(mon)){
                                                                        precioTotal.setText(reqRowset.getCostoTotalDom() );
                                                                    }
                                                                    else{
                                                                        precioTotal.setText(reqRowset.getCostoTotalFor());
                                                                    }*/
                                                                    precioTotal.setText(reqRowset.getCostoTotalDom() );


                                                                    companiaGlobal = compania.getText().toString();
                                                                    tipoGlobal = tipo.getText().toString();
                                                                    numeroGlobal = numero.getText().toString();
                                                                    precioGlobal = precioTotal.getText().toString();
                                                                    proveedorGlobal = proveedor.getText().toString();
                                                                    moneda2Global = moneda.getText().toString();


                                                                }




                                                            });


                                                        }




                                                    }



                                                }

                                              Intent e = new Intent(FabrilActivity.this, FabrilActivity2.class);
                                                e.putExtra("dato", compania.getText().toString());
                                                e.putExtra("dato2", tipo.getText().toString());
                                                e.putExtra("dato3", numero.getText());
                                                e.putExtra("dato4", proveedor.getText());
                                                e.putExtra("dato5", precioTotal.getText());
                                                e.putExtra("dato6", moneda.getText());
                                                e.putExtra("dato7", comprador.getText());



                                                startActivity(e);




                                            }



                                        }

                                    }
                                    @Override
                                    public void onFailure(Call<Cuerpo> call, Throwable t) {
                                        Toast.makeText(FabrilActivity.this, "No existe el numero de orden indicado", Toast.LENGTH_LONG).show();

                                    }
                                });








                                // compania.setText("");
                                tipo.setText("");
                                fecha.setText("");
                                comprador.setText("");
                                proveedor.setText("") ;
                                moneda.setText("") ;
                                precioTotal.setText("");


                            }
                        });

                        String numeroOrden2 = numero.getText().toString();


                        if (numeroOrden2 != "0" ){
                            if(d.getOrdenNro().equals(numeroOrden2)){
                                { reqRowset = d;


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            compania.setText(reqRowset.getOrdenCia()) ;
                                            tipo.setText( reqRowset.getOrdenTipo() );
                                            numero.setText( reqRowset.getOrdenNro()  );
                                            fecha.setText( reqRowset.getFechaOrden() );
                                            comprador.setText( reqRowset.getIniciadorNombre() );
                                            proveedor.setText(reqRowset.getProveedorDesc()) ;
                                            moneda.setText(reqRowset.getMonedaTran()) ;

                                            String mon =getIntent().getStringExtra("moneda");

                                            if(reqRowset.getMonedaTran().equals(mon)){
                                                precioTotal.setText(reqRowset.getCostoTotalDom() );
                                            }
                                            else{
                                                precioTotal.setText(reqRowset.getCostoTotalFor());
                                            }
                                        }




                                    });


                                }



                            }



                        }

                    }

                    }

                }

            }
            @Override
            public void onFailure(Call<Cuerpo> call, Throwable t) {
                Toast.makeText(FabrilActivity.this, "No existe el numero de orden indicado", Toast.LENGTH_LONG).show();

            }
        });


    }



    public void Siguiente (View view){

        progresBar.setVisibility(View.VISIBLE);

        Intent e = new Intent(this, LoginActivity.class);


        startActivity(e);
    }

    public void Salir(View v){
            new AlertDialog.Builder(this)
                    //.setIcon(R.drawable.alacran)
                    .setTitle("¿Realmente desea cerrar la aplicación?")
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                            finishAffinity();;
                        }
                    }).show();
        }
}