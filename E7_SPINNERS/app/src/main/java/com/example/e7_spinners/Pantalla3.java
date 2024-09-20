package com.example.e7_spinners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla3 extends AppCompatActivity {
    private static final int RESPUESTA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla3);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, externo.class);

        int color = -1;
        int visibility = View.INVISIBLE;
        int visibility1=View.GONE;
        switch (view.getId()) {
            case R.id.red:
                color = R.color.red;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;

            case R.id.yellow:
                color = R.color.yellow;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;

            case R.id.green:
                color = R.color.green;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;

            case R.id.blue:
                color = R.color.blue;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;
            case R.id.naranja:
                color = R.color.naranja;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;
            case R.id.morado:
                color = R.color.morado;
                visibility = View.VISIBLE;
                visibility1 = View.VISIBLE;
                break;
        }
        intent.putExtra("colorFondo", color);
        intent.putExtra("visibility", visibility);
        intent.putExtra("visibility1", visibility1);
        startActivityForResult(intent, RESPUESTA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == RESULT_OK) {
                boolean resultOk = data.getBooleanExtra("resultOk", false);
                if (resultOk==true) {
                    finish();
                }
            } else if (resultCode== 4) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("salida", true);
                setResult(4, returnIntent);
                finish();
            }
        }
    }
}