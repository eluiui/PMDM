package com.example.a2023_diegoribasgonzalez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Calcular extends AppCompatActivity {
    private EditText etAltura, etPeso;
    private Button btnCalcular, btnFinalizar;
    private ToggleButton tbSistemaMedida;
    private boolean sistemaMetrico = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        etAltura = findViewById(R.id.etAltura);
        etPeso = findViewById(R.id.etPeso);

        tbSistemaMedida = findViewById(R.id.tbSistemaMedida);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(escuchadorCalcular);
        btnFinalizar.setOnClickListener(escuchadorFinalizar);
        tbSistemaMedida.setOnClickListener(escuchadorSistemaMedida);
    }


    private View.OnClickListener escuchadorCalcular = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (etAltura.getText().toString().equals("") || etPeso.getText().toString().equals("")){
                Toast.makeText(Calcular.this, "Introduzca los datos", Toast.LENGTH_SHORT).show();
            }
            else {
                double altura = Double.parseDouble(etAltura.getText().toString());
                double peso =  Double.parseDouble(etPeso.getText().toString());

                if (altura == 0){
                    Toast.makeText(Calcular.this, "Introduzca una altura correcta", Toast.LENGTH_SHORT).show();
                }

                else {
                    double imc;
                    if (sistemaMetrico) {
                        imc = peso / (altura * altura);
                    }
                    else {
                        imc = peso / (altura * altura) * 703;
                    }
                    imc = Math.round(imc * 100) / 100.0;

                    //crear objeto Intent
                    Intent intent = new Intent(Calcular.this, Resultado.class);
                    intent.putExtra("imc", imc);

                    //Realizar la llamada
                    startActivity(intent);
                }
            }
        }
    };


    private View.OnClickListener escuchadorFinalizar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //volver a la MainActivity enviando un dato
            String datoRespuesta = "Finalizar aplicacion";
            Intent intent = new Intent();
            intent.putExtra("respuesta", datoRespuesta);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


    private View.OnClickListener escuchadorSistemaMedida = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (tbSistemaMedida.isChecked()){
                sistemaMetrico = false;
                etAltura.setHint("Pulgadas");
                etPeso.setHint("Libras");
            }
            else {
                sistemaMetrico = true;
                etAltura.setHint("Metros");
                etPeso.setHint("Kg");
            }
        }
    };
}
