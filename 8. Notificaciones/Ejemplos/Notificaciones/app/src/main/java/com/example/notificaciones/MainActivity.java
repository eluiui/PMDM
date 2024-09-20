package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private int posicion = 0;
    private static final int NOTIFICACION_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_mensaje:
                dialogo_mensaje();
                break;

            case R.id.btn_1boton:
                dialogo_ventana_1boton();
                break;

            case R.id.btn_2boton:
                dialogo_ventana_2boton();
                break;

            case R.id.btn_3boton:
                dialogo_ventana_3boton();
                break;

            case R.id.btn_selectlist:
                dialogo_lista_simple();
                break;

            case R.id.btn_selectlist_aceptar:
                dialogo_lista_simple_aceptar();
                break;

            case R.id.btn_selectlist_multiple:
                dialogo_lista_multiple_aceptar();
                break;

            case R.id.btn_barra_estado:
                notificacion_barra_estado();
                break;
        }
    }

    // Método para mostrar una notificacion de diálogo con un solo botón
    private void dialogo_ventana_1boton() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Establecer el mensaje del diálogo
        ventana.setMessage("AlertDialog con un botón")
                // Establecer el título del diálogo
                .setTitle("Mensaje con un botón. AlertDialog")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Cerrar el diálogo al hacer clic en el botón "Ok"
                        dialogInterface.cancel();
                    }
                })
                // Hacer que el diálogo no sea cancelable al tocar fuera de él
                .setCancelable(false)
                // Mostrar el diálogo
                .show();
    }


    // Método para mostrar una notificacion de diálogo con dos botones
    private void dialogo_ventana_2boton() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Establecer el mensaje del diálogo
        ventana.setMessage("AlertDialog con dos botones")
                // Establecer el título del diálogo
                .setTitle("Mensaje con dos botones. AlertDialog")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "Pulsado Ok", Toast.LENGTH_SHORT).show();

                        // Mostrar un Toast personalizado al hacer clic en el botón "Ok"
                        toast_personalizada("Pulsado OK");
                        // Cerrar el diálogo
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "Pulsado cancelar", Toast.LENGTH_SHORT).show();

                        // Mostrar un Toast centrado al hacer clic en el botón "Cancelar"
                        Toast t = Toast.makeText(MainActivity.this, "Pulsado cancelar", Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                        // Cerrar el diálogo
                        dialogInterface.cancel();
                    }
                })
                // Hacer que el diálogo no sea cancelable al tocar fuera de él
                .setCancelable(false)
                // Mostrar el diálogo
                .show();
    }


    // Método para mostrar un diálogo con tres botones
    private void dialogo_ventana_3boton() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setMessage("AlertDialog con tres botones")
                .setTitle("Mensaje con tres botones. AlertDialog")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulsado Ok", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                })
                .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulsado cancelar", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulsado No", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }


    // Método para mostrar un mensaje de aviso mediante un dialogo
    private void dialogo_mensaje() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Establecer el mensaje del diálogo
        ventana.setMessage("Esto es un mensaje de aviso")
                // Establecer el título del diálogo
                .setTitle("Notificacion por mensaje. AlertDialog")
                // Mostrar el diálogo
                .show();
    }


    // Método para mostrar una lista simple en un diálogo
    private void dialogo_lista_simple() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Establecer el título del diálogo
        ventana.setTitle("Mensaje con una LISTA. AlertDialog")
                .setItems(R.array.colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Mostrar un Toast con el elemento de la lista seleccionado
                        Toast.makeText(MainActivity.this, "Pulsado " + getResources().getStringArray(R.array.colores)[i], Toast.LENGTH_SHORT).show();
                    }
                })
                // Hacer que el diálogo no sea cancelable al tocar fuera de él
                .setCancelable(false)
                // Mostrar el diálogo
                .show();
    }

    // Método para mostrar una lista con un botón de aceptar en un diálogo
    private void dialogo_lista_simple_aceptar() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Establecer el título del diálogo
        ventana.setTitle("Mensaje con una LISTA. AlertDialog")
                .setSingleChoiceItems(R.array.colores, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Almacenar la posición del elemento seleccionado
                        posicion = i;
                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Mostrar un Toast con el elemento seleccionado al hacer clic en "Aceptar"
                        Toast.makeText(MainActivity.this, "Pulsado " + getResources().getStringArray(R.array.colores)[posicion], Toast.LENGTH_SHORT).show();
                        // Cerrar el diálogo
                        dialogInterface.cancel();
                    }
                })
                // Hacer que el diálogo no sea cancelable al tocar fuera de él
                .setCancelable(false)
                // Mostrar el diálogo
                .show();
    }


    // Método para mostrar una lista con múltiples opciones y un botón de aceptar en un diálogo
    private void dialogo_lista_multiple_aceptar() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        // Array para almacenar los colores seleccionados
        boolean[] coloresSeleccionados = {false, false, false, false};
        // Establecer el título del diálogo
        ventana.setTitle("Mensaje con una LISTA. AlertDialog")
                .setMultiChoiceItems(R.array.colores, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // Actualizar el array de colores seleccionados al hacer clic en un elemento
                        if (b) {
                            coloresSeleccionados[i] = true;
                        }
                        else {
                            coloresSeleccionados[i] = false;
                        }
                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strSeleccionados = "";
                        // Construir una cadena con los colores seleccionados
                        for (int j = 0; j < coloresSeleccionados.length; j++) {
                            if (coloresSeleccionados[j]) {
                                strSeleccionados += getResources().getStringArray(R.array.colores)[j] + "\n";
                            }
                        }
                        // Mostrar un Toast con los colores seleccionados al hacer clic en "Aceptar"
                        Toast.makeText(MainActivity.this, "Pulsado: \n" + strSeleccionados, Toast.LENGTH_SHORT).show();
                        // Cerrar el diálogo
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }


    // Método para crear y mostrar una notificación en la barra de estado
    private void notificacion_barra_estado() {
        //1. Crear la notificación
        Notification.Builder builder = new Notification.Builder(this);

        //2. Personalizar la notificación
        // Establecer el icono pequeño
        builder.setSmallIcon(android.R.drawable.btn_star);
        // Establecer el texto del ticker
        builder.setTicker("Atención!!");
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), android.R.drawable.btn_star);
        // Establecer el icono grande
        builder.setLargeIcon(largeIcon);
        // Establecer el título del contenido
        builder.setContentTitle("Mensaje nuevo");

        //builder.setContentText("Próxima reunión semanal blablabla");
        //incluir texto lago --> sustituye al texto corto de setContextText

        // Establecer el estilo del texto grande
        builder.setStyle(new Notification.BigTextStyle().bigText("Linea de prueba 1\n" +
                "Linea de prueba 2\n" +
                "Linea de prueba 3\n" +
                "Linea de prueba 4\n" +
                "Linea de prueba 5\n" +
                "Linea de prueba 6"));
        // Hacer que la notificación se cancele al hacer clic en ella
        builder.setAutoCancel(true);

        //3. Asociar acción
        // Crear un intent para la actividad
        Intent intent = new Intent(this, Activity2.class);
        // Crear un PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // Establecer la acción al hacer clic en la notificación
        builder.setContentIntent(pendingIntent);

        //4. Lanzar la notificacion
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Construir la notificación
        Notification notification = builder.build();
        // Mostrar la notificación
        notificationManager.notify(NOTIFICACION_1, notification);
    }


    // Método para mostrar una toast personalizada
    private void toast_personalizada(String mensaje) {
        // Obtener la referencia al layout contenedor de la toast personalizada
        LinearLayout llToast = findViewById(R.id.ll_toast);
        // Obtener el inflater del contexto
        LayoutInflater inflater = getLayoutInflater();
        // Inflar el layout de la toast personalizada en el contenedor
        View view = inflater.inflate(R.layout.toast_personalizada, llToast);
        // Obtener la referencia al TextView dentro del layout de la toast
        TextView textoToast = view.findViewById(R.id.txt_mensaje_toast);
        // Establecer el texto de la toast con el mensaje proporcionado
        textoToast.setText(mensaje);
        // Crear una instancia de Toast
        Toast toast = new Toast(this);
        // Establecer la duración de la toast
        toast.setDuration(Toast.LENGTH_SHORT);
        // Establecer la vista personalizada para el toast
        toast.setView(view);
        // Mostrar el toast personalizado
        toast.show();
    }
}