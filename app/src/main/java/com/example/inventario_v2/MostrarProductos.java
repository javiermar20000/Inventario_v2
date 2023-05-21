package com.example.inventario_v2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class MostrarProductos extends AppCompatActivity {
    EditText name, contact; //falta usar ID

    private Spinner spinner;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        //Poner icono de aplicacion en actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        spinner = (Spinner)findViewById(R.id.spinner);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        String [] opciones = {"Tipo", "Legumbres" , "Verduras", "Carnes", "Frutas", "Cereales", "Lacteos", "Bebestibles" };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = spinner.getSelectedItem().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MostrarProductos.this, "Nuevo Producto Ingresado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MostrarProductos.this, "Producto ya existe", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = spinner.getSelectedItem().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MostrarProductos.this, "Producto Editado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MostrarProductos.this, "Producto NO existe", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MostrarProductos.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MostrarProductos.this, "Producto NO existe", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MostrarProductos.this, "No existen productos", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nombre P: "+res.getString(0)+"\n");
                    //buffer.append("Cantidad: "+res.getString(1)+"\n");
                    //buffer.append("Tipo P: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarProductos.this);
                builder.setCancelable(true);
                builder.setTitle("Lista de Productos");
                builder.setMessage(buffer.toString());
                builder.setNegativeButton("Regresar         ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("      lista completa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor res = DB.getdata();
                        if(res.getCount()==0){
                            Toast.makeText(MostrarProductos.this, "No existen productos", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while(res.moveToNext()){
                            buffer.append("Nombre P: "+res.getString(0)+"\n");
                            buffer.append("Cantidad: "+res.getString(1)+"\n");
                            buffer.append("Tipo P: "+res.getString(2)+"\n\n");
                        }
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MostrarProductos.this);
                        builder1.setCancelable(true);
                        builder1.setTitle("Lista Completa");
                        builder1.setMessage(buffer.toString());
                        builder1.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public  void onClick(DialogInterface dialog, int which){
                            }
                                });
                        builder1.show();
                        //startActivity(new Intent(MostrarProductos.this,ListaActivity.class));
                    }
                });
                builder.show();
            }        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.desplegable2, menu);
        return true;
    }


    //Metodo para asignar las funciones correspondientes a las opciones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opRegresar) {
            Intent intent = new Intent(MostrarProductos.this, InicioActivity.class);
            startActivity(intent);

        } else if(id == R.id.deleteAll) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MostrarProductos.this);
            builder.setCancelable(true);
            builder.setTitle("Borrar lista");
            builder.setMessage("¿Esta seguro de querer eliminar TODA la lista de productos?");
            builder.setNegativeButton("NO         ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("      Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MostrarProductos.this,"Lista Borrada",Toast.LENGTH_SHORT).show();
                    DB.deleteALLdata();
                }
                    }
            );
            builder.show();

        } else if(id == R.id.opBuscar) {
            Intent intent = new Intent(MostrarProductos.this, ListaActivity.class);
            startActivity(intent);

        }else {
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}