package com.example.cambiodemoneda_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        String mensaje = intent.getStringExtra("mensaje");

        tvMensaje.setText(mensaje);

        //tvMensaje.setText(intent.getStringExtra("mensaje"));

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
