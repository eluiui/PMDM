package com.example.a2023_diegoribasgonzalez;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Informacion  extends AppCompatActivity {
    private static final int LLAMADA_TELEFONICA = 0;
    private RadioGroup rgOpcionesInformacion;
    private RadioButton rbEnlace, rbTelefono;
    private Button btnOk, btnAtras;
    private boolean enlace = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        rgOpcionesInformacion = findViewById(R.id.rgOpcionesInformacion);

        btnOk = findViewById(R.id.btnOk);
        btnAtras = findViewById(R.id.btnAtras);

        rgOpcionesInformacion.setOnCheckedChangeListener(escuchadorOpcionesInformacion);
        btnOk.setOnClickListener(escuchadorOk);
        btnAtras.setOnClickListener(escuchadorAtras);
    }


    private RadioGroup.OnCheckedChangeListener escuchadorOpcionesInformacion = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if(checkedId == R.id.rbEnlace){
                enlace = true;
            }
            else{
                enlace = false;
            }
        }
    };


    private View.OnClickListener escuchadorOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (enlace){
                //generar accion del intent implicito: ACTION_VIEW URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.enlace)));
                if (intent.resolveActivity(getPackageManager()) != null){//resoluble
                    startActivity(intent);
                }
                else {//irresoluble
                    Toast.makeText(Informacion.this, "No se puede realizar esta accion", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if (ActivityCompat.checkSelfPermission(Informacion.this, android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    //existe el permiso
                    //realizar la llamada
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(getResources().getString(R.string.telefono)));
                    startActivity(intent);
                }
                else {//no existe el permiso
                    //solicitamos al SO la gestión del permiso por parte del usuario
                    ActivityCompat.requestPermissions(Informacion.this, new String[] {Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONICA);
                }
            }
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //vemos si el código de respuesta coincide con el identificador de nuestra solicitud
        if (requestCode == LLAMADA_TELEFONICA){
            //vemos si el permiso está concedido
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //acciones correspondientes al permiso concedido
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(getResources().getString(R.string.telefono)));
                startActivity(intent);
            }
            else {
                //acciones correspondientes al permiso denegado
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private View.OnClickListener escuchadorAtras = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
