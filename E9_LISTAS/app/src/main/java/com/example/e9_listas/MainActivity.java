package com.example.e9_listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView pantallaslv,paddinglv;
    private static final int RESPUESTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantallaslv = findViewById(R.id.pantallas_lv);
        paddinglv = findViewById(R.id.padding_lv);

        ArrayAdapter<String> adapterpantallas=new ArrayAdapter<String>(this,
                R.layout.adaptador,
                R.id.textView,
                getResources().getStringArray(R.array.pantallas));
        pantallaslv.setAdapter(adapterpantallas);
        pantallaslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                listviewpantallas(i);
            }
        });
        ArrayAdapter<String> adapterpadding=new ArrayAdapter<String>(this,
                R.layout.adaptador,
                R.id.textView,
                getResources().getStringArray(R.array.padding));
        paddinglv.setAdapter(adapterpadding);
        paddinglv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                listviewpadding(i);
            }
        });
    }

    public void listviewpantallas(int i) {
        Intent intent;
        switch (i) {
            case 0:
                intent = new Intent(this, Pantalla1.class);
                startActivityForResult(intent, RESPUESTA);
                break;

            case 1:
                pantallaslv.setVisibility(View.GONE);
                paddinglv.setVisibility(View.VISIBLE);
                break;

            case 2:
                intent = new Intent(this, Pantalla4.class);
                startActivityForResult(intent, RESPUESTA);
                break;
        }
    }

    public void listviewpadding(int i) {
        Intent intent;
        switch (i) {
            case 0:
                intent = new Intent(this, Pantalla2.class);
                startActivityForResult(intent, RESPUESTA);
                paddinglv.setVisibility(View.GONE);
                pantallaslv.setVisibility(View.VISIBLE);
                break;

            case 1:
                intent = new Intent(this, Pantalla3.class);
                startActivityForResult(intent, RESPUESTA);
                paddinglv.setVisibility(View.GONE);
                pantallaslv.setVisibility(View.VISIBLE);
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