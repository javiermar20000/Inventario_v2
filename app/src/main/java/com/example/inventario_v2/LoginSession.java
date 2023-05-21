package com.example.inventario_v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import com.google.android.material.button.MaterialButton;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSession extends AppCompatActivity {

    CheckBox checkGuardarSesion;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";


    private void inicializarElementos() {
        preferences =this.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();
        checkGuardarSesion =findViewById(R.id.Iniciar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_session);

        //Poner icono de aplicacion en actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        inicializarElementos();
        revisarSesion();

        TextView NombreUsuario =(TextView) findViewById(R.id.NombreUsuario);
        TextView Contrasena =(TextView) findViewById(R.id.Contrasena);

        MaterialButton Boton = (MaterialButton) findViewById(R.id.Boton);
        MaterialButton Boton2 = (MaterialButton) findViewById(R.id.Boton2);

        if(revisarSesion()) {
            startActivity(new Intent(this, InicioActivity.class));
            Toast.makeText(LoginSession.this,"Sesion Guardada, iniciando...",Toast.LENGTH_SHORT).show();
        } else {

        }

        //admin and admin password
        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NombreUsuario.getText().toString().equals("") || Contrasena.getText().toString().equals("")){
                    //Login correct
                    Toast.makeText(LoginSession.this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
                } else {
                    guardarSesion(checkGuardarSesion.isChecked());
                    Toast.makeText(LoginSession.this,"Inicio de sesion EXITOSO!!!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginSession.this,InicioActivity.class));
                }
            }
        });
        Boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NombreUsuario.getText().toString().equals("") || Contrasena.getText().toString().equals("")){
                    Toast.makeText(LoginSession.this, "Llene todos los campos para el registro", Toast.LENGTH_SHORT).show();
                } else {
                    guardarSesion(checkGuardarSesion.isChecked());
                    Toast.makeText(LoginSession.this, "Registro Exitoso, iniciando sesión",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginSession.this,InicioActivity.class));
                }
            }

        });
    }
    private void guardarSesion(boolean checked) {
        editor.putBoolean(llave, checked);
        //editor.putString("nombre","jose");
        editor.apply();

    }
    private boolean revisarSesion() {
        return this.preferences.getBoolean(llave, false);
    }





}