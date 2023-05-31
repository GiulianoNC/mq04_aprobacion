package com.quantum;

import static com.quantum.aprobaciones.LoginActivity.imagenGlobal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.aprobaciones.LoginActivity;
import com.quantum.aprobaciones.R;

public class Configuracion extends AppCompatActivity {

    private TextView mone, direccion, qtm, numero;

    public static String direc = null;
    public static String monedaGlobal = null;

    private ImageView quantumImangen, fabrilImagen,arberdiImagen,farmacityImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion2);
        mone= findViewById(R.id.mone);
        direccion= findViewById(R.id.direccion);

        SharedPreferences preferences = getSharedPreferences("dato", Context.MODE_PRIVATE);
        mone.setText(preferences.getString("moneda",""));
        direccion.setText(preferences.getString("direcciones",""));

        direc = direccion.getText().toString();
        monedaGlobal = mone.getText().toString();

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color

        qtm = findViewById(R.id.QTM);
        qtm.setText("QTM - APROBACIÓN" + "\n" + "ORDEN DE COMPRA" );



    }


    public void guardar (View view){
        SharedPreferences preferecias =  getSharedPreferences("dato",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();

        Obj_editor.putString("moneda", mone.getText().toString());
        Obj_editor.putString("direcciones", direccion.getText().toString());



        Obj_editor.commit();


        Intent siguiente = new Intent(Configuracion.this, LoginActivity.class);

        siguiente.putExtra("direcciones", direccion.getText().toString());
        siguiente.putExtra("moneda", mone.getText().toString());

        startActivity(siguiente);


    }
}