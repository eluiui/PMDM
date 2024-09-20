package com.example.e13_listas;

public class Pojo {
    private String nombre;
    private String informacion;
    private String numero;
    private int idImagen;

    public Pojo(String nombre, String informacion, String numero, int idImagen) {
        this.nombre = nombre;
        this.informacion = informacion;
        this.numero = numero;
        this.idImagen = idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public String getNumero() {
        return numero;
    }

    public int getIdImagen() {
        return idImagen;
    }
}