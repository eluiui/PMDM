package com.example.e13_listas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adaptadorpadding extends ArrayAdapter<Pojo> {

    private Activity context;
    private int layoutPersonalizado;

    public Adaptadorpadding(Activity context, int layoutPersonalizado, List<Pojo> listaPojos) {
        super(context, layoutPersonalizado, listaPojos);
        this.context = context;
        this.layoutPersonalizado = layoutPersonalizado;
    }

    static class ViewHolder {
        TextView tvpadding;
        ImageView img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutPersonalizado, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvpadding = convertView.findViewById(R.id.padding);
            viewHolder.img= convertView.findViewById(R.id.imagepadding);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pojo pojo = getItem(position);

        if (pojo != null) {
            viewHolder.tvpadding.setText(pojo.getNombre());
            viewHolder.img.setImageResource(pojo.getIdImagen());
        }

        return convertView;
    }
}