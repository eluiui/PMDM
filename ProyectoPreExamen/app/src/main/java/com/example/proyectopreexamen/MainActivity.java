package com.example.proyectopreexamen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectopreexamen.lists.ListsActivity;
import com.example.proyectopreexamen.menus.MenusActivity;
import com.example.proyectopreexamen.notifications.NotificationsActivity;
import com.example.proyectopreexamen.persistence.BdActivity;
import com.example.proyectopreexamen.persistence.BdAuxiliar;
import com.example.proyectopreexamen.spinners.SpinnersActivity;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_ACTIVITY = 0;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BdAuxiliar auxiliar = new BdAuxiliar(this, "Colores BD", null, 1);

        db = auxiliar.getWritableDatabase();

    }

    public SQLiteDatabase getDb() {
        return db;
    }

    //CIERRA LA BASE DE DATOS AL CERRAR LA APP
    @Override
    protected void onDestroy() {
        super.onDestroy();

        db.close();

    }

    public void onClickBtn(View view) {
        Intent intent = null;

        switch (view.getId()){

            case R.id.btnMenus:

                intent = new Intent(this, MenusActivity.class);
                break;

            case R.id.btnSpinners:

                intent = new Intent(this, SpinnersActivity.class);
                break;

            case R.id.btnListViews:

                intent = new Intent(this, ListsActivity.class);
                break;

            case R.id.btnNotificaciones:

                intent = new Intent(this, NotificationsActivity.class);
                break;

            case R.id.btnPersistence:

                intent = new Intent(this, BdActivity.class);
                break;

        }

        startActivityForResult(intent, RESULT_ACTIVITY);

    }



    //MÉTODO QUE FINALIZA LAS ACTIVITYS
    public static Intent finalizarActivityResult(String s){

        String mensajeRespuesta = s;

        Intent intent = new Intent();

        intent.putExtra("mensaje_retornado", mensajeRespuesta);

        return intent;

    }


    //SACA UN TOAST CON EL TEXTO QUE RECIBE
    public static void sacarToast(Context context, String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }



    //RECOGE EL RESULTADO DE LAS ACTIVITIES
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_ACTIVITY){
            //testeamos el código del resultado
            if (resultCode == RESULT_OK){
                //operaciones si la actividad llamada finaliza según lo previsto
                Toast.makeText(this, data.getStringExtra("mensaje_retornado"), Toast.LENGTH_SHORT).show();
            } else {
                //operaciones si la actividad llamada no hace lo previsto
                Toast.makeText(this, "Algo falló", Toast.LENGTH_SHORT).show();
            }
        }
    }
}