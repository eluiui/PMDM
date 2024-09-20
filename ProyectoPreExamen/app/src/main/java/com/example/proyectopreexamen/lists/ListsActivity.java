package com.example.proyectopreexamen.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectopreexamen.MainActivity;
import com.example.proyectopreexamen.R;

import java.util.ArrayList;

public class ListsActivity extends AppCompatActivity {

    private ListView lvPersonalizada;
    private ListView lv1;

    private SharedPreferences preferencia;
    private LinearLayout layout;

    private ArrayList<ColorBg> arrayColores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_activity);

        lvPersonalizada = findViewById(R.id.lvPersonalizada);
        lv1 = findViewById(R.id.lv1);
        layout = findViewById(R.id.layout);

        preferencia = PreferenceManager.getDefaultSharedPreferences(this);

        //CREA UN ARRAY PARA LOS NOMBRES DE LOS COLORES Y OTRO PARA LAS IMÁGENES Y LLENA OTRO ARRAY
        inicializarArrays();

        // CREA EL ADAPTER CON EL LAYOUT PERSONALIZADO Y EL ARRAY Y LO ASOCIA A LA LISTVIEW
        asociarAdapter();

        //ESCUCHA CUAL ES EL ITEM SELECCIONADO
        itemClickListener_ListView();

        //ESCUCHA QUE COLOR SE CLICKA EN EL PRIMER LISTVIEW Y PONE ESE COLOR DE FONDO
        itemClickListenerFondo();

    }

    private void itemClickListenerFondo() {
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String color = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ListsActivity.this, color, Toast.LENGTH_SHORT).show();

                comprobarColor(color);

            }
        });
    }


    //COMPRUEBA QUE COLOR ESTÁ SELECCIONADO
    private void comprobarColor(String color) {

        int colorFondo = Color.WHITE;

        if (color.equals("Rojo")) {

            colorFondo = Color.RED;

        } else if (color.equals("Azul")) {

            colorFondo = Color.BLUE;

        } else if (color.equals("Verde")) {

            colorFondo = Color.GREEN;

        } else if (color.equals("Amarillo")) {

            colorFondo = Color.YELLOW;

        }

        cambiarColor(colorFondo);
    }

    //CAMBIA EL COLOR DE FONDO DE LA ACTIVIDAD
    private void cambiarColor(int colorFondo) {

        getWindow().getDecorView().setBackgroundColor(colorFondo);

    }


    //ASOCIA EL ADAPTADOR A LA LISTVIEW
    private void asociarAdapter() {
        Adapter adapter = new Adapter(this, R.layout.adapter_list, arrayColores);

        lvPersonalizada.setAdapter(adapter);
    }

    private void itemClickListener_ListView() {
        lvPersonalizada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ColorBg colorSeleccionado = (ColorBg) adapterView.getItemAtPosition(i);

                MainActivity.sacarToast(ListsActivity.this, colorSeleccionado.getColor().toString());
            }
        });
    }


    private void inicializarArrays() {
        String[] nombresColores = getResources().getStringArray(R.array.colores);
        TypedArray fotosColores = getResources().obtainTypedArray(R.array.fotoscolores);

        for (int i = 0; i < nombresColores.length; i++) {

            arrayColores.add(new ColorBg(nombresColores[i], fotosColores.getResourceId(i, -1)));

        }
    }

    public void onClickBtnList(View view) {

        switch (view.getId()) {

            case R.id.btnAtrasLists:

                Intent intent = MainActivity.finalizarActivityResult("Saliendo desde ListViews");

                setResult(RESULT_OK, intent);

                finish();

        }

    }
}