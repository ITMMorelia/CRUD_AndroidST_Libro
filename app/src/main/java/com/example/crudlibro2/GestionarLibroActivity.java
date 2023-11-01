package com.example.crudlibro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudlibro2.controladores.LibroBD;
import com.example.crudlibro2.modelos.Libro;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo, txtsubtitulo, txtisbn, txtautor, txtaniopublicacion, txtprecio;
    int id;
    LibroBD libroBD;
    Button btnguardar, btnactualizar, btnborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_libro);
        init();
    }

    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.ges_txttitulo);
        txtsubtitulo = findViewById(R.id.ges_txtsubtitulo);
        txtautor = findViewById(R.id.ges_txtautor);
        txtisbn= findViewById(R.id.ges_txtisbn);
        txtaniopublicacion = findViewById(R.id.ges_txtaniopublicacion);
        txtprecio = findViewById(R.id.ges_txtprecio);

        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");
        if (id != 0){
            txttitulo.setText( bolsa.getString("titulo"));
            txtsubtitulo.setText( bolsa.getString("subtitulo"));
            txtautor.setText( bolsa.getString("autor"));
            txtisbn.setText( bolsa.getString("isbn"));
            txtaniopublicacion.setText( bolsa.getInt("anio_publicacion") + "");
            txtprecio.setText( bolsa.getDouble("precio") + "");
            btnguardar.setEnabled(false);
        }else {
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id = 0;
        txttitulo.setText("");
        txtsubtitulo.setText("");
        txtautor.setText("");
        txtisbn.setText("");
        txtaniopublicacion.setText("");
        txtprecio.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String t = txttitulo.getText().toString();
        String s = txtsubtitulo.getText().toString();
        String a = txtautor.getText().toString();
        String i = txtisbn.getText().toString();
        String anio = txtaniopublicacion.getText().toString();
        String precio = txtprecio.getText().toString();

        libro.setId(id);
        libro.setTitulo(t);
        libro.setSubtitulo(s);
        libro.setAutor(a);
        libro.setIsbn(i);
        libro.setAnioPublicacion(Integer.parseInt(anio));
        libro.setPrecio(Double.parseDouble(precio));

        return libro;
    }

    private void guardar(){
        libroBD = new LibroBD(context, "LibrosBD.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id==0){
            libroBD.agregar(libro);
            Toast.makeText(context,"Guardado Nievo OK", Toast.LENGTH_LONG).show();
        }else {
            libroBD.actualizar(id, libro);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context,"Actualizado Existente OK", Toast.LENGTH_LONG).show();
        }
    }

    private void borrar(){
        libroBD = new LibroBD(context, "LibrosBD.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id==0){
            Toast.makeText(context,"No es posible borrar", Toast.LENGTH_LONG).show();
        }else {
            libroBD.borrar(id);
            limpiarCampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context,"Se borro el registro", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ges_btnguardar:
                guardar();
                break;
            case R.id.ges_btnactualizar:
                guardar();
                break;
            case R.id.ges_btnborrar:
                borrar();
                break;
        }
    }
}