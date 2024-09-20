package com.example.ejerciciorepaso;

public class Alumno {
    private String nombre;
    private String curso;
    private String ciclo;

    public Alumno(String nombre, String curso, String ciclo) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        String result;

        if (ciclo.trim().equals("")){
            result = "Nombre: " + nombre + '\n' +
                    "Curso: " + curso;
        }
        else{
            result = "Nombre: " + nombre + '\n' +
                    "Curso: " + curso + '\n' +
                    "Ciclo: " + ciclo;
        }
        return result;
    }
}
