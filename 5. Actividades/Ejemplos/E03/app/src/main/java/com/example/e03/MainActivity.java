package com.example.e03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String datoAEnviar;
    private static final int CODIGO_LLAMADA_ACT5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickCambioAcitivity(View view) {
        if (view.getId() == R.id.btnLlamadaActividad2){
            //crear un objeto intent
            Intent intent = new Intent(this, Activity2.class);
            //realizar la llamada
            startActivity(intent);
        }
        else if (view.getId() == R.id.btnLlamadaActividad3) {
            Intent intent = new Intent(this, Activity3.class);
            datoAEnviar = "Actividad 1 envia mensaje a Activity 3";
            //añadir dato al input
            intent.putExtra("mensaje", datoAEnviar);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btnLlamadaActividad4Bundle) {
            Intent intent = new Intent(this, Activity4.class);
            datoAEnviar="Activity 1 envia mensaje a aActivity 4 med Bundle";
            //crear el objeto Bundle
            Bundle bundle = new Bundle();

            //añadir datos al bundle
            bundle.putString("mensaje", datoAEnviar);

            //asociar el bundle con el intent
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (view.getId() == R.id.btnLlamadaEsperandoRespuesta) {
            Intent intent = new Intent(this, Activity5.class);
            //llamada esperando respuesta
            startActivityForResult(intent, CODIGO_LLAMADA_ACT5);

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //vemos que activity nos esta contestando
        if (requestCode == CODIGO_LLAMADA_ACT5){
            //testeamos el codigo del resultado
            if (resultCode == RESULT_OK){
                //operaciones si la actividad llamada finaliza segun lo previsto
                Toast.makeText(this, "Todo ok", Toast.LENGTH_SHORT).show();
                TextView tvRespuesta = findViewById(R.id.tvRespuesta);
                tvRespuesta.setText(data.getStringExtra("mensaje_retornado"));
            }
            else {
                //operaciones i la actividad llamada NO finaliza segun lo previsto
                Toast.makeText(this, "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ciclo", "Ejecutando onCreate Actividad 3");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ciclo", "Ejecutando onStart Actividad 3");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ciclo", "Ejecutando onStop Actividad 3");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ciclo", "Ejecutando onResume Actividad 3");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ciclo", "Ejecutando onRestart Actividad 3");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ciclo", "Ejecutando onPause Actividad 3");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ciclo", "Ejecutando onDestroy Actividad 3");
    }

    public void onClickFinalizar(View view){
        finish();
    }
}