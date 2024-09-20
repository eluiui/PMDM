package com.example.proyecto_blancomiguezjesus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Adaptador personalizado para mostrar elementos de Anime en una lista.
 * Este adaptador extiende ArrayAdapter<Anime> y se utiliza para mostrar objetos de tipo Anime
 * en una interfaz de usuario.
 */
public class AdaptadorAnime extends ArrayAdapter<Anime> {
    private Context context;
    private List<Anime> listaAnimes;
    private static class ViewHolder {
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewSinopsis;
    }

    /**
     * Constructor de la clase AdaptadorAnime.
     *
     * @param context     El contexto de la aplicación.
     * @param listaAnimes Lista de objetos Anime que se mostrarán en el adaptador.
     */
    public AdaptadorAnime(Context context, List<Anime> listaAnimes) {
        super(context, R.layout.adaptador, listaAnimes);
        this.context = context;
        this.listaAnimes = listaAnimes;
    }

    /**
     * Método para establecer nuevos datos en el adaptador.
     * Actualiza la lista de Anime mostrada en el adaptador y notifica al adaptador
     * que los datos han cambiado.
     *
     * @param dataList La nueva lista de Anime a mostrar en el adaptador.
     */
    public void establecerDatos(List<Anime> dataList) {
        if (dataList != null) {
            listaAnimes.clear();
            listaAnimes.addAll(dataList);
            notifyDataSetChanged();
        }
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
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adaptador, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.adaptadoriv);
            viewHolder.textViewNombre = convertView.findViewById(R.id.adaptadortv);
            viewHolder.textViewSinopsis = convertView.findViewById(R.id.adaptadorsinopsis);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Anime anime = listaAnimes.get(position);
        viewHolder.imageView.setImageBitmap(anime.getImagen());
        viewHolder.textViewNombre.setText(anime.getNombre());
        viewHolder.textViewSinopsis.setText(anime.getSinopsis());
        return convertView;
    }
}