package com.example.e03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity5 extends AppCompatActivity {
    private Button btnRetornar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        btnRetornar = findViewById(R.id.btnEnviarRespuesta);

        //escuchador del boton de retorno
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //volver a la actividad 1 enviandole un dato
                String datoRespuesta = "La actividad 5 envia de vuelta este mensaje a la Act1";
                Intent intent = new Intent();
                intent.putExtra("mensaje_retornado", datoRespuesta);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}