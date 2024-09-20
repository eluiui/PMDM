package com.example.e13_listas;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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
        String[] nombres = getResources().getStringArray(R.array.pantallas);
        String[] informaciones = getResources().getStringArray(R.array.informacion);
        String[] numeros = getResources().getStringArray(R.array.numero);
        TypedArray imagenesIds = getResources().obtainTypedArray(R.array.imagenespantallas);

        List<Pojo> pojos = new ArrayList<>();
        for (int i = 0; i < nombres.length; i++) {
            int imagenId = imagenesIds.getResourceId(i, 0);
            pojos.add(new Pojo(nombres[i], informaciones[i], numeros[i], imagenId));
        }
        imagenesIds.recycle();
        Adaptador adapterPantallas = new Adaptador(
                this,
                R.layout.adaptador,
                pojos
        );

        pantallasListView.setAdapter(adapterPantallas);
    }
    private void setupPadding() {
        String[] nombres = getResources().getStringArray(R.array.padding);
        TypedArray imagenesIds = getResources().obtainTypedArray(R.array.imagenespadding);

        List<Pojo> pojos = new ArrayList<>();
        for (int i = 0; i < nombres.length; i++) {
            int imagenId = imagenesIds.getResourceId(i, 0);
            pojos.add(new Pojo(nombres[i],"","", imagenId));
        }
        imagenesIds.recycle();
        Adaptadorpadding adapterPadding = new Adaptadorpadding(
                this,
                R.layout.adaptadorpadding,
                pojos
        );

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