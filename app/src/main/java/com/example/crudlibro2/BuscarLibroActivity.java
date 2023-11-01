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

public class BuscarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo;
    Button btnbuscar;
    LibroBD libroBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libro);
        init();
    }

    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.bus_txttitulo);
        btnbuscar = findViewById(R.id.bus_btnbuscar);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.bus_btnbuscar){
            String titulo = txttitulo.getText().toString();
            Libro libro = buscarLibro(titulo);
            if (libro != null){
                Bundle bolsa = new Bundle();
                bolsa.putInt( "id", libro.getId());
                bolsa.putString( "titulo", libro.getTitulo());
                bolsa.putString( "subtitulo", libro.getSubtitulo());
                bolsa.putString( "autor", libro.getAutor());
                bolsa.putString( "isbn", libro.getIsbn());
                bolsa.putInt( "anio_publicacion", libro.getAnioPublicacion());
                bolsa.putDouble( "precio", libro.getPrecio());

                Intent i = new Intent( context, GestionarLibroActivity.class);
                i.putExtras( bolsa );
                startActivity(i);
            }else{
                Toast.makeText(context, "No existe el libro con ese titulo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Libro buscarLibro(String titulo) {
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = libroBD.elemento(titulo);

        return libro;
    }
}//