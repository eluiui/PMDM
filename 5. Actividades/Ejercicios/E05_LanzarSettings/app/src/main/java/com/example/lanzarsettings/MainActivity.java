package com.example.lanzarsettings;

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
        Intent intent;
        int viewId = view.getId();

        if (viewId == R.id.btnCalculadora) {
            try {
                intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Intent para la mayoria de calculadoras
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                else {
                    // Intent para algunos dispositivos Samsung
                    intent.setClassName("com.sec.android.app.popupcalculator", "com.sec.android.app.popupcalculator.Calculator");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            }
            catch (Exception e) {
                // Handle the exception
            }
        }
        else if (viewId == R.id.btnAjustes) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}