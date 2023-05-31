package com.quantum.aprobaciones;

import static com.quantum.Configuracion.direc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.Configuracion;
import com.quantum.aprobaciones.LaFabrilClases.FabrilActivity;
import com.quantum.aprobaciones.conectividad.Conexion;
import com.quantum.aprobaciones.parseoLaFabril.Cuerpo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView user, password, informeConexion, urls, moneda, urls2, moneda2, qtm,nm,posicion;


    public static String usuarioGlobal = null;
    public static String contraseñaGlobal = null;
    public static String imagenGlobal= null;

    private ImageView imagen ;
    private Uri mImageUri;
    PreferenceManager preferenceManager;

    Switch switcher;
    boolean nightMODE;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
          // We don’t have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //statusBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.rgb(102,45,145));  //Define color
        //la fabril
        user = findViewById(R.id.Usuario);
        password = findViewById(R.id.contras);

        qtm = findViewById(R.id.QTM);
        qtm = findViewById(R.id.QTMtitulo);

        qtm.setText("QTM - APROBACIÓN" + "\n" + "ORDEN DE COMPRA" );


        //configuracion traer

        moneda = findViewById(R.id.mon);
        urls = findViewById(R.id.dir);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        user.setText(preferences.getString("usuario",""));
        password.setText(preferences.getString("password",""));


        String direccion = getIntent().getStringExtra("direcciones");
        urls.setText(direccion);

        String mone = getIntent().getStringExtra("moneda");
        moneda.setText(mone);


        moneda2 = findViewById(R.id.mon);
        urls2 = findViewById(R.id.dir);


        guardar();
        traer();


        //imagenes

        imagen = findViewById(R.id.image);
        verifyStoragePermissions(this);

        //Esto es el Day/Night Mode
        //Uso el SharedPreference para guardar el modo cuando salgo de la pagina
        switcher = findViewById(R.id.btnToggleDark);
        sharedPreferences = getSharedPreferences("MODE",Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night",false); //El modo luz es el default

        if (nightMODE){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();
            }
        });

    }



    public void cargar (View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);

        }
        else{
            Toast.makeText(this, "could not captured", Toast.LENGTH_SHORT).show();
        }
    }

    public void traer(){
        String direccion = getIntent().getStringExtra("direcciones");
        urls.setText(direccion);

        String mone = getIntent().getStringExtra("moneda");
        moneda.setText(mone);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //acciones de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_privacidad:
                Toast.makeText(this, "Politicas de privacidad", Toast.LENGTH_SHORT).show();

                String url = "https://quantumconsulting.com.ar/politicas-de-privacidad/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.action_moneda:
                Intent siguiente = new Intent(LoginActivity.this, Configuracion.class);


                startActivity(siguiente);
                break;


        }
        return super.onOptionsItemSelected(item);
    }



    //metodo para guardar

    public void guardar (){
        SharedPreferences preferecias =  getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();
        Obj_editor.putString("usuario", user.getText().toString());
        Obj_editor.putString("password", password.getText().toString());


        Obj_editor.commit();


    }



    public void Logearse(View view ) {




        String user2 = user.getText().toString();
        String contraseña2 = password.getText().toString();
        String direccion = urls.getText().toString();

        if (direccion.length() == 0)  {

            Intent siguiente = new Intent(LoginActivity.this, Configuracion.class);

            startActivity(siguiente);
        }else{


            if (user2.length() == 0 && contraseña2.length() == 0) {
                Toast.makeText(this, "Debes ingresar un usuario y contraseña", Toast.LENGTH_LONG).show();
            }
            if (user2.length() != 0 && contraseña2.length() != 0) {

                Toast.makeText(this, "Procesando", Toast.LENGTH_LONG).show();


                usuarioGlobal = user.getText().toString();
                contraseñaGlobal = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(direc)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Conexion conexion = retrofit.create(Conexion.class);


                Cuerpo loguerse = new Cuerpo(user2, contraseña2);


                Call<Cuerpo> call = conexion.getData(loguerse);
                call.enqueue(new Callback<Cuerpo>() {
                    @Override
                    public void onResponse(Call<Cuerpo> call, Response<Cuerpo> response) {
                        int statusCode = response.code();

                        if (response.isSuccessful()){
                            //informeConexion.setText("code: " + response.code() + " Conexión exitosa " + "\n");
                            Toast.makeText(LoginActivity.this, "Conexión exitosa", Toast.LENGTH_LONG).show();


                            if(statusCode <= 200){

                                Intent siguiente = new Intent(LoginActivity.this, FabrilActivity.class);
                                siguiente.putExtra("user", user.getText().toString());
                                siguiente.putExtra("contraseña", password.getText().toString());
                                siguiente.putExtra("URL", urls.getText().toString());
                              siguiente.putExtra("moneda", moneda.getText().toString());



                                startActivity(siguiente);


                            }

                            else{
                                Toast.makeText(LoginActivity.this, "algo falló "+ "\n" + "code: " +response.code(), Toast.LENGTH_LONG).show();

                            }
                        }
                        else {
                            if (statusCode == 403) {
                                Toast.makeText(LoginActivity.this, "Usuario/Contraseña Incorrecto", Toast.LENGTH_LONG).show();
                            }
                            if (statusCode == 500) {
                                Toast.makeText(LoginActivity.this, "Error en el servidor", Toast.LENGTH_LONG).show();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Cuerpo> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "algo falló", Toast.LENGTH_LONG).show();
                        // informeConexion.setText(t.getMessage());
                    }
                });



            }
        }

        SharedPreferences preferecias =  getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferecias.edit();
        Obj_editor.putString("usuario", user.getText().toString());
        Obj_editor.putString("password", password.getText().toString());

        Obj_editor.commit();


    }
}