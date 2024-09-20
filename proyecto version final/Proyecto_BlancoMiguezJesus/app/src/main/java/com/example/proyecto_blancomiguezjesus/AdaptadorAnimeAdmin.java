package com.example.proyecto_blancomiguezjesus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adaptador utilizado para cargar y mostrar una lista de animes en la vista de administrador.
 * Este adaptador se usa exclusivamente cuando un usuario ingresa con privilegios de administrador.
 * Extiende ArrayAdapter para manejar objetos de tipo Anime.
 */
public class AdaptadorAnimeAdmin extends ArrayAdapter<Anime> {
    private Context context;
    private List<Anime> listaAnimes;

    private static class ViewHolder {
        ImageView imageViewAdmin;
        TextView textViewNombreAdmin;
        TextView textViewSinopsisAdmin;
    }

    /**
     * Constructor del adaptador.
     *
     * @param context     El contexto de la aplicación.
     * @param listaAnimes Lista de objetos Anime que se mostrarán en la vista de administrador.
     */
    public AdaptadorAnimeAdmin(Context context, List<Anime> listaAnimes) {
        super(context, R.layout.adaptador, listaAnimes);
        this.context = context;
        this.listaAnimes = listaAnimes;
    }

    /**
     * Método que se llama para obtener la vista de cada elemento en la lista.
     * Este método infla el diseño de los elementos de la lista y asigna los valores correspondientes
     * a cada vista.
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
            viewHolder.imageViewAdmin = convertView.findViewById(R.id.adaptadoriv);
            viewHolder.textViewNombreAdmin = convertView.findViewById(R.id.adaptadortv);
            viewHolder.textViewSinopsisAdmin = convertView.findViewById(R.id.adaptadorsinopsis);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Anime anime = listaAnimes.get(position);
        viewHolder.imageViewAdmin.setImageBitmap(anime.getImagen());
        viewHolder.textViewNombreAdmin.setText(anime.getNombre());
        viewHolder.textViewSinopsisAdmin.setText(anime.getSinopsis());
        return convertView;
    }
}
