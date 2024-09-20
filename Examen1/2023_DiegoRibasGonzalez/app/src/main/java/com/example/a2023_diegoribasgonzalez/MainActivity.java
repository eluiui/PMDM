package com.example.a2023_diegoribasgonzalez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_LLAMADA_ACT_CALCULOIMC = 0;
    boolean calculo = true;
    private View ivLogo;
    private LinearLayout llImc;
    private RadioGroup rgOpciones;
    private Button btnOpcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogo = findViewById(R.id.ivLogo);
        llImc = findViewById(R.id.llImc);
        rgOpciones = findViewById(R.id.rgOpciones);
        btnOpcion = findViewById(R.id.btnOpcion);


        ivLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ivLogo.setVisibility(View.GONE);
                llImc.setVisibility(View.VISIBLE);
                return false;
            }
        });

        rgOpciones.setOnCheckedChangeListener(escuchadorOpciones);
        btnOpcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculo){
                    Intent intent = new Intent(MainActivity.this, Calcular.class);
                    //llamada esperando respuesta
                    startActivityForResult(intent, CODIGO_LLAMADA_ACT_CALCULOIMC);
                }
                else{
                    //crear objeto Intent
                    Intent intent = new Intent(MainActivity.this, Informacion.class);
                    //realizar la llamada
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_LLAMADA_ACT_CALCULOIMC){
            //testeamos codigo de resultado
            if(resultCode == RESULT_OK){
                //actividad llamada finaliza según lo previsto
                finish();
            }
        }
    }


    private RadioGroup.OnCheckedChangeListener escuchadorOpciones = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rbImc){
                btnOpcion.setText("Calcular IMC".toUpperCase());
                calculo = true;
            }
            else {
                btnOpcion.setText("Mas informacion".toUpperCase());
                calculo = false;
            }
        }
    };
}