package com.example.e02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMensaje;
    private Button btnSi, btnNo, btnAveces, btnNose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMensaje = findViewById(R.id.tvMensaje);
        btnSi = findViewById(R.id.btnSi);
        btnNo = findViewById(R.id.btnNo);
        btnAveces = findViewById(R.id.btnAveces);
        btnNose = findViewById(R.id.btnNs);

        //instanciar el objeto de la clase Auxiliar (que implemento la interfaz) --> objeto "escuchador"
        Auxiliar escuchadorSi = new Auxiliar();

        //asignar el escuchador a la vista correspondiente
        btnSi.setOnClickListener(escuchadorSi);
        btnNo.setOnClickListener(escuchadorNo);
        btnNose.setOnClickListener(this);


        //METODO 3:Crear el escuchador (med. clase anonima) y asignarlo a la vista en 1 sentencia
        btnAveces.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tvMensaje.setText("Has pulsado A VECES");
            }
        });
    }

    //METODO 4:
    @Override
    public void onClick(View view) {
        tvMensaje.setText("Has pulsado NO SE");
    }

    //METODO 1: Crear clase auxuiliar para implementar la interfaz
    private class Auxiliar implements View.OnClickListener{
        @Override
        public void onClick(View view){
            tvMensaje.setText("Has pulsado SI");
        }
    }

    //METODO 2: Crear el objeto escuchador med. una clase anonima
    private View.OnClickListener escuchadorNo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tvMensaje.setText("Has pulsado NO");
        }
    };

}