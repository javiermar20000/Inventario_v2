package com.example.inventario_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    private Button btnReg, btnBu, btnLi, btnPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Poner icono de aplicacion en actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        btnBu = (Button)findViewById(R.id.btnBu);
        btnLi = (Button)findViewById(R.id.btnLi);
        btnPref = (Button)findViewById(R.id.btnPref);

        btnBu.setOnClickListener(this);
        btnLi.setOnClickListener(this);
        btnPref.setOnClickListener(this);

        preferences = getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor =preferences.edit();

    }

    //Metodo para mostrar y ocultar el menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //Metodo para asignar las funciones correspondientes a las opciones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opSalir) {
            editor.putBoolean("sesion", false);
            editor.apply();
            Toast.makeText(InicioActivity.this,"La sesión fue cerrada exitosamente",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InicioActivity.this, LoginSession.class);
            startActivity(intent);

        } else if (id == R.id.opPref) {
            Intent intent = new Intent(InicioActivity.this, Preferencias.class);
            startActivity(intent);
        } else {
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnBu) {
            Intent in2 = new Intent(InicioActivity.this, MostrarProductos.class);
            startActivity(in2);

        } else if (id == R.id.btnLi) {
            Intent in3 = new Intent(InicioActivity.this, lista2.class);
            startActivity(in3);

        } else if (id == R.id.btnPref) {
            Intent in4 = new Intent(InicioActivity.this, TiendasConsulta.class);
            startActivity(in4);
        }}}