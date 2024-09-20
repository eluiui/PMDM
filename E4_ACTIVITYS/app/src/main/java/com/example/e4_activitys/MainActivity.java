package com.example.e4_activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int RESPUESTA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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