package com.example.e10_listas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adaptador extends  ArrayAdapter{

    private Activity context;
    private int layoutPersonalizado;
    private String[] arraypantallas, arrayInformacionpantallas, arraynumeros;

    TextView tvpantallas,tvnumeros,tvInformacionpantallas;

    public Adaptador(
            @NonNull Activity context,
            int layoutPersonalizado,
            String[] arraypantallas,
            String[] arrayInformacionpantallas,
            String[] arraynumeros){
        super(context, layoutPersonalizado, arraypantallas);
        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
        this.arraypantallas = arraypantallas;
        this.arrayInformacionpantallas = arrayInformacionpantallas;
        this.arraynumeros=arraynumeros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(layoutPersonalizado, null);

        tvpantallas = fila.findViewById(R.id.textView);
        tvInformacionpantallas = fila.findViewById(R.id.tvinfo);
        tvnumeros = fila.findViewById(R.id.tvnumeros);

        tvpantallas.setText(arraypantallas[position]);
        tvInformacionpantallas.setText(arrayInformacionpantallas[position]);
        tvnumeros.setText(arraynumeros[position]);

        return fila;
    }
}
