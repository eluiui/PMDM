package com.example.e18_eurovision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Menu {
    private Button verdatos;
    LinearLayout layoutautocomplete;
    private ListView listdatos,listdatostodos,listdatospais;
    BD bd;
    AutoCompleteTextView autocomplete;
    List<Eurovision> listaResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    public void inicializar() {
        verdatos = findViewById(R.id.verdatos);
        listdatos = findViewById(R.id.listdatos);
        listdatostodos= findViewById(R.id.listdatostotalespais);
        listdatospais=findViewById(R.id.listdatospais);
        autocomplete=findViewById(R.id.autocomplete);
        layoutautocomplete=findViewById(R.id.llautocomplete);
        bd = new BD(this);
        obtenerdatos();
        List<Eurovision> listaPaises = bd.obtenerPais();
        List<String> nombresPaises = new ArrayList<>();
        for (Eurovision eurovision : listaPaises) {
            nombresPaises.add(eurovision.getPais());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nombresPaises);
        autocomplete.setAdapter(adapter);
        autocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                String textoBusqueda = s.toString();
                List<Eurovision> datosDelPais = bd.obtenerResultadosPorPais(textoBusqueda);
                Adaptador resultado = new Adaptador(MainActivity.this, datosDelPais);
                listdatospais.setAdapter(resultado);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public void onclick(View view) {
        verdatos.setVisibility(View.GONE);
        listdatos.setVisibility(View.VISIBLE);
    }
    public void obtenerdatos() {
        listaResultados = bd.obtenerTodosResultados();
        Adaptador adaptador = new Adaptador(this, listaResultados);
        listdatos.setAdapter(adaptador);
    }
    public void obtenerdatostotalespais() {
        verdatos.setVisibility(View.GONE);
        listdatostodos.setVisibility(View.VISIBLE);
        layoutautocomplete.setVisibility(View.GONE);
        listdatos.setVisibility(View.GONE);
        listaResultados = bd.obtenerTotalesPais();
        AdaptadorTotalesPais adaptador = new AdaptadorTotalesPais(this, listaResultados);
        listdatostodos.setAdapter(adaptador);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.datospais:
                verdatos.setVisibility(View.GONE);
                listdatostodos.setVisibility(View.GONE);
                layoutautocomplete.setVisibility(View.VISIBLE);
                listdatos.setVisibility(View.GONE);
                return true;
            case R.id.totalesporpais:
                obtenerdatostotalespais();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}