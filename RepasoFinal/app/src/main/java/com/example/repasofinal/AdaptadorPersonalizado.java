package com.example.repasofinal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends ArrayAdapter {

    private Activity activity;
    private ArrayList<Alumno> arrayAlumnos;
    private int layout_personalizado;

    public AdaptadorPersonalizado(@NonNull Activity activity, int layout_personalizado, ArrayList arrayAlumnos) {
        super(activity, layout_personalizado, arrayAlumnos);

        this.activity = activity;
        this.layout_personalizado = layout_personalizado;
        this.arrayAlumnos = arrayAlumnos;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        if (fila == null){

            LayoutInflater inflater = activity.getLayoutInflater();

            fila = inflater.inflate(layout_personalizado, null);

            holder = new ViewHolder();
            holder.tv_nombreAlumno = fila.findViewById(R.id.tv_nombreAlumno);
            holder.tv_curso = fila.findViewById(R.id.tv_curso);
            holder.tv_ciclo = fila.findViewById(R.id.tv_ciclo);
            holder.img_tipoAlumno = fila.findViewById(R.id.img_tipoAlumno);

            fila.setTag(holder);

        } else {

            holder = (ViewHolder) fila.getTag();

        }

        holder.tv_nombreAlumno.setText(arrayAlumnos.get(position).getNombre().toUpperCase());
        holder.tv_curso.setText(arrayAlumnos.get(position).getCurso());
        holder.tv_ciclo.setText(arrayAlumnos.get(position).getCiclo());

        if (arrayAlumnos.get(position).getCurso().equals("ESO")){

            holder.img_tipoAlumno.setImageResource(R.drawable.icono_eso);

        } else {
            holder.img_tipoAlumno.setImageResource(R.drawable.icono_resto);
        }

        return fila;
    }


    static class ViewHolder{

        TextView tv_nombreAlumno;
        TextView tv_curso;
        TextView tv_ciclo;
        ImageView img_tipoAlumno;

    }
}
