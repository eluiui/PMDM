package com.example.e2_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Pantalla1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla1);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, externo.class);

        int color = -1;
        switch (view.getId()) {
            case R.id.red:
                color = R.color.red;
                break;

            case R.id.yellow:
                color = R.color.yellow;
                break;

            case R.id.green:
                color = R.color.green;
                break;

            case R.id.blue:
                color = R.color.blue;
                break;
        }
        intent.putExtra("colorFondo", color);
        startActivity(intent);
    }
}