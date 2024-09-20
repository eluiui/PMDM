package com.example.proyectopreexamen.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectopreexamen.MainActivity;
import com.example.proyectopreexamen.R;
import com.example.proyectopreexamen.lists.ColorBg;
import com.example.proyectopreexamen.lists.ListsActivity;

import java.util.ArrayList;
import java.util.Collections;

public class BdActivity extends AppCompatActivity {

    private EditText et_colorBd;
    private ListView lv_colorBd;
    private SQLiteDatabase db;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayColores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bd_activity);

        et_colorBd = findViewById(R.id.et_colorBd);
        lv_colorBd = findViewById(R.id.lv_colorBd);

        //RECUPERA LA BASE DE DATOS DEL MAIN
        BdAuxiliar dbHelper = new BdAuxiliar(this, "Colores BD", null, 1);
        db = dbHelper.getWritableDatabase();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayColores);

        lv_colorBd.setAdapter(adapter);

        listColores();

        listViewOnItemCLick();
    }



    public void onClickBtnBd(View view) {

        String color = et_colorBd.getText().toString();

        switch (view.getId()){

            case R.id.btn_insertColorBd:

                if (!color.trim().isEmpty()){

                    insertColor(color);

                } else {

                    MainActivity.sacarToast(this, "El campo no puede estar vacío");

                }

                break;

            case R.id.btn_eliminarColorBd:

                if (!color.trim().isEmpty()){

                    deleteColor(color);

                } else {

                    MainActivity.sacarToast(this, "El campo no puede estar vacío");

                }
                break;

            case R.id.btn_listarColoresBd:

                listColores();

                break;

            case R.id.btn_atrasBd:

                Intent intent = MainActivity.finalizarActivityResult("Atrás desde BD");

                setResult(RESULT_OK, intent);
                finish();
                break;

        }

    }

    //MÉTODO PARA INSERTAR UN COLOR
    private void insertColor(String color) {

        /*
        código SQL directo

        try{
            db.execSQL("insert into usuarios (codigo, nombre) values(2, 'Juan Rodriguez')");
        } catch (Exception ex){
            Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
        }
        */

        //MÉTODO PARAMETRIZADO
        ContentValues registroNuevo = new ContentValues();
        registroNuevo.put("color", color);
        long l = db.insert("colores", null, registroNuevo);

        if (l == -1){
            MainActivity.sacarToast(this, "Inserción errónea");
        } else {
            MainActivity.sacarToast(this, "Inserción correcta");
            arrayColores.add(color);
            adapter.notifyDataSetChanged();
        }

    }


    //MÉTODO PARA ELIMINAR UN COLOR
    private void deleteColor(String color) {
/*

        //código SQL directo
        try{
            db.execSQL("delete from usuarios where codigo=3");                  //Eliminamos el usuario 3
        } catch (Exception ex){
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
*/

        //MÉTODO PARAMETRIZADO
        int i = db.delete("colores", "color = " + "'" + color + "'" , null);

        if (i == 0){

            MainActivity.sacarToast(this, "Eliminación errónea");

        } else {

            MainActivity.sacarToast(this, "Eliminación correcta");
            arrayColores.remove(color);
            adapter.notifyDataSetChanged();

        }

    }


/*
    MÉTODO DE MODIFICAR
    private void modificarColor (String color){

        //código SQl directo
        try{
            db.execSQL("update usuarios set nombre='ZZZZZZZ' where codigo=1");
        } catch (Exception ex){
            Toast.makeText(this, "Modificación errónea", Toast.LENGTH_SHORT).show();
        }

        //método parametrizado
        ContentValues registroUpdated = new ContentValues();
        registroUpdated.put("nombre", "AAAAAAA");
        int filas = db.update("usuarios", registroUpdated, "codigo=1", null);

        if (filas == 0){
            Toast.makeText(this, "Modificacion parametrizada errónea", Toast.LENGTH_SHORT).show();
        }

    }

    */


    //LISTA LOS COLORES Y LOS MUESTRA EN LA LISTVIEW
    private void listColores() {

/*
        //código SQl directo
        Cursor cursor = db.rawQuery("select nombre from usuarios where codigo=1", null);

        if (cursor.moveToFirst()){

            String nombre = cursor.getString(0);
            Toast.makeText(this, String.format("Nombre: %s", nombre), Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Dato inexistente", Toast.LENGTH_SHORT).show();

        }

        cursor.close();
        */

        //método parametrizado
        String[] datosARecuperarVarios = {"color"};

        // ESTA QUERY DEVUELVE TODOS PERO SIN PASARLE UN STRING[]
        //Cursor cursorParametrizadoVarios = db.query("usuarios", null, null,null, null , null, null);

        Cursor cursorParametrizadoVarios = db.query("colores", datosARecuperarVarios, null,null, null , null, null);


        if (cursorParametrizadoVarios.moveToFirst()){

            arrayColores.clear();

            do{

                String colorParametrizado = cursorParametrizadoVarios.getString(0);

                llenarArrayConBaseDeDatos(colorParametrizado);

                adapter.notifyDataSetChanged();

            } while (cursorParametrizadoVarios.moveToNext());


        } else {

            Toast.makeText(this, "Dato parametrizado no existente", Toast.LENGTH_SHORT).show();

        }

        cursorParametrizadoVarios.close();

    }


    //LLENA EL ARRAY CON DATOS DE LA BASE DE DATOS PARA LA LIST VIEW
    private void llenarArrayConBaseDeDatos(String colorParametrizado) {

        arrayColores.add(colorParametrizado);

    }



    //LISTENER DE LA LISTVIEW QUE LLENA EL EDITTEXT
    private void listViewOnItemCLick() {
        lv_colorBd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String colorSeleccionado = (String) adapterView.getItemAtPosition(i);

                MainActivity.sacarToast(BdActivity.this, colorSeleccionado.toString());

                et_colorBd.setText(colorSeleccionado.toString());

            }
        });
    }

}