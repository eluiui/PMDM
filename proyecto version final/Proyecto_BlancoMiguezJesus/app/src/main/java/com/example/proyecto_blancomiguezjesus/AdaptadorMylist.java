package com.example.proyecto_blancomiguezjesus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Adaptador utilizado en "MiLista" para cargar y mostrar una lista de animes.
 * Este adaptador extiende ArrayAdapter y gestiona la visualización de objetos Anime
 * en una lista personalizada.
 */
public class AdaptadorMylist extends ArrayAdapter<Anime> {
    private Context context;
    private List<Anime> listaAnimes;
    private static class ViewHolder {
        ImageView mylistImageView;
        TextView mylistTextView;
        TextView mylistTextViewSinopsis;
    }

    /**
     * Constructor del adaptador.
     *
     * @param context     El contexto de la aplicación.
     * @param listaAnimes Lista de objetos Anime que se mostrarán en "Mi Lista".
     */
    public AdaptadorMylist(Context context, List<Anime> listaAnimes) {
        super(context, R.layout.adaptador, listaAnimes);
        this.context = context;
        this.listaAnimes = listaAnimes;
    }

    /**
     * Método que se llama para obtener la vista de cada elemento en la lista.
     *
     * @param position    La posición del elemento dentro del adaptador de datos.
     * @param convertView La vista antigua a reutilizar, si es posible. (Puede ser null)
     * @param parent      El padre al que esta vista será adjunta.
     * @return La vista para el elemento en la posición especificada.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adaptador, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mylistImageView = convertView.findViewById(R.id.adaptadoriv);
            viewHolder.mylistTextView = convertView.findViewById(R.id.adaptadortv);
            viewHolder.mylistTextViewSinopsis = convertView.findViewById(R.id.adaptadorsinopsis);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Anime anime = listaAnimes.get(position);
        viewHolder.mylistImageView.setImageBitmap(anime.getImagen());
        viewHolder.mylistTextView.setText(anime.getNombre());
        viewHolder.mylistTextViewSinopsis.setText(anime.getSinopsis());

        return convertView;
    }
}