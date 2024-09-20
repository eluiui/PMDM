package com.example.ejerciciorepaso;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorAlumno extends ArrayAdapter {
    private Activity activity;
    private ArrayList<Alumno> arrayAlumnos;
    private int layoutPersonalizado;

    public AdaptadorAlumno(@NonNull Activity activity, int layoutPersonalizado, ArrayList<Alumno> arrayAlumnos) {
        super(activity, layoutPersonalizado, arrayAlumnos);
        this.activity = activity;
        this.arrayAlumnos = arrayAlumnos;
        this.layoutPersonalizado = layoutPersonalizado;
    }


    // Método para obtener la vista de cada elemento en la lista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        // Si la fila es nula, inflarla con el layout personalizado y asignar los componentes a ViewHolder
        if (fila == null){
            LayoutInflater inflater = activity.getLayoutInflater();

            fila = inflater.inflate(layoutPersonalizado, null);

            holder = new ViewHolder();
            holder.tvNombreAlumno = fila.findViewById(R.id.tvNombreAlumno);
            holder.tvCurso = fila.findViewById(R.id.tvCurso);
            holder.tvCiclo = fila.findViewById(R.id.tvCiclo);
            holder.ivAlumnoCurso = fila.findViewById(R.id.ivAlumnoCurso);

            fila.setTag(holder);
        }
        // Si la fila ya tiene un ViewHolder asignado, obtenerlo directamente
        else {
            holder = (ViewHolder) fila.getTag();
        }

        // Establecer los valores de los componentes de la fila con los datos del alumno en la posición actual
        holder.tvNombreAlumno.setText(arrayAlumnos.get(position).getNombre().toUpperCase());
        holder.tvCurso.setText(arrayAlumnos.get(position).getCurso());
        holder.tvCiclo.setText(arrayAlumnos.get(position).getCiclo());


        // Establecer la imagen del tipo de alumno según su curso
        if (arrayAlumnos.get(position).getCurso().equals("ESO")){
            holder.ivAlumnoCurso.setImageResource(R.drawable.eso);
        }
        else if (arrayAlumnos.get(position).getCurso().equals("Bach.")) {
            holder.ivAlumnoCurso.setImageResource(R.drawable.bach);
        }
        else {
            holder.ivAlumnoCurso.setImageResource(R.drawable.ciclo);
        }
        return fila;
    }

    // Clase ViewHolder para almacenar los componentes de la fila y evitar búsquedas innecesarias
    static class ViewHolder{
        TextView tvNombreAlumno;
        TextView tvCurso;
        TextView tvCiclo;
        ImageView ivAlumnoCurso;
    }
}
