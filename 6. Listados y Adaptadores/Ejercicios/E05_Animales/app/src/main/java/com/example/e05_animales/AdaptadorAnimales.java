package com.example.e05_animales;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class AdaptadorAnimales extends ArrayAdapter {
    private Activity context;
    private int layoutPersonalizado;
    private String[] arrayAnimales, arrayInformacionAnimales;
    private TypedArray arrayImagenesAnimales, arrayImagenesColores;

    public AdaptadorAnimales(
            @NonNull Activity context,
            int layoutPersonalizado,
            String[] arrayAnimales,
            String[] arrayInformacionAnimales,
            TypedArray arrayImagenesAnimales,
            TypedArray arrayImagenesColores) {

        //Constructor por defecto de la clase ArrayAdapter
        super(context, layoutPersonalizado, arrayAnimales);
        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.arrayAnimales = arrayAnimales;
        this.arrayInformacionAnimales = arrayInformacionAnimales;
        this.arrayImagenesAnimales = arrayImagenesAnimales;
        this.arrayImagenesColores = arrayImagenesColores;
    }

    //Implementar el metodo getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflamos nuestro layout personalizado
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layoutPersonalizado, null);

        //Capturamos los id de cada componente de nuestro layout
        TextView tvAnimales = fila.findViewById(R.id.tvAnimales);
        TextView tvInformacionAnimales = fila.findViewById(R.id.tvInformacionAnimales);
        ImageView ivAnimales = fila.findViewById(R.id.ivAnimales);
        ImageView ivColor = fila.findViewById(R.id.ivColor);

        //Insertar cada valor (animal/imagen)
        tvAnimales.setText(arrayAnimales[position]);
        tvInformacionAnimales.setText(arrayInformacionAnimales[position]);
        ivAnimales.setImageDrawable(arrayImagenesAnimales.getDrawable(position));
        ivColor.setImageDrawable(arrayImagenesColores.getDrawable(position));

        // Configurar el color de fondo del ImageView ivColor intercalando entre dos colores
        if (position % 2 == 0) {
            fila.setBackgroundColor(context.getColor(R.color.cyan));
            tvAnimales.setTextColor(context.getColor(R.color.yellow));
        }
        else {
            fila.setBackgroundColor(context.getColor(R.color.blue));
            tvAnimales.setTextColor(context.getColor(R.color.red));
        }
        return fila;
    }


    public String getInformacionAnimal(int position) {
        return arrayInformacionAnimales[position];
    }
}
