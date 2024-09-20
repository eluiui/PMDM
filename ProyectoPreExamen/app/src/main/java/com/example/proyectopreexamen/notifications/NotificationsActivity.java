package com.example.proyectopreexamen.notifications;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopreexamen.MainActivity;
import com.example.proyectopreexamen.R;

public class NotificationsActivity extends AppCompatActivity {

    private String[] arrayColores;


    //ARRAY PARA EL DIALOG MULTIPLE CHOICE
    private boolean[] arrayEstado;

    //NECESARIO PARA LA NOTIFICACIÓN A PARTIR DEL API 26
    private final static int NOTIFICATION_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);

        arrayColores = getResources().getStringArray(R.array.colores);

    }

    public void onClickBtnNotifications(View view) {

        switch (view.getId()) {

            case R.id.btn_NToast:

                crearToastPersonalizada();
                break;

            case R.id.btn_NToast2:

                crearToastPersonalizadaLargo();
                break;

            case R.id.btn_AlertDialog:

                dialogConList();
                break;

            case R.id.btn_AlertDialogMultipleChoice:

                dialogMultipleChoice();
                break;

            case R.id.btn_NBarra:

                showNotification();
                break;


            case R.id.btn_Botones:

                dialogConBotones();
                break;

        }

    }




    //CREA UNA TOAST PERSONALIZADA DE FORMA SENCILLA
    private void crearToastPersonalizada() {

        Toast toast = Toast.makeText(NotificationsActivity.this, "Toast modificada sencillo", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.END, 10, 10);                         //Dos valores con |
        toast.show();

    }


    //CREA UNA TOAST MUCHO MÁS PERSONALIZABLE
    private void crearToastPersonalizadaLargo() {

        LinearLayout llToast = findViewById(R.id.ll_toast);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_personalizada, llToast);

        TextView txtToast = view.findViewById(R.id.txt_mensaje_toast);

        txtToast.setText("Toast personalizada con Layout nuevo");

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 10);

        toast.setView(view);        //Asociar la Toast a la view
        toast.show();

    }


    //DIALOG QUE TIENE UNA LISTA
    private void dialogConList() {

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setIcon(R.drawable.cheque);
        ventana.setTitle("Atención!");
        ventana.setItems(arrayColores, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(NotificationsActivity.this, arrayColores[which], Toast.LENGTH_SHORT).show();

                dialog.cancel();

            }
        });

        ventana.show();
    }


    //DIALOG CON VARIAS OPCIONES QUE GUARDA EN TRUE SI ESTA MARCADO Y FASE SI NO LO ESTÁ
    private void dialogMultipleChoice() {

        arrayEstado = new boolean[arrayColores.length];

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setIcon(R.drawable.cheque);
        ventana.setTitle("Atención!");


        ventana.setMultiChoiceItems(arrayColores, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        arrayEstado[which] = isChecked;

                    }
                });


        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String result = recorrerArrayBoolean();

                        Toast.makeText(NotificationsActivity.this, result, Toast.LENGTH_SHORT).show();

                    }
                });

        ventana.show();


    }


    //MUESTRA UNA NOTIFICACIÓN ARRIBA PARA IR A OTRA ACTIVIDAD
    private void showNotification() {

        // Convertir el drawable a bitmap
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.cheque);

        //Crear la Notification.Builder
        //El caso comentado es para Apis anteriores
        //Notification.Builder builder = new Notification.Builder(this)
        Notification.Builder builder = new Notification.Builder(this);

        builder.setSmallIcon(android.R.drawable.star_on);
        builder.setTicker("Soy el mensaje opcional");     //Esto no se ve en las APIs actuales

        builder.setContentTitle("Soy el título");
        builder.setContentText("Soy el contenido");
        builder.setStyle(new Notification.BigTextStyle()
                .bigText("Línea de prueba 1\n" +
                        "Línea de prueba 2\n" +
                        "Línea de prueba 3"));
        builder.setAutoCancel(true);

        builder.setLargeIcon(largeIcon);


        //Asociar acción
        Intent intent = new Intent(this, MainActivity.class);      //Al pulsar la notificación te lleva a otra Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        //Asociar el PendingIntent con la notificación
        builder.setContentIntent(pendingIntent);

        //Lanzar la notificación
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();

        notificationManager.notify(NOTIFICATION_1, notification);

    }


    //DIALOG CON LOS TRES BOTONES POSIBLES Y CANCELANDO QUE PUEDA SALIR HASTA QUE PULSE UNO
    private void dialogConBotones() {

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setIcon(R.drawable.cheque);
        ventana.setMessage("Esto es un mensaje de aviso de bla bla");
        ventana.setTitle("Atención!");

        ventana.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(NotificationsActivity.this, "Pulsado Ok", Toast.LENGTH_SHORT).show();

                dialog.cancel();

            }
        });
        ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(NotificationsActivity.this, "Pulsado No", Toast.LENGTH_SHORT).show();

                dialog.cancel();
            }
        });
        ventana.setNeutralButton("Quizás", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NotificationsActivity.this, "Pulsado Quizás", Toast.LENGTH_SHORT).show();

                dialog.cancel();
            }
        });


        ventana.setCancelable(false);       //Método para que no pueda salir a no ser que pulse un botón
        ventana.show();

    }



    //RECORRE EL ARRAY DE ESTADOS PARA SABER SI UN BOTON ESTABA SELECCIONADO O NO
    //LO ESCRIBE SI ESTABA SELECCIONADO
    private String recorrerArrayBoolean() {

        String result = "Has seleccionado: ";

        for (int i = 0; i < arrayEstado.length; i++) {

            if (arrayEstado[i]) {

                result = result.concat(String.format("\n %s ", arrayColores[i]));

            }

        }

        return result;

    }
}