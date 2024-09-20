package com.example.saludopersonalizado_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityRespuesta extends AppCompatActivity {
    private TextView tvMensaje, tvElegir;
    private Button btnFinalizar;
    private CheckBox cbDespedida;
    private RadioButton rbAdios, rbHastaPronto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        tvMensaje = findViewById(R.id.tvMensaje);
        tvElegir = findViewById(R.id.tvElegir);

        cbDespedida = findViewById(R.id.cbDespedida);

        rbAdios = findViewById(R.id.rbAdios);
        rbHastaPronto = findViewById(R.id.rbHastaPronto);

        btnFinalizar = findViewById(R.id.btnFinalizar);

        String saludo = getIntent().getStringExtra("saludo");
        tvMensaje.setText(saludo);

        listeners();
    }

    private void listeners(){
        cbDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvElegir.setVisibility(View.VISIBLE);
                    rbAdios.setVisibility(View.VISIBLE);
                    rbHastaPronto.setVisibility(View.VISIBLE);
                }
                else{
                    tvElegir.setVisibility(View.GONE);
                    rbAdios.setVisibility(View.GONE);
                    rbHastaPronto.setVisibility(View.GONE);
                }
            }
        });
    }

    public void finalizar(View v){
        String despedida;
        if (cbDespedida.isChecked()){
            if (rbAdios.isChecked()){
                despedida = "ADIOS";
            }
            else{
                despedida = "HASTA PRONTO";
            }
        }
        else {
            despedida = "El usuario no quiso despedida";
        }

        Intent intentRetorno = new Intent(this, MainActivity.class);
        intentRetorno.putExtra("despedida", despedida);
        startActivity(intentRetorno);
        finish();
    }
}
