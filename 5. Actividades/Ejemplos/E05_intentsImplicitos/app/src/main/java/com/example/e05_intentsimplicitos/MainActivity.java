package com.example.e05_intentsimplicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //cte identificadora de la llamada de teléfono
    private static final int LLAMADA_TELEFONICA_DIRECTA=0;
    private static final int LLAMADA_TELEFONICA_DIRECTA_COMPAT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_contactos:
                //genera la accion del intent implicito: ACTION_VIEW
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;

            case R.id.btn_dial:
                //genera la accion del intent implicito: ACTION_DIAL
                intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
                break;

            case R.id.btn_dia_numero_premarcado:
                //genera la accion del intent implicito: ACTION_DIAL_CON_NUMERO
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+34)986430137"));
                startActivity(intent);
                break;

            case R.id.btn_call_directo:
                //genera la accion del intent implicito: ACTION_CALL
               /* intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                startActivity(intent);*/

                //averiguar si el permiso ya ha sido concedido
                if (Build.VERSION.SDK_INT >= 23) {
                    if(checkSelfPermission(android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                        //realizar la llamada
                        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                        startActivity(intent);
                    }
                    else{
                        //solicitamos al SO la gestión del permiso por parte del usuario
                        requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},LLAMADA_TELEFONICA_DIRECTA);
                    }

                }
                else{
                    //en APIs anteriores a la 23
                    //realizar acción correspondiente: llamada directa
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                    startActivity(intent);
                }
                break;

            case R.id.btn_call_directo_compat:
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    //realizar la llamada
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                    startActivity(intent);
                }
                else{
                    //solicitamos al SO la gestión del permiso por parte del usuario
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},LLAMADA_TELEFONICA_DIRECTA_COMPAT);
                }

            case R.id.btn_carga_url:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.xunta.gal"));
                startActivity(intent);
                break;

            case R.id.btn_mapa:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:42.25,-8.68"));
                intent.setPackage("com.google.android.apss.map");
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Esta acción no se puede realizar", Toast.LENGTH_SHORT).show();
                }
        }//end switch
    }

    //metodo en donde recibimos la respuesta que ha dado el usuario
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==LLAMADA_TELEFONICA_DIRECTA){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //acciones correspondientes al permiso concedido
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                startActivity(intent);
                Toast.makeText(this,"El usuario ha permitido el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
            else{
                //acciones correspondientes al permiso denegado
                Toast.makeText(this,"El usuario ha denegado el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}