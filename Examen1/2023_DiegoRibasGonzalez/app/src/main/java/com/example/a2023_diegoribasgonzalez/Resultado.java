package com.example.a2023_diegoribasgonzalez;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado  extends AppCompatActivity {
    TextView tvResultado, tvTipoResultado;
    ImageView ivResultado;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvResultado = findViewById(R.id.tvResultado);
        tvTipoResultado = findViewById(R.id.tvTipoResultado);
        ivResultado = findViewById(R.id.ivResultado);

        //Recuperar dato enviado desde MainActivity mediante el Intent
        Intent intent = getIntent();

        //Extraer el dato
        double imc = intent.getDoubleExtra("imc", 0);

        //Visualizar dato en TextView
        tvResultado.setText("IMC = " + imc);

        if(imc < 25){
            ivResultado.setImageResource(R.drawable.imc_correcto);
            tvTipoResultado.setText("Normal");
        }
        else if (imc < 30){
            ivResultado.setImageResource(R.drawable.imc_sobrepeso);
            tvTipoResultado.setText("Sobrepeso");
        }
        else {
            ivResultado.setImageResource(R.drawable.imc_obesidad);
            tvTipoResultado.setText("Obesidad");
        }
    }
}
