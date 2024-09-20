package com.example.alarma_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
    }

    public void onClickBtn(View view) {
        int hora, minutos;
        if (Build.VERSION.SDK_INT < 23) {
            hora = timePicker.getCurrentHour();
            minutos = timePicker.getCurrentMinute();
        } else {
            hora = timePicker.getHour();
            minutos = timePicker.getMinute();
        }

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hora);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutos);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se puede realizar esta acción", Toast.LENGTH_SHORT).show();
        }
    }
}