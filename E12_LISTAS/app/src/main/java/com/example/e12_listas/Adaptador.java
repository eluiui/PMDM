package com.example.e12_listas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adaptador extends ArrayAdapter<Pojo> {

    private Activity context;
    private int layoutPersonalizado;
    public Adaptador(Activity context, int layoutPersonalizado, List<Pojo> listaPojos) {
        super(context, layoutPersonalizado, listaPojos);
        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
    }

    static class ViewHolder {
        TextView tvPantallas;
        TextView tvInformacionPantallas;
        TextView tvNumeros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutPersonalizado, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvPantallas = convertView.findViewById(R.id.textView);
            viewHolder.tvInformacionPantallas = convertView.findViewById(R.id.tvinfo);
            viewHolder.tvNumeros = convertView.findViewById(R.id.tvnumeros);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pojo pojo = getItem(position);

        if (pojo != null) {
            viewHolder.tvPantallas.setText(pojo.getNombre());
            viewHolder.tvInformacionPantallas.setText(pojo.getInformacion());
            viewHolder.tvNumeros.setText(pojo.getNumero());
        }

        return convertView;
    }
}
