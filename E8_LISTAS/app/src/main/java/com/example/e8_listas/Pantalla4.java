package com.example.e8_listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla4 extends AppCompatActivity {
    private static final int RESPUESTA = 1;
    int selecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla4);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, like.class);

        int color = -1;
        switch (view.getId()) {
            case R.id.red:
                color = R.color.red;
                selecion=1;
                break;

            case R.id.yellow:
                color = R.color.yellow;
                selecion=2;
                break;

            case R.id.green:
                color = R.color.green;
                selecion=3;
                break;

            case R.id.blue:
                color = R.color.blue;
                selecion=4;
                break;
            case R.id.naranja:
                color = R.color.naranja;
                selecion=5;
                break;
            case R.id.morado:
                color = R.color.morado;
                selecion=6;
                break;
            case R.id.white:
                color = R.color.white;
                selecion=7;
                break;
            case R.id.gray:
                color = R.color.grey;
                selecion=8;
                break;
            case R.id.darkgreen:
                color = R.color.darkgreen;
                selecion=9;
                break;
        }
        intent.putExtra("colorFondo", color);
        startActivityForResult(intent, RESPUESTA);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == RESULT_OK) {
                boolean resultOk = data.getBooleanExtra("resultOk", false);
                View red = findViewById(R.id.red);
                View blue = findViewById(R.id.blue);
                View yellow = findViewById(R.id.yellow);
                View green = findViewById(R.id.green);
                View naranja = findViewById(R.id.naranja);
                View morado = findViewById(R.id.morado);
                View white = findViewById(R.id.white);
                View grey = findViewById(R.id.gray);
                View darkgreen = findViewById(R.id.darkgreen);


                if (resultOk==true) {
                    if (selecion==1){
                        red.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==2){
                        yellow.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==3){
                        green.setVisibility(View.INVISIBLE);

                    }
                    else if(selecion==4){
                        blue.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==5){
                        naranja.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==6){
                        morado.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==7){
                        white.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==8){
                        grey.setVisibility(View.INVISIBLE);
                    }
                    else if(selecion==9){
                        darkgreen.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }
}


