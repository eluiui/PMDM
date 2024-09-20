package com.example.e02_preferenciascompartida_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferencia;
    private TextView tvCambiante;
    private RadioButton rbAzul, rbRojo, rbVerde, rbAmarillo;
    private static final String PREF_COLOR_KEY = "color_preference";
    private static final String DEFAULT_COLOR = "black";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencia = PreferenceManager.getDefaultSharedPreferences(this);
        rbAzul = findViewById(R.id.rbAzul);
        rbRojo = findViewById(R.id.rbRojo);
        rbVerde = findViewById(R.id.rbVerde);
        rbAmarillo = findViewById(R.id.rbAmarillo);

        // Cargar preferencia guardada y establecer el color del TextView
        String color = preferencia.getString(PREF_COLOR_KEY, DEFAULT_COLOR);
        setColor(color);
    }

    public void onClickBtn(View view){
        switch (view.getId()) {
            case R.id.btnGuardar:
                // Guardar preferencia del color
                SharedPreferences.Editor editor = preferencia.edit();
                String color = getColorFromRadioButton();
                editor.putString(PREF_COLOR_KEY, color);
                editor.apply();
                Toast.makeText(this, "Los datos se han guardado", Toast.LENGTH_SHORT).show();
                // Mostrar botón de reseteo y ocultar botón de guardar
                view.setVisibility(View.GONE);
                findViewById(R.id.btnResetear).setVisibility(View.VISIBLE);
                break;

            case R.id.btnResetear:
                // Restablecer color al valor predeterminado
                setColor(DEFAULT_COLOR);
                // Borrar preferencia guardada
                preferencia.edit().remove(PREF_COLOR_KEY).apply();
                // Mostrar botón de guardar y ocultar botón de reseteo
                view.setVisibility(View.GONE);
                findViewById(R.id.btnGuardar).setVisibility(View.VISIBLE);
                Toast.makeText(this, "Color reseteado", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getColorFromRadioButton() {
        if (rbAzul.isChecked()) {
            return "option_blue";
        }
        else if (rbRojo.isChecked()) {
            return "option_red";
        }
        else if (rbVerde.isChecked()) {
            return "option_green";
        }
        else if (rbAmarillo.isChecked()) {
            return "option_yellow";
        }
        return DEFAULT_COLOR;
    }

    private void setColor(String colorName) {
        int colorResId = getResources().getIdentifier(colorName, "color", getPackageName());
        if (colorResId != 0) {
            int color = getResources().getColor(colorResId);
            tvCambiante.setTextColor(color);
        }
    }
}