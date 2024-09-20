package com.example.intentsimplicitos;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //cte identificadora de la llamada de teléfono
    private static final int LLAMADA_TELEFONICA_DIRECTA = 0;
    private static final int LLAMADA_TELEFONICA_DIRECTA_COMPAT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {
        Intent intent;
        int viewId = view.getId();

        if (viewId == R.id.btnMostrar) {
            //Genera la acción del intent implicito: ACTION_VIEW
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
            startActivity(intent);
        }

        else if (viewId == R.id.btnLlamar) {
            //Genera la acción del intent implicito: ACTION_CALL
            if (Build.VERSION.SDK_INT >= 23) {
                //Averiguar si el permiso ya ha sido concedido
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    //Realizar la llamada
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)604053218"));
                    startActivity(intent);
                }
                else {
                    //Solicitamos al SO la gestión del permiso por parte del usuario
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONICA_DIRECTA);
                }
            }
            //En APIs anteriores a la 23
            else {
                //Realizar acción correspondiente: llamada directa
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)604053218"));
                startActivity(intent);
            }
        }

        if (viewId == R.id.btnLlamarCompat) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                //realizar la llamada
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)604053218"));
                startActivity(intent);
            }
            else {
                //solicitamos al SO la gestión del permiso por parte del usuario
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONICA_DIRECTA_COMPAT);
            }
        }

        else if (viewId == R.id.btnMarcar) {
            /** Genera la accion del intent implicito: ACTION_DIAL
            intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
            */

            //Genera la acción del intent implicito: ACTION_DIAL con un numero premarcado
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+34)604053218"));
            startActivity(intent);
        }

        else if (viewId == R.id.btnSMS) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:(+34)604053218"));
            startActivity(intent);
        }

        else if (viewId == R.id.btnAbrir) {
            //Genera la acción del intent implicito: ACTION_VIEW
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es"));
            startActivity(intent);
        }

        else if (viewId == R.id.btnMapa) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-31.417, -64.183"));

            //Especifica que se prefiere utilizar la aplicación de mapas de Google para manejar esta acción.
            intent.setPackage("com.google.android.apps.map");

            //Verifica si hay alguna actividad que pueda manejar este intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Esta acción no se puede realizar", Toast.LENGTH_SHORT).show();
            }
        }
        else if (viewId == R.id.btnOtraApp) {
            intent = new Intent();
            //Se establece el nombre del paquete y la actividad (clase) de la aplicación a la que se quiere dirigir.
            intent.setClassName("com.example.cuenta_clicks_v2", "com.example.cuenta_clicks_v2.MainActivity");

            //Da información sobre las app instaladas en el dispositivo y permite realizar consultas
            // sobre las actividades que pueden manejar un determinado Intent
            PackageManager pm = getPackageManager();

            //Obtiene una lista de actividades (componentes) que pueden manejar el Intent especificado.
            List actividadesPosibles = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

            //Se verifica si hay actividades que puedan manejar el Intent creado anteriormente
            if (actividadesPosibles.size() > 0){
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Esta acción no se puede realizar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Metodo en donde recibimos la respuesta que ha dado el usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LLAMADA_TELEFONICA_DIRECTA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Respuesta cuando el permiso es concedido
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986430137"));
                startActivity(intent);
                Toast.makeText(this, "El usuario ha permitido el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
            else {
                //Respuesta cuando el permiso es denegado
                Toast.makeText(this, "El usuario ha denegado el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}