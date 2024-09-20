package com.example.e11_listas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adaptador extends ArrayAdapter<String> {

    private Activity context;
    private int layoutPersonalizado;
    private String[] arrayPantallas, arrayInformacionPantallas, arrayNumeros;
    private static class ViewHolder {
        TextView tvPantallas;
        TextView tvInformacionPantallas;
        TextView tvNumeros;
    }

    public Adaptador(@NonNull Activity context,
                     int layoutPersonalizado,
                     String[] arrayPantallas,
                     String[] arrayInformacionPantallas,
                     String[] arrayNumeros) {
        super(context, layoutPersonalizado, arrayPantallas);
        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.arrayPantallas = arrayPantallas;
        this.arrayInformacionPantallas = arrayInformacionPantallas;
        this.arrayNumeros = arrayNumeros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutPersonalizado, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvPantallas = convertView.findViewById(R.id.textView);
            viewHolder.tvInformacionPantallas = convertView.findViewById(R.id.tvinfo);
            viewHolder.tvNumeros = convertView.findViewById(R.id.tvnumeros);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvPantallas.setText(arrayPantallas[position]);
        viewHolder.tvInformacionPantallas.setText(arrayInformacionPantallas[position]);
        viewHolder.tvNumeros.setText(arrayNumeros[position]);

        return convertView;
    }
}
