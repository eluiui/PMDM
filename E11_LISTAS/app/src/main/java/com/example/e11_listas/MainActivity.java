package com.example.e11_listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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

        pantallasListView.setOnItemClickListener(this);
        paddingListView.setOnItemClickListener(this);
    }

    private void setupPantallas() {
        Adaptador adapterPantallas = new Adaptador(
                this,
                R.layout.adaptador,
                getResources().getStringArray(R.array.pantallas),
                getResources().getStringArray(R.array.informacion),
                getResources().getStringArray(R.array.numero));

        pantallasListView.setAdapter(adapterPantallas);
    }

    private void setupPadding() {
        ArrayAdapter<String> adapterPadding = new ArrayAdapter<>(
                this,
                R.layout.adaptadorpadding,
                R.id.padding,
                getResources().getStringArray(R.array.padding));

        paddingListView.setAdapter(adapterPadding);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.pantallas_lv:
                listviewpantallas(position);
                break;
            case R.id.padding_lv:
                listviewpadding(position);
                break;
        }
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
                pantallasListView.setVisibility(View.VISIBLE);
                paddingListView.setVisibility(View.GONE);
                break;
            case 1:
                intent = new Intent(this, Pantalla3.class);
                startActivityForResult(intent, RESPUESTA);
                pantallasListView.setVisibility(View.VISIBLE);
                paddingListView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA && resultCode == 4) {
            boolean resultOk = data.getBooleanExtra("salida", false);
            if (resultOk) {
                finish();
            }
        }
    }
}