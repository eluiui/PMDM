package com.example.e14_repasobdexterna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class BD extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "alumnos.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "alumnos";

    public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("curso", alumno.getCurso());
        values.put("ciclo", alumno.getCiclo());
        long id = db.insert(TABLE_NAME, null, values);
        alumno.setId((int) id);
        db.close();
    }

    public void deleteAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id" + " = ?", new String[]{String.valueOf(alumno.getId())});
        db.close();
    }

    public ArrayList<Alumno> getAllAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

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
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return alumnos;
    }
}