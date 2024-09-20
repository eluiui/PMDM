package com.example.e12_listas;

public class Pojo {
    private String nombre;
    private String informacion;
    private String numero;

    public Pojo(String nombre, String informacion, String numero) {
        this.nombre = nombre;
        this.informacion = informacion;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
