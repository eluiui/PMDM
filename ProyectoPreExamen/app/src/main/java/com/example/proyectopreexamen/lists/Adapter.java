package com.example.proyectopreexamen.lists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectopreexamen.R;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    private Activity activity;
    private int layout_personalizado;
    private ArrayList<ColorBg> arrayColores;


    public Adapter(@NonNull Activity activity, int layout_personalizado, ArrayList arrayColores) {
        super(activity, layout_personalizado, arrayColores);

        this.activity = activity;
        this.layout_personalizado = layout_personalizado;
        this.arrayColores = arrayColores;

    }


    //ASOCIA UN LAYOUT PERSONALIZADO A LA VIEW
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        if (fila == null){

            LayoutInflater inflater = activity.getLayoutInflater();

            fila = inflater.inflate(layout_personalizado, null);

            holder = new ViewHolder();
            holder.tv_list = fila.findViewById(R.id.tv_list);
            holder.img_list = fila.findViewById(R.id.img_list);

            fila.setTag(holder);

        } else {

            holder = (ViewHolder) fila.getTag();

        }

        holder.tv_list.setText(arrayColores.get(position).getColor());

        holder.img_list.setImageResource(arrayColores.get(position).getIdColor());

        return fila;
    }

    //ESTE VIEWHOLDER VALE PARA AHORRAR RECURSOS
    static class ViewHolder {

        TextView tv_list;
        ImageView img_list;

    }
}
