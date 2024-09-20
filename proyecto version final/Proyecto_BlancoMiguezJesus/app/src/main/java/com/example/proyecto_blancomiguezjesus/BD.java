package com.example.proyecto_blancomiguezjesus;

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

/**
 * Clase que gestiona la base de datos SQLite de la aplicación.
 */
public class BD extends SQLiteAssetHelper {
    private static final String dbname = "aplicacion.db";
    public static final String userstab = "usuarios";
    public static final String username = "nombre";
    public static final String userpassword = "contraseña";
    public static final String useradress = "correo";

    public static final String listtab = "listas";
    public static final String listuserid = "usuario_id";
    public static final String listname = "anime_id";

    public static final String animetab = "animes";
    public static final String animetitle = "titulo";
    public static final String animesinopsis = "sinopsis";
    public static final String animelink = "link";
    public static final String animeimage = "imagen";

    private Context context;

    public BD(Context context) {
        super(context, dbname, null, 1);
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

    /**
     * Método para insertar un nuevo usuario en la base de datos.
     *
     * @param nombre     El nombre del usuario.
     * @param contraseña La contraseña del usuario.
     * @param correo     El correo electrónico del usuario.
     */
    public void insertarUsuario(String nombre, String contraseña, String correo) {
        if (!nombre.isEmpty() && !contraseña.isEmpty() && !correo.isEmpty()) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(username, nombre);
            values.put(userpassword, contraseña);
            values.put(useradress, correo);
            db.insert(userstab, null, values);
            db.close();
            Toast.makeText(context, context.getString(R.string.exito), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.allthecamps), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para verificar si un usuario ya existe en la base de datos.
     *
     * @param nombre El nombre del usuario a verificar.
     * @return true si el usuario existe, false de lo contrario.
     */
    public boolean existeUsuario(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(userstab, new String[]{username}, username + "=?", new String[]{nombre}, null, null, null);
        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    /**
     * Método para verificar si las credenciales de un usuario son correctas.
     *
     * @param nombre     El nombre del usuario.
     * @param contraseña La contraseña del usuario.
     * @return true si las credenciales son correctas, false de lo contrario.
     */
    public boolean verificarUsuario(String nombre, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                userstab,
                null,
                username + " = ? AND " + userpassword + " = ?",
                new String[]{nombre, contraseña},
                null,
                null,
                null
        );
        boolean usuarioExiste = cursor.moveToFirst();
        cursor.close();
        db.close();
        return usuarioExiste;
    }

    /**
     * Método para insertar un nuevo anime en la base de datos.
     *
     * @param titulo    El título del anime.
     * @param sinopsis  La sinopsis del anime.
     * @param link      El enlace del anime.
     * @param imagen    La imagen del anime en formato byte array.
     */
    public void insertarAnime(String titulo,String sinopsis, String link, byte[] imagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(animetitle, titulo);
        values.put(animesinopsis, sinopsis);
        values.put(animelink, link);
        values.put(animeimage, imagen);
        db.insert(animetab, null, values);
        db.close();
    }

    /**
     * Método para modificar un anime existente en la base de datos.
     *
     * @param nombre      El nombre del anime a modificar.
     * @param nuevoNombre El nuevo nombre del anime.
     * @param nuevasinopsis La nueva sinopsis del anime.
     * @param nuevoLink   El nuevo enlace del anime.
     * @param nuevaImagen La nueva imagen del anime en formato byte array.
     */
    public void modificarAnime(String nombre, String nuevoNombre,String nuevasinopsis, String nuevoLink, byte[] nuevaImagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(animetitle, nuevoNombre);
        values.put(animesinopsis, nuevasinopsis);
        values.put(animelink, nuevoLink);
        values.put(animeimage, nuevaImagen);
        db.update(animetab, values, animetitle + " = ?", new String[]{nombre});
        db.close();
    }

    /**
     * Método para verificar si un anime existe en la lista del usuario en la base de datos.
     *
     * @param nombreUsuario El nombre del usuario.
     * @param anime         El título del anime a verificar.
     * @return true si el anime existe en la lista del usuario, false de lo contrario.
     */
    public boolean existeanimeUsuario(String nombreUsuario, String anime) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(listtab, new String[]{listname}, listuserid + "=? AND " + listname + "=?", new String[]{nombreUsuario, anime}, null, null, null);
        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    /**
     * Método para insertar un nuevo anime en la lista del usuario en la base de datos.
     * Si el anime ya está en la lista del usuario, muestra un mensaje de advertencia.
     *
     * @param nombreUsuario El nombre del usuario.
     * @param tituloAnime   El título del anime a insertar en la lista del usuario.
     */
    public void insertarEnLista(String nombreUsuario, String tituloAnime) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (existeanimeUsuario(nombreUsuario, tituloAnime)) {
            Toast.makeText(context, tituloAnime + context.getString(R.string.alreadyinthelist), Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(listuserid, nombreUsuario);
        values.put(listname, tituloAnime);
        db.insert(listtab, null, values);
        db.close();
        Toast.makeText(context, tituloAnime + context.getString(R.string.addedtolist) + nombreUsuario, Toast.LENGTH_SHORT).show();
    }

    /**
     * Método para eliminar un anime de la lista personal de cada usuario individual.
     *
     * @param nombreUsuario El nombre del usuario.
     * @param tituloAnime   El título del anime a eliminar de la lista del usuario.
     */
    public void eliminarDeLista(String nombreUsuario, String tituloAnime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(listtab, listuserid + " = ? AND " + listname + " = ?", new String[]{nombreUsuario, tituloAnime});
        db.close();
    }

    /**
     * Método para obtener todos los animes que se encuentren en la lista personal de un usuario en la base de datos.
     *
     * @param nombreUsuario El nombre del usuario cuya lista de animes se desea obtener.
     * @return Una lista de objetos Anime que representan los animes en la lista personal del usuario.
     */
    public List<Anime> obtenerAnimesEnLista(String nombreUsuario) {
        List<Anime> listaAnimes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                listtab,
                new String[]{listname},
                listuserid + " = ?",
                new String[]{nombreUsuario},
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String animeId = cursor.getString(cursor.getColumnIndex(listname));
                Cursor animeCursor = db.query(
                        animetab,
                        null,
                        animetitle + " = ?",
                        new String[]{animeId},
                        null,
                        null,
                        null
                );
                if (animeCursor != null && animeCursor.moveToFirst()) {
                    String titulo = animeCursor.getString(animeCursor.getColumnIndex(animetitle));
                    String sinopsis = animeCursor.getString(animeCursor.getColumnIndex(animesinopsis));
                    String link = animeCursor.getString(animeCursor.getColumnIndex(animelink));
                    byte[] imagenBytes = animeCursor.getBlob(animeCursor.getColumnIndex(animeimage));
                    Bitmap imagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
                    Anime anime = new Anime(imagen, titulo, sinopsis, link);
                    listaAnimes.add(anime);
                    animeCursor.close();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaAnimes;
    }

    /**
     * Método para borrar un anime de la base de datos.
     *
     * @param tituloAnime El título del anime que se desea borrar.
     */
    public void borrarAnime(String tituloAnime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(animetab, animetitle + " = ?", new String[]{tituloAnime});
        db.close();
    }

    /**
     * Método para obtener todos los animes que se encuentren en la base de datos.
     *
     * @return Una lista de objetos Anime que representan todos los animes en la base de datos.
     */
    public List<Anime> obtenerTodosAnimes() {
        List<Anime> listaAnimes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                animetab,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String titulo = cursor.getString(cursor.getColumnIndex(animetitle));
                String sinopsis = cursor.getString(cursor.getColumnIndex(animesinopsis));
                String link = cursor.getString(cursor.getColumnIndex(animelink));
                byte[] imagenBytes = cursor.getBlob(cursor.getColumnIndex(animeimage));
                Bitmap imagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
                Anime anime = new Anime(imagen, titulo, sinopsis, link);
                listaAnimes.add(anime);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listaAnimes;
    }
}