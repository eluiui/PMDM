package com.example.proyectopreexamen.spinners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.proyectopreexamen.MainActivity;
import com.example.proyectopreexamen.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SpinnersActivity extends AppCompatActivity {

    private ArrayList<String> listColores = new ArrayList<>();
    private Spinner spn1;
    private Button btnAnhadirColor;
    private EditText etColor;

    private ArrayAdapter<String> adapter;
    private boolean primera = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinners_activity);

        spn1 = findViewById(R.id.spn1);
        btnAnhadirColor = findViewById(R.id.btnAnhadirColor);
        etColor = findViewById(R.id.etColor);

        //OBTIENE LOS VALORES DE UN ARRAY EN .XML PARA HACER DESPUÉS UN ARRAYLIST MODIFICABLE
        obtenerColoresXML();

        //INICIA EL ADAPTADOR Y LO ASOCIA AL SPINNER
        iniciarAdaptador();

        //ESCUCHA EL ITEM SELECCIONADO Y LO ENSEÑA AL PULSAR (FIJARSE EN EL BOOLEAN PARA QUE NO LO MUESTRE AL ABRIR)
        listenerSpinner();

    }

    private void listenerSpinner() {
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!primera){
                    primera = true;
                } else {
                    String colorSeleccionado = adapterView.getItemAtPosition(i).toString();

                    MainActivity.sacarToast(SpinnersActivity.this, colorSeleccionado);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void obtenerColoresXML() {
        // Obtener los nombres del array desde el archivo XML
        Resources res = getResources();
        String[] coloresArray = res.getStringArray(R.array.colores);

        // Convertir el array a un ArrayList
        listColores = new ArrayList<>(Arrays.asList(coloresArray));
    }

    private void iniciarAdaptador() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listColores);

        spn1.setAdapter(adapter);

    }



    //BOTÓN QUE AÑADE UN VALOR AL SPINNER QUE SE RECOGE DE UN EDITTEXT
    //LLAMA A UN MÉTODO QUE COMPRUEBA QUE NO SE REPITA EL COLOR
    //BOTÓN QUE SALE DE LA ACTIVITY
    public void onClickBtnSpinner(View view) {

        if (view.getId() == R.id.btnAnhadirColor){

            if (!etColor.getText().toString().trim().isEmpty()) {

                String colorNuevo = etColor.getText().toString();

                if (!comprobarColor(colorNuevo)){

                    listColores.add(colorNuevo);

                    adapter.notifyDataSetChanged();

                } else {

                    MainActivity.sacarToast(this, "Este color ya existe");

                }



            } else {

                MainActivity.sacarToast(this, "Tienes que escribir algo en la caja de texto");

            }
        } else if (view.getId() == R.id.btnAtrasSpinner) {

            Intent intent = MainActivity.finalizarActivityResult("Saliendo desde Spinners");

            setResult(RESULT_OK, intent);

            finish();

        }

    }


    //COMPRUEBA SI EXISTE O NO EL COLOR EN EL ARRAYLIST
    private boolean comprobarColor(String colorNuevo) {
        boolean existe = false;

        for (String color : listColores) {

            if (colorNuevo.equals(color)){

                existe = true;

            }

        }

        return existe;

    }




}

