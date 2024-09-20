package com.example.e14_repasobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteOpenHelper {

    private static final String CREAR_TABLA_ALUMNOS = "CREATE TABLE " + "alumnos" + " (" +
            "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre" + " TEXT," +
            "curso" + " TEXT," +
            "ciclo" + " TEXT" +
            ")";

    public BD(Context context) {
        super(context, "alumnos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "alumnos");
        onCreate(db);
    }

    public void addAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("curso", alumno.getCurso());
        values.put("ciclo", alumno.getCiclo());
        long id = db.insert("alumnos", null, values);
        alumno.setId((int) id);
        db.close();
    }

    public void deleteAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("alumnos", "id" + " = ?", new String[]{String.valueOf(alumno.getId())});
        db.close();
    }

    public ArrayList<Alumno> getAllAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("alumnos", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String curso = cursor.getString(cursor.getColumnIndex("curso"));
                String ciclo = cursor.getString(cursor.getColumnIndex("ciclo"));

                Alumno alumno = new Alumno(id, nombre, curso, ciclo);
                alumnos.add(alumno);
            }
            cursor.close();
        }
        return alumnos;
    }
}