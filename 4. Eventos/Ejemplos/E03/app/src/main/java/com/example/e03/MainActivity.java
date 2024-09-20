package com.example.e03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private ToggleButton toggleButton;
    private Switch switchWIFI;
    private CheckBox checkAcepto;
    private EditText etTextoAlfanumerico, etNumber, etDecimal;
    private Button btnEstandard;
    private RadioGroup radioGroup1;
    private RadioButton rbSi, rbNo, rbAveces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializar variables
        sentenciasFind();

        //asignar escuchadores
        toggleButton.setOnClickListener(this);
        switchWIFI.setOnClickListener(this);
        checkAcepto.setOnClickListener(this);
        btnEstandard.setOnClickListener(this);
        rbSi.setOnClickListener(this);
        rbNo.setOnClickListener(this);
        rbAveces.setOnClickListener(this);

        //escuchador del evento onCheckedChange para la checkbox
        checkAcepto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this,
                            "Cambio de estado: ACEPTO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Cambio de estado: NOOOOOOOOO ACEPTO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //escuchador del evento onCheckedChange para el radioGroup
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbSi){
                    Toast.makeText(MainActivity.this,
                            "Cambio: Elegido SI", Toast.LENGTH_SHORT).show();
                }else if(checkedId==R.id.rbNo){
                    Toast.makeText(MainActivity.this,
                            "Cambio: Elegido NO", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,
                            "Cambio: Elegido A VECES", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sentenciasFind() {
        toggleButton = findViewById(R.id.toggle1);
        switchWIFI = findViewById(R.id.swWifi);
        checkAcepto = findViewById(R.id.chk_acepto);
        etDecimal = findViewById(R.id.et_decimal);
        etTextoAlfanumerico = findViewById(R.id.et_texto_alfanumerico);
        etNumber = findViewById(R.id.et_number);
        btnEstandard = findViewById(R.id.btn_estandar);
        radioGroup1 = findViewById(R.id.rg_1);
        rbSi = findViewById(R.id.rbSi);
        rbNo = findViewById(R.id.rbNo);
        rbAveces = findViewById(R.id.rbAveces);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.toggle1){
            if(toggleButton.isChecked()){
                Toast.makeText(this, "toggle ACTIVADO", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "toggle DESACTIVADO", Toast.LENGTH_SHORT).show();
            }
        }//end toggle
        else if (v.getId()==R.id.swWifi){
            if (switchWIFI.isChecked()){
                Toast.makeText(this, "WIFI ACTIVADO", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "WIFI DESACTIVADO", Toast.LENGTH_LONG).show();
            }
        }//end switch
        else if (v.getId()==R.id.chk_acepto){
            if (checkAcepto.isChecked()){
                Toast.makeText(this, "Acepto ACTIVADO", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Acepto DESACTIVADO", Toast.LENGTH_LONG).show();
            }
        }//end checkAcepto
        else if (v.getId()==R.id.btn_estandar){
            //capturar entradas de cada EditText y construir un mensaje
            String mensaje;
            mensaje = etDecimal.getText().toString()+"\n"+
                    etNumber.getText().toString()+"\n"+
                    etTextoAlfanumerico.getText().toString();
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
            //Visualizar el mensaje
        }//end boton estandar
        else if (v.getId()==R.id.rbSi){
            Toast.makeText(this, "onClick rb SI", Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.rbNo){
            Toast.makeText(this, "onClick rb NO", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "onClick rb A VECES", Toast.LENGTH_LONG).show();
        }
    }
}