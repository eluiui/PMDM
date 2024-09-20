package com.example.e03_preferenciascompartidas_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int posicion = 0;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
    }

    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_singlechoice:
                dialogo_lista_simple();
                break;
            case R.id.btn_reset_pref:
                Toast.makeText(this, "Reset pulsado", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void dialogo_lista_simple() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje con una LISTA. AlertDialog")
                .setSingleChoiceItems(R.array.colores, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        posicion = i;
                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulsado " + getResources().getStringArray(R.array.colores)[posicion], Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }
}