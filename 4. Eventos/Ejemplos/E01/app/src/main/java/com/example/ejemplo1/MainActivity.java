package com.example.ejemplo1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mensaje;
    private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asociar el objeto TextView con la correspondiente vista del layout
        mensaje = findViewById(R.id.tvMensaje);
        btn1 = findViewById(R.id.btnSi);
        btn2 = findViewById(R.id.btnNo);
        btn3 = findViewById(R.id.btnAveces);
    }

    public void onClickBtn(View view){
        //operaciones para gestionar la pulsacion del btn
        if(view.getId() == R.id.btnSi)
                mensaje.setText("Has tecleado SI");
        else if(view.getId()  == R.id.btnNo)
                mensaje.setText("Has tecleado NO");
        else if(view.getId()  == R.id.btnAveces)
                mensaje.setText("Has tecleado A VECES");
        }

    }
}