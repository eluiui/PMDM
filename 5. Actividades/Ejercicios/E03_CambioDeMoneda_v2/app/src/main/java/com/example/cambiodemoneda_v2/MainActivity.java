package com.example.cambiodemoneda_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton rbToEur, rbToPts;
    private EditText etCantidad;
    private String cantidadValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbToEur = findViewById(R.id.rbToEur);
        rbToPts = findViewById(R.id.rbToPts);

        etCantidad = findViewById(R.id.etCantidad);

        if (savedInstanceState != null) {
            cantidadValue = savedInstanceState.getString("cantidadValue");
            etCantidad.setText(cantidadValue);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cantidadValue", etCantidad.getText().toString());
    }

    public void cambiar(View v) {
        if (!etCantidad.getText().toString().isEmpty()){
            double cantidad =  Double.parseDouble(etCantidad.getText().toString());
            double tasa = 166.386;
            String filtroResultado, mensaje;

            if (rbToEur.isChecked()){
                double resultado = cantidad / tasa;
                filtroResultado = String.format("%.2f", resultado);
                mensaje = cantidad + " pesetas equivalen a " + filtroResultado + " euros.";
            }
            else {
                double resultado = cantidad * tasa;
                filtroResultado = String.format("%.2f", resultado);
                mensaje = cantidad + " euros equivalen a " + filtroResultado + " pesetas.";
            }

            Intent intent = new Intent(this, ActivityRespuesta.class);
            intent.putExtra("mensaje", mensaje);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
        }
    }
}