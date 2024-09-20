package com.example.e10_listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView pantallasListView, paddingListView;
    private static final int RESPUESTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantallasListView = findViewById(R.id.pantallas_lv);
        paddingListView = findViewById(R.id.padding_lv);

        setupPantallas();
        setupPadding();
    }

    private void setupPantallas() {
        Adaptador adapterPantallas = new Adaptador(
                this,
                R.layout.adaptador,
                getResources().getStringArray(R.array.pantallas),
                getResources().getStringArray(R.array.informacion),
                getResources().getStringArray(R.array.numero));

        pantallasListView.setAdapter(adapterPantallas);
        pantallasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listviewpantallas(position);
            }
        });
    }

    private void setupPadding() {
        ArrayAdapter<String> adapterPadding = new ArrayAdapter<>(
                this,
                R.layout.adaptadorpadding,
                R.id.padding,
                getResources().getStringArray(R.array.padding));

        paddingListView.setAdapter(adapterPadding);
        paddingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listviewpadding(position);
            }
        });
    }

    private void listviewpantallas(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, Pantalla1.class);
                startActivityForResult(intent, RESPUESTA);
                break;
            case 1:
                pantallasListView.setVisibility(View.GONE);
                paddingListView.setVisibility(View.VISIBLE);
                break;
            case 2:
                intent = new Intent(this, Pantalla4.class);
                startActivityForResult(intent, RESPUESTA);
                break;
        }
    }

    private void listviewpadding(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, Pantalla2.class);
                startActivityForResult(intent, RESPUESTA);
                paddingListView.setVisibility(View.GONE);
                pantallasListView.setVisibility(View.VISIBLE);
                break;
            case 1:
                intent = new Intent(this, Pantalla3.class);
                startActivityForResult(intent, RESPUESTA);
                paddingListView.setVisibility(View.GONE);
                pantallasListView.setVisibility(View.VISIBLE);
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