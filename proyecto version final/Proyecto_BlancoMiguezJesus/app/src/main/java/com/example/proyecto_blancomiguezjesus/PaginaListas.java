package com.example.proyecto_blancomiguezjesus;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase representa la actividad encargada de mostrar una lista de animes disponibles.
 * Extiende la clase Menus para heredar su funcionalidad de menú, como la creación y manejo de opciones de menú.
 */
public class PaginaListas extends Menus {

    private SearchView buscartexto;
    private ListView listViewpaginas;
    private List<Anime> animes;
    private List<Anime> datosfiltrados;
    private BD bd;
    private AdaptadorAnime adapter;

    /**
     * Método llamado cuando se crea la actividad.
     * Configura el diseño de la actividad y llama a otros métodos para inicializar variables y configurar el ListView.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado anterior de la actividad, si lo hay.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginalistas);
        iniciarVariables();
        registerForContextMenu(listViewpaginas);
    }

    /**
     * Inicializa las variables y configura los listeners necesarios.
     * Crea instancias de la base de datos, el SearchView y el ListView.
     * Carga los animes en el ListView y configura el listener para el campo de búsqueda.
     */
    private void iniciarVariables() {
        bd = new BD(this);
        buscartexto = findViewById(R.id.buscartexto);
        listViewpaginas = findViewById(R.id.listanime);
        animes = new ArrayList<>();
        datosfiltrados = new ArrayList<>();
        cargarAnimesEnListView();
        setUpListViewHoverEffect();
        setUpListeners();
    }

    /**
     * Establece los escuchadores para la funcionalidad de búsqueda en un objeto SearchView.
     * Este método configura los escuchadores necesarios para detectar cambios en la entrada de texto
     * y controlar las acciones correspondientes cuando se realiza una búsqueda o se modifica el texto
     * en el cuadro de búsqueda.
     */
    public void setUpListeners() {
        buscartexto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * Se llama cuando el usuario envía la consulta de búsqueda.
             *
             * @param query El texto de la consulta de búsqueda.
             * @return Devuelve false para indicar que no se ha manejado la acción de enviar la consulta.
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * Se llama cuando se cambia el texto en el cuadro de búsqueda.
             *
             * @param newText El nuevo texto de búsqueda.
             * @return Devuelve true para indicar que se ha manejado el evento de cambio de texto.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                String textoBusqueda = buscartexto.getQuery().toString().trim();

                if (textoBusqueda.isEmpty()) {
                    datosfiltrados.clear();
                    cargarAnimesEnListView();
                } else {
                    filter(newText);
                }
                return true;
            }
        });


    }

    /**
     * Filtra la lista de animes según el texto de búsqueda proporcionado.
     * Borra los datos filtrados actuales y luego recorre la lista de animes original,
     * añadiendo aquellos que coincidan con el texto de búsqueda al conjunto de datos filtrados.
     * Actualiza el adaptador con los datos filtrados y notifica al adaptador del cambio.
     *
     * @param text El texto de búsqueda utilizado para filtrar la lista de animes.
     */
    private void filter(String text) {
        datosfiltrados.clear();
        String textoBusqueda = text.toLowerCase();
        for (Anime anime : animes) {
            String nombreAnime = anime.getNombre().toLowerCase();
            if (nombreAnime.contains(textoBusqueda)) {
                datosfiltrados.add(anime);
            }
        }
        adapter.establecerDatos(datosfiltrados);
        adapter.notifyDataSetChanged();
    }

    /**
     * Configura el efecto de hover en el ListView.
     * Aplica un efecto de escala cuando se toca un elemento en el ListView.
     * Cuando se presiona un elemento, se aumenta ligeramente su escala.
     * Cuando se levanta el dedo o se cancela la acción, se restaura la escala original.
     */
    private void setUpListViewHoverEffect() {
        listViewpaginas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        int position = listViewpaginas.pointToPosition((int) event.getX(), (int) event.getY());
                        if (position != ListView.INVALID_POSITION) {
                            View itemView = listViewpaginas.getChildAt(position - listViewpaginas.getFirstVisiblePosition());
                            if (itemView != null) {
                                LinearLayout adaptador = itemView.findViewById(R.id.adaptadorll);
                                if (adaptador != null) {
                                    adaptador.setScaleX(1.05f);
                                    adaptador.setScaleY(1.05f);
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        for (int i = 0; i < listViewpaginas.getChildCount(); i++) {
                            View itemView = listViewpaginas.getChildAt(i);
                            if (itemView != null) {
                                LinearLayout adaptador = itemView.findViewById(R.id.adaptadorll);
                                if (adaptador != null) {
                                    adaptador.setScaleX(1.0f);
                                    adaptador.setScaleY(1.0f);
                                }
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }

    /**
     * Carga la lista de animes en un ListView.
     * Obtiene todos los animes de la base de datos, crea un adaptador de anime y lo asigna al ListView correspondiente.
     */
    private void cargarAnimesEnListView() {
        animes = bd.obtenerTodosAnimes();
        adapter = new AdaptadorAnime(this, animes);
        listViewpaginas.setAdapter(adapter);
    }

    /**
     * Método llamado cuando se crea un menú contextual.
     * Infla el menú contextual y establece el título del elemento seleccionado como encabezado del menú.
     *
     * @param menu     El menú contextual que se está creando.
     * @param v        La vista que ha desencadenado la creación del menú contextual.
     * @param menuInfo Información adicional sobre la vista que ha desencadenado la creación del menú contextual.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menuctx_user, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        Anime animeSeleccionado = (!datosfiltrados.isEmpty() ? datosfiltrados : animes).get(position);
        if (animeSeleccionado != null) {
            menu.setHeaderTitle(animeSeleccionado.getNombre());
        }
    }


    /**
     * Método llamado cuando se selecciona un elemento del menú contextual.
     * Realiza acciones basadas en la selección del usuario, como agregar un anime a la lista o ver su página.
     *
     * @param item El elemento del menú contextual que se ha seleccionado.
     * @return true si el evento se ha procesado correctamente, false en caso contrario.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Anime animeSeleccionado = (!datosfiltrados.isEmpty() ? datosfiltrados : animes).get(position);
        switch (item.getItemId()) {
            case R.id.añadir:
                String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
                bd.insertarEnLista(nombreUsuario, animeSeleccionado.getNombre());
                return true;
            case R.id.ver:
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.verpag))
                        .setMessage(getString(R.string.seethepage))
                        .setIcon(R.drawable.logo)
                        .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String url = animeSeleccionado.getLink();
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}