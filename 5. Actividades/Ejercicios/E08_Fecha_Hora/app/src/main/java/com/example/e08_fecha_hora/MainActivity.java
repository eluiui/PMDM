package com.example.e08_fecha_hora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private DatePicker datePicker;
    private String mensaje_con_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.time_picker);
        datePicker = findViewById(R.id.date_picker);
    }

    public void onClickBtn(View view){
        int version_API = Build.VERSION.SDK_INT;
        if (version_API < 23) {
            mensaje_con_datos = "Fecha: " + datePicker.getDayOfMonth() +
                    "/" + (datePicker.getMonth() + 1) +
                    "/" + datePicker.getYear() +
                    "\nHora:" + timePicker.getCurrentHour() +
                    ":" + timePicker.getCurrentMinute();
        }
        else{
            mensaje_con_datos = "Fecha: " + datePicker.getDayOfMonth() +
                    "/" + (datePicker.getMonth() + 1) +
                    "/" + datePicker.getYear() +
                    "\nHora:" + timePicker.getHour() +
                    ":" + timePicker.getMinute();
        }
        Toast.makeText(this, "mensaje_con_datos", Toast.LENGTH_SHORT).show();
    }
}