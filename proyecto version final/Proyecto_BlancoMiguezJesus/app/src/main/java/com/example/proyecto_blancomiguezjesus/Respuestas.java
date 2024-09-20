package com.example.proyecto_blancomiguezjesus;

/**
 * Clase que representa una entrada de pregunta-respuesta.
 * Esta clase encapsula una pregunta y su respuesta correspondiente.
 */
public class Respuestas {
    private String cuestion;
    private String respuestas;

    /**
     * Constructor de la clase Respuestas.
     *
     * @param cuestion   La pregunta asociada a la respuesta.
     * @param respuestas La respuesta correspondiente a la pregunta.
     */
    public Respuestas(String cuestion, String respuestas) {
        this.cuestion = cuestion;
        this.respuestas = respuestas;
    }

    /**
     * Obtiene la pregunta asociada a la respuesta.
     *
     * @return La pregunta asociada a la respuesta.
     */
    public String getCuestion() {
        return cuestion;
    }

    /**
     * Obtiene la respuesta correspondiente a la pregunta.
     *
     * @return La respuesta correspondiente a la pregunta.
     */
    public String getRespuestas() {
        return respuestas;
    }
}
