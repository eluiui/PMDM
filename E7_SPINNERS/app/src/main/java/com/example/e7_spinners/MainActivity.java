package com.example.e7_spinners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner pantallas_sp, padding_sp;
    private static final int RESPUESTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantallas_sp = findViewById(R.id.pantallas_spinner);
        padding_sp = findViewById(R.id.padding_spinner);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource
                (this, R.array.padding, android.R.layout.simple_spinner_dropdown_item);

        padding_sp.setAdapter(adaptador);

        pantallas_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2){
                    spinerpantallas();
                }
                else {
                    padding_sp.setVisibility(View.GONE);

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void onClickadelante(View view) {
        spinerpantallas();
        if (padding_sp.getVisibility()==View.VISIBLE){
            spinerpadding();
        }
    }

    public void spinerpantallas() {
        Intent intent;
        int i = pantallas_sp.getSelectedItemPosition();
        switch (i) {
            case 0:
                break;

            case 1:
                intent = new Intent(this, Pantalla1.class);
                startActivityForResult(intent, RESPUESTA);
                break;

            case 2:
                padding_sp.setVisibility(View.VISIBLE);
                break;

            case 3:
                intent = new Intent(this, Pantalla4.class);
                startActivityForResult(intent, RESPUESTA);
                break;
        }
    }

    public void spinerpadding() {
        Intent intent;
        int i = padding_sp.getSelectedItemPosition();

        switch (i) {
            case 0:
                break;

            case 1:
                intent = new Intent(this, Pantalla2.class);
                startActivityForResult(intent, RESPUESTA);
                break;

            case 2:
                intent = new Intent(this, Pantalla3.class);
                startActivityForResult(intent, RESPUESTA);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == 4) {
                boolean resultOk = data.getBooleanExtra("salida", false);
                if (resultOk) {
                    finish();
                }
            }
        }
    }
}
