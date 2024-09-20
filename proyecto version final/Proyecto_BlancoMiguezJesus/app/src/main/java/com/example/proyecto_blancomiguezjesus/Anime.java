package com.example.proyecto_blancomiguezjesus;

import android.graphics.Bitmap;

/**
 * Clase que representa un objeto Anime.
 * Esta clase encapsula la información relacionada con un anime, incluyendo su imagen, nombre, sinopsis y enlace relacionado.
 */
public class Anime {
    // Imagen del anime
    private Bitmap imagen;
    // Nombre del anime
    private String nombre;
    // Sinopsis del anime
    private String sinopsis;
    // Enlace relacionado con el anime
    private String link;

    /**
     * Constructor de la clase Anime.
     *
     * @param imagen   La imagen del anime.
     * @param nombre   El nombre del anime.
     * @param sinopsis La sinopsis del anime.
     * @param link     El enlace relacionado con el anime.
     */
    public Anime(Bitmap imagen, String nombre, String sinopsis, String link) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.link = link;
    }

    /**
     * Obtiene la sinopsis del anime.
     *
     * @return La sinopsis del anime.
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Obtiene la imagen del anime.
     *
     * @return La imagen del anime.
     */
    public Bitmap getImagen() {
        return imagen;
    }

    /**
     * Obtiene el nombre del anime.
     *
     * @return El nombre del anime.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el enlace relacionado con el anime.
     *
     * @return El enlace relacionado con el anime.
     */
    public String getLink() {
        return link;
    }

}