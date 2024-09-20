package com.example.e02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
        else if (view.getId() == R.id.btnLlamadaCalculadora) {
            Intent intent = new Intent();
            intent.setClassName("com.example.eligegiro",
                    "com.example.eligegiro.MainActivity");

            PackageManger pm = getPackageManager();
            List actividadesPosibles = pm.queryIntentActivities(intent, PackageManager.MATCH);
            if (actividadesPosibles.size() > 0){
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Ninguna actividad puede realizar")
            }

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
}