package com.example.e5_activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button pantalla1,pantalla2,pantalla3,pantalla4;
    private static final int RESPUESTA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla1 = findViewById(R.id.pantalla1);
        pantalla2 = findViewById(R.id.pantalla2);
        pantalla3 = findViewById(R.id.pantalla3);
        pantalla4 = findViewById(R.id.pantalla4);


        pantalla1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showAlertDialog("Pantalla 1", "Esto es la pantalla 1 con 4 colores", Pantalla1.class);
                return true;
            }
        });

        pantalla2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showAlertDialog("Pantalla 2", "Esto es la pantalla 2 con 6 colores", Pantalla2.class);
                return true;
            }
        });

        pantalla3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showAlertDialog("Pantalla 3", "Esto es la pantalla 3 con 6 colores y padding", Pantalla3.class);
                return true; // Indica que el evento de pulsación larga ha sido consumido
            }
        });

        pantalla4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showAlertDialog("Pantalla 4", "Esto es la pantalla 4 con 9 colores y padding", Pantalla4.class);
                return true;
            }
        });
    }
    private void showAlertDialog(String title, String message, final Class<?> targetActivity) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.colores)
                .show();
    }
    public void onClickBtn(View view) {
        Intent intent=null;

        switch (view.getId()) {
            case R.id.pantalla1:
                intent = new Intent(this, Pantalla1.class);
                break;

            case R.id.pantalla2:
                intent = new Intent(this, Pantalla2.class);
                break;

            case R.id.pantalla3:
                intent = new Intent(this, Pantalla3.class);
                break;

            case R.id.pantalla4:
                intent = new Intent(this, Pantalla4.class);
                break;
        }
        startActivityForResult(intent, RESPUESTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == RESULT_OK) {
                boolean resultado = data.getBooleanExtra("resultado", false);
                if (resultado==true) {
                    finish();
                }
            }
        }
    }
}