package com.example.e03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //recuperar el dato enviado desde Activity1 med. el intent
        Intent intent = getIntent();

        //extraer el dato
        String datoRecibido = intent.getStringExtra("mensaje");

        //Visualizar el dato en la TextView
        TextView tvRecepcion = findViewById(R.id.tvMensajeRecibido);
        tvRecepcion.setText(datoRecibido);
    }
}