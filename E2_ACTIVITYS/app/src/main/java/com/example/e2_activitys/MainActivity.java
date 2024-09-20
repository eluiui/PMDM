package com.example.e2_activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        }
        startActivity(intent);
    }
}