package com.example.e02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        //recuperar el dato enviado desde Acivity1 ned. el bundle del intent
        Intent intent = getIntent();

        //extraer el bundle
        Bundle bundle = intent.getExtras();

        //unica sentencia
        //String string = getIntent().getExtras().getString("mensaje");


        //recuperar el dato con el metodo adecuado
        String mensajeRecibido = bundle.getString("mensaje");
        TextView tvMensajeRecibido = findViewById(R.id.tvMensajeRecibidoAct4);
        tvMensajeRecibido.setText(mensajeRecibido);
    }
}