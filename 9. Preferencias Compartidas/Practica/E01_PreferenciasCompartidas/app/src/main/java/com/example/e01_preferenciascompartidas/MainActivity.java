package com.example.e01_preferenciascompartidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencia = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.btn_guardar_pref:
                SharedPreferences.Editor editorPreferencia = preferencia.edit();
                editorPreferencia.putString("nombre", "Yo mismo");
                editorPreferencia.putString("email", "diegoribas59@gmail.com");
                editorPreferencia.apply();

                Toast.makeText(this, "Los datos se han guardado", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_recuperar_pref:
                String nombre = preferencia.getString("nombre", "NO HAY NOMBRE");
                String email = preferencia.getString("email", "NO HAY EMAIL");

                Toast.makeText(this, "Nombre;" + nombre + " Email:" + email, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}