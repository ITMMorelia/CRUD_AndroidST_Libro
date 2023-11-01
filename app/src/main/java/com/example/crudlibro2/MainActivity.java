package com.example.crudlibro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context contexto;
    Button btnListar, btnRegistrar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        contexto = getApplicationContext();
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnBuscar = findViewById(R.id.btnbuscar);
        btnListar = findViewById(R.id.btnlistar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregistrar:
                Toast.makeText(contexto, "Registrar", Toast.LENGTH_LONG).show();
                Intent i = new Intent(contexto, GestionarLibroActivity.class);
                Bundle bolsa = new Bundle();
                bolsa.putInt("id",0);
                i.putExtras(bolsa);
                startActivity(i);
                break;
            case R.id.btnlistar:
                Toast.makeText(contexto, "Listar", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(contexto, ListadoLibrosActivity.class);
                startActivity(i2);
                break;
            case R.id.btnbuscar:
                Toast.makeText(contexto, "Buscar", Toast.LENGTH_LONG).show();
                Intent i3 = new Intent(contexto, BuscarLibroActivity.class);
                startActivity(i3);
                break;
        }
    }
}
