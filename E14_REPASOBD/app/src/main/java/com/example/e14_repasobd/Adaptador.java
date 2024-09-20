package com.example.e14_repasobd;

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

public class Adaptador extends ArrayAdapter<Alumno> {
    private Activity activity;
    private int layoutPersonalizado;

    public Adaptador(@NonNull Activity activity, int layoutPersonalizado, ArrayList<Alumno> arrayAlumnos) {
        super(activity, layoutPersonalizado, arrayAlumnos);
        this.activity = activity;
        this.layoutPersonalizado = layoutPersonalizado;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewholder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(layoutPersonalizado, parent, false);

            viewholder = new ViewHolder();
            viewholder.nombre = convertView.findViewById(R.id.tvnombre);
            viewholder.curso = convertView.findViewById(R.id.tvcurso);
            viewholder.ciclo = convertView.findViewById(R.id.tvciclo);
            viewholder.imagen = convertView.findViewById(R.id.imageview);

            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Alumno alumno = getItem(position);
        if (alumno != null) {
            viewholder.nombre.setText(alumno.getNombre().toUpperCase());
            viewholder.curso.setText(alumno.getCurso());
            viewholder.ciclo.setText(alumno.getCiclo());

            int imagenResId;
            if (alumno.getCurso().equals("ESO")) {
                imagenResId = R.drawable.eso;
            } else{
                imagenResId = R.drawable.sombrero;
            }

            viewholder.imagen.setImageResource(imagenResId);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView nombre, curso, ciclo;
        ImageView imagen;
    }
}