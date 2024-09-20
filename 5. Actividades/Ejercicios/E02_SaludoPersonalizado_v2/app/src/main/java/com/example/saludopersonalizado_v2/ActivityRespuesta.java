package com.example.saludopersonalizado_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityRespuesta extends AppCompatActivity {
    private TextView tvMensaje;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        tvMensaje = findViewById(R.id.tvMensaje);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        Intent intent = getIntent();

        String saludo = intent.getStringExtra("saludo");

        tvMensaje.setText(saludo);

        //tvMensaje.setText(intent.getStringExtra("saludo"));

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

