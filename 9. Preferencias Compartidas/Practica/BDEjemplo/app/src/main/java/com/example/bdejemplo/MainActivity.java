package com.example.bdejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar objeto de la clase auxiliar
        AuxiliarDB auxiliarDB = new AuxiliarDB(this, "BDUsuarios", null, 1);

        //invocar el metodo de apertura correspondiente: getReadebleDatabase() y getWritableDatabase();
        db = auxiliarDB.getWritableDatabase();

        //AQUI NO PUEDE IR db.close();
    }

    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.btnInsertar:
                //codigo SQL directo
                try{
                    db.execSQL("INSERT INTO usuarios (codigo, nombre) VALUES (2, 'Diego Ribas')");
                }
                catch(SQLException e){
                    Toast.makeText(this, "Insercion erronea directa", Toast.LENGTH_SHORT).show();
                }

                //metodo especifico parametrizado
                ContentValues registroNuevo = new ContentValues();
                registroNuevo.put("codigo", 3);
                registroNuevo.put("nombre", "Leonice Ribas");
                long l = db.insert("usuarios", null, registroNuevo);
                if (l == -1){
                    Toast.makeText(this, "Insecion erronea parametrizada", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btneEliminar:
                //codigo SQL directo
                try{
                    db.execSQL("DELETE FROM usuarios WHERE codigo = 3");
                }
                catch(SQLException e){
                    Toast.makeText(this, "Eliminacion erronea directa", Toast.LENGTH_SHORT).show();
                }

                //metodo especifico parametrizado
                int i = db.delete("usuarios", "codigo = 2", null);
                if (i == 0){
                    Toast.makeText(this, "Eliminacion erronea parametrizada", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnActualizar:
                //codigo SQL directo
                try{
                    db.execSQL("UPDATE usuarios SET nombre='Z_Z' WHERE codigo = 1");
                }
                catch(SQLException e){
                    Toast.makeText(this, "Modificacion erronea directa", Toast.LENGTH_SHORT).show();
                }

                //metodo especifico parametrizado
                ContentValues registroAModificar = new ContentValues();
                registroAModificar.put("nombre", "A_A");
                int filas = db.update("usuarios", registroAModificar, "codigo=1", null);
                if (filas == 0){
                    Toast.makeText(this, "Modificacion erronea parametrizada", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnConsulta1:
                //codigo SQL directo
                Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios WHERE codigo = 1", null);
                if(cursor.moveToFirst()){
                    String nombre = cursor.getString(0);
                    Toast.makeText(this, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Dato inexistente por consulta directa", Toast.LENGTH_SHORT).show();
                }
                cursor.close();

                //metodo parametrizado
                String[] datosARecuperar = {"nombre"};
                Cursor cursor2 = db.query("usuarios", datosARecuperar, "codigo=2", null, null, null, null);
                if(cursor2.moveToFirst()){
                    String nombre = cursor2.getString(0);
                    Toast.makeText(this, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Dato inexistente por consulta parametrizada", Toast.LENGTH_SHORT).show();
                }
                cursor2.close();
                break;

            case R.id.btnConsulta2:
                //codigo SQL directo
                Cursor cursor3 = db.rawQuery("SELECT * FROM usuarios", null);
                if(cursor3.moveToFirst()){ //recuperamos alguna tupla
                    do{
                        int codigo = cursor3.getInt(0);         //campo 1 recuperado --> codigo, index 0
                        String nombre = cursor3.getString(1);   //campo 2 recuperado --> nombre, index 1
                        Toast.makeText(this, "Nombre:" + nombre + " Codigo:" + codigo, Toast.LENGTH_SHORT).show();
                    }
                    while(cursor3.moveToNext());
                }
                else{
                    Toast.makeText(this, "Dato inexistente por consulta directa", Toast.LENGTH_SHORT).show();
                }
                cursor3.close();

                //metodo parametrizado
                String[] datosARecuperar2 = {"nombre"};
                Cursor cursor4 = db.query("usuarios", datosARecuperar2, "codigo=2", null, null, null, null);
                if(cursor4.moveToFirst()){
                    do{
                        int codigo = cursor4.getInt(0);
                        String nombre = cursor4.getString(1);
                        Toast.makeText(this, "PARAMETRIZADO\nNombre: " + nombre, Toast.LENGTH_SHORT).show();
                    }
                    while(cursor4.moveToNext());
                }
                else{
                    Toast.makeText(this, "Dato inexistente por consulta parametrizada", Toast.LENGTH_SHORT).show();
                }
                cursor4.close();
                break;
        }
    }
}