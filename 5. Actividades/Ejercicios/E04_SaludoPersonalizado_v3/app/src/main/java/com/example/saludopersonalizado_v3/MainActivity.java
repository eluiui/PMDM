package com.example.saludopersonalizado_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etNacimiento;
    private TextView tvDespedida;
    private RadioButton rbMasculino, rbFemenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etNacimiento = findViewById(R.id.etNacimiento);

        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);

        tvDespedida = findViewById(R.id.tvDespedida);

        String despedida = getIntent().getStringExtra("despedida");
        tvDespedida.setText(despedida);
    }

    public void saludar(View v){
        if (!etNombre.getText().toString().isEmpty() || !etNacimiento.getText().toString().isEmpty()){
            String saludo = "Hola, ";

            if (rbMasculino.isChecked()){
                saludo += "Sr. ";
            }
            else{
                saludo += "Sra. ";
            }

            saludo += etNombre.getText().toString() + "\n";

            if (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(etNacimiento.getText().toString()) >= 18) {
                saludo += "Eres mayor de edad";
            }
            else {
                saludo += "Eres menor de edad";
            }

            //Moverse a la Activity ActivityRespuesta
            Intent intent = new Intent(this, ActivityRespuesta.class);
            intent.putExtra("saludo", saludo);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
        }
    }
}