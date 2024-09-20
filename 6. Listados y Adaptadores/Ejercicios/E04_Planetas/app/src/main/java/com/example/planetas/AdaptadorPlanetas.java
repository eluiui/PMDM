package com.example.planetas;

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

public class AdaptadorPlanetas extends ArrayAdapter {
    private Activity context;
    private int layoutPersonalizado;
    private String[] arrayPlanetas, arrayInformacionPlanetas;
    private TypedArray arrayImagenesPlanetas;

    public AdaptadorPlanetas(
            @NonNull Activity context,
            int layoutPersonalizado,
            String[] arrayPlanetas,
            String[] arrayInformacionPlanetas,
            TypedArray arrayImagenesPlanetas) {

        //Constructor por defecto de la clase ArrayAdapter
        super(context, layoutPersonalizado, arrayPlanetas);

        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.arrayPlanetas = arrayPlanetas;
        this.arrayInformacionPlanetas = arrayInformacionPlanetas;
        this.arrayImagenesPlanetas = arrayImagenesPlanetas;
    }


    //Implementacion del metodo getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflamos nuestro layout personalizado
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layoutPersonalizado, null);

        //Capturamos los id de cada componente de nuestro layout
        TextView tvPlanetas = fila.findViewById(R.id.tvPlanetas);
        TextView tvInformacionPlanetas = fila.findViewById(R.id.tvInformacionPlanetas);
        ImageView ivPlaneta = fila.findViewById(R.id.ivPlanetas);

        //Insertar cada valor (planeta/imagen)
        tvPlanetas.setText(arrayPlanetas[position]);
        tvInformacionPlanetas.setText(arrayInformacionPlanetas[position]);
        ivPlaneta.setImageDrawable(arrayImagenesPlanetas.getDrawable(position));

        return fila;
    }
}
