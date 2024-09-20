package com.example.e2_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class externo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externo);

        int colorFondo = getIntent().getIntExtra("colorFondo", -1);
        int visibility = getIntent().getIntExtra("visibility", View.INVISIBLE);
        if (colorFondo != -1) {
            LinearLayout linearLayout = findViewById(R.id.fondo);
            linearLayout.setBackgroundResource(colorFondo);
        }

        View inicio = findViewById(R.id.inicio);
        inicio.setVisibility(visibility);
    }
    public void onClickBtn(View view) {

        switch (view.getId()){

            case R.id.atras:
                finish();
                break;

            case R.id.inicio:

                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultOk", true);
                setResult(externo.RESULT_OK, returnIntent);
                finish();

                break;
        }
    }
}