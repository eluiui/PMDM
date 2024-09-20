package com.example.e14_repaso;

public class Alumno {
    private String nombre, curso, ciclo;

    public Alumno(String nombre, String curso, String ciclo) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public String getCiclo() {
        return ciclo;
    }
    @Override
    public String toString() {
        String result;

        if (ciclo.trim().equals("")){
            result = "Nombre: "+nombre+"\nCurso: "+curso;
        }
        else{
            result = "Nombre: "+nombre+"\nCurso: "+curso+"\nCiclo: "+ciclo;
        }
        return result;
    }
}