package com.example.e18_eurovision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "eurovision2021.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RESULTADOS = "resultados";
    private static final String COLUMN_PAIS = "pais";
    private static final String COLUMN_INTERPRETE = "interprete";
    private static final String COLUMN_CANCION = "cancion";
    private static final String COLUMN_VOTOS_JURADO = "votosJurado";
    private static final String COLUMN_VOTOS_AUDIENCIA = "votosAudiencia";

    private final Context context;

    public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
    public List<Eurovision> obtenerResultadosPorPais(String nombrePais) {
        List<Eurovision> listaResultados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnas = {COLUMN_PAIS, COLUMN_INTERPRETE, COLUMN_CANCION, COLUMN_VOTOS_JURADO, COLUMN_VOTOS_AUDIENCIA};
        String seleccion = COLUMN_PAIS + "=?";
        String[] seleccionArgs = {nombrePais};

        Cursor cursor = db.query(
                TABLE_RESULTADOS,
                columnas,
                seleccion,
                seleccionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex(COLUMN_PAIS));
                String interprete = cursor.getString(cursor.getColumnIndex(COLUMN_INTERPRETE));
                String cancion = cursor.getString(cursor.getColumnIndex(COLUMN_CANCION));
                int votosJurado = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_JURADO));
                int votosAudiencia = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_AUDIENCIA));
                Eurovision resultado = new Eurovision(pais, interprete, cancion, votosJurado, votosAudiencia);
                listaResultados.add(resultado);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaResultados;
    }
    public List<Eurovision> obtenerTodosResultados() {
        List<Eurovision> listaResultados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_RESULTADOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex(COLUMN_PAIS));
                String interprete = cursor.getString(cursor.getColumnIndex(COLUMN_INTERPRETE));
                String cancion = cursor.getString(cursor.getColumnIndex(COLUMN_CANCION));
                int votosJurado = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_JURADO));
                int votosAudiencia = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_AUDIENCIA));
                Eurovision resultado = new Eurovision(pais, interprete, cancion, votosJurado, votosAudiencia);
                listaResultados.add(resultado);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaResultados;
    }
    public List<Eurovision> obtenerTotalesPais() {
        List<Eurovision> listaResultados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_RESULTADOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex(COLUMN_PAIS));
                int votosJurado = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_JURADO));
                int votosAudiencia = cursor.getInt(cursor.getColumnIndex(COLUMN_VOTOS_AUDIENCIA));
                Eurovision resultado = new Eurovision(pais,votosJurado,votosAudiencia);
                listaResultados.add(resultado);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaResultados;
    }
    public List<Eurovision> obtenerPais() {
        List<Eurovision> listaResultados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_RESULTADOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String pais = cursor.getString(cursor.getColumnIndex(COLUMN_PAIS));
                Eurovision resultado = new Eurovision(pais);
                listaResultados.add(resultado);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaResultados;
    }
}
