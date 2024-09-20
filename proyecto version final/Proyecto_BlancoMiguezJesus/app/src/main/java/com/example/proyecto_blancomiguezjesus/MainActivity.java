package com.example.proyecto_blancomiguezjesus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Esta clase representa la actividad principal de la aplicación.
 */
public class MainActivity extends AppCompatActivity {
    private TextView Bienvenida;
    private ImageView animeimg;
    private LinearLayout layoutbotones;
    private static final int RESPUESTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        createonlongclick();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario.
     */
    public void inicializar() {
        Bienvenida = findViewById(R.id.Bienvenida);
        animeimg = findViewById(R.id.animeimg);
        layoutbotones = findViewById(R.id.layoutbotones);
    }

    /**
     * Crea un evento de clic largo en la imagen animeimg.
     */
    public void createonlongclick() {
        animeimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bienvenida.setVisibility(View.VISIBLE);
                animeimg.setVisibility(View.GONE);
                setup();
                return false;
            }
        });
    }

    /**
     * Maneja los eventos de clic en los botones de la interfaz de usuario.
     *
     * @param view La vista que ha recibido el clic.
     */
    public void onclick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.adminbtn:
                showDialogWithOptions();
                break;
            case R.id.userbtn:
                intent = new Intent(this, TipoUsuario.class);
                intent.putExtra("btn", 2);
                startActivityForResult(intent, RESPUESTA);
                break;
            case R.id.asistenciabtn:
                intent = new Intent(this, AiChan.class);
                startActivityForResult(intent, RESPUESTA);
                break;
        }
    }

    /**
     * Muestra un diálogo de advertencia con opciones sobre el uso de administracion
     */
    private void showDialogWithOptions() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setIcon(R.drawable.warning)
                .setMessage(getString(R.string.sure))
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, TipoUsuario.class);
                        intent.putExtra("btn", 1);
                        startActivityForResult(intent, RESPUESTA);
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    /**
     * Configura la animación de entrada para la interfaz de usuario.
     */
    public void setup() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        layoutbotones.setVisibility(View.VISIBLE);
                        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
                        Bienvenida.startAnimation(animation2);
                        layoutbotones.startAnimation(animation2);
                        Bienvenida.setText(getString(R.string.tipuser)+"\n"+getString(R.string.ofuser));
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        Bienvenida.startAnimation(animation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA) {
            if (resultCode == RESULT_OK) {
                boolean resultOk = data.getBooleanExtra("salir", false);
                if (resultOk) {
                    finish();
                }
            }
        }
    }
}
