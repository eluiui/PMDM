package com.example.e15_preferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class externo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externo);

        int colorFondo = getIntent().getIntExtra("colorFondo", -1);
        int visibility = getIntent().getIntExtra("visibility", View.INVISIBLE);
        int visibility1 =getIntent().getIntExtra("visibility1", View.GONE);
        if (colorFondo != -1) {
            LinearLayout linearLayout = findViewById(R.id.fondo);
            linearLayout.setBackgroundResource(colorFondo);
        }

        View inicio = findViewById(R.id.inicio);
        View fuera = findViewById(R.id.fuera);
        inicio.setVisibility(visibility);
        fuera.setVisibility(visibility1);
    }
    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.atras:
                Intent atras = new Intent();
                atras.putExtra("resultOk", false);
                setResult(externo.RESULT_OK, atras);
                finish();
                break;

            case R.id.inicio:

                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultOk", true);
                setResult(externo.RESULT_OK, returnIntent);
                finish();
                break;

            case R.id.fuera:
                showDialogWithOptions();
                break;
        }
    }
    private void showDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle("Fuera")
                .setMessage("¿Estás seguro de que deseas salir de la aplicación?")
                .setIcon(R.drawable.colores)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent returnIntentfinis = new Intent();
                        returnIntentfinis.putExtra("resultOK", true);
                        setResult(4, returnIntentfinis);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}