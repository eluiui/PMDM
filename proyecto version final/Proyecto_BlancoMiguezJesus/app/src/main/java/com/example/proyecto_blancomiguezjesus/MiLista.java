package com.example.proyecto_blancomiguezjesus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Esta clase representa la actividad de la lista personalizada de animes.
 * así como la reproducción de audio y la interacción con el usuario.
 */
public class MiLista extends AppCompatActivity {
    private Spinner spavatar;
    private TextView nombreuser;
    private ImageView avatariv;
    private ListView milistalv;
    private AdaptadorMylist adapter;
    private List<Anime> animes;
    private MediaPlayer musica;
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milista);
        inicializar();
    }

    /**
     * Método llamado cuando la actividad pasa al estado detenido.
     * Se encarga de detener y liberar los recursos de los reproductores de audio.
     */
    @Override
    protected void onStop() {
        super.onStop();
        stopAndReleaseMediaPlayer();
    }

    /**
     * Inicializa las vistas de la interfaz de usuario y carga los datos de la lista de animes.
     * También configura los eventos para el spinner de avatares y el menú contextual.
     */
    public void inicializar() {
        bd = new BD(this);
        milistalv = findViewById(R.id.milistalv);
        spavatar = findViewById(R.id.spprofile);
        avatariv = findViewById(R.id.avatariv);
        nombreuser = findViewById(R.id.nombredeusertv);
        String username = getIntent().getStringExtra("nombreUsuario");
        nombreuser.setText(username);
        cargarAnimesEnListView();
        configurarSpinnerAvatares();
        registerForContextMenu(milistalv);
    }

    /**
     * Configura el spinner de avatares para reproducir audio y cambiar el avatar.
     * Si el dispositivo tiene una versión de Android superior a API 22,
     * establece un listener en el spinner para manejar la selección de avatares.
     * Cuando se selecciona un avatar, detiene la reproducción de cualquier audio previo,
     * reproduce el nuevo audio asociado con el avatar seleccionado y actualiza la imagen del avatar.
     * Si el dispositivo tiene una versión anterior o igual a 22, simplemente establece la imagen
     * predeterminada del avatar y oculta el spinner.
     */
    private void configurarSpinnerAvatares() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            spavatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    stopAndReleaseMediaPlayer();
                    switch (position) {
                        case 0:
                            avatariv.setImageResource(R.drawable.anonimo);
                            break;
                        case 1:
                            playAudio(R.raw.megumin, R.drawable.megumin);
                            break;
                        case 2:
                            playAudio(R.raw.loneliness, R.drawable.jesu);
                            break;
                        case 3:
                            playAudio(R.raw.miku, R.drawable.miku);
                            break;
                        case 4:
                            playAudio(R.raw.alastor, R.drawable.alastor);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else {
            avatariv.setImageResource(R.drawable.anonimo);
            spavatar.setVisibility(View.GONE);
        }
    }

    /**
     * Reproduce el audio asociado con el avatar seleccionado y actualiza la imagen del avatar.
     *
     * @param audioResource Recurso de audio que se reproducirá.
     * @param avatarResource Recurso de imagen que se asociará con el avatar.
     */
    private void playAudio(int audioResource, int avatarResource) {
        stopAndReleaseMediaPlayer();
        musica = MediaPlayer.create(this, audioResource);
        if (musica != null) {
            musica.start();
            musica.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            avatariv.setImageResource(avatarResource);
        }
    }

    /**
     * Detiene y libera la instancia actual de MediaPlayer si existe.
     */
    private void stopAndReleaseMediaPlayer() {
        if (musica != null) {
            musica.stop();
            musica.release();
            musica = null;
        }
    }

    /**
     * Crea el menú contextual para la lista de animes.
     *
     * @param menu     El menú contextual que se va a crear.
     * @param v        La vista que ha activado el menú contextual.
     * @param menuInfo Información adicional sobre el elemento seleccionado.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        Anime animeSeleccionado = animes.get(position);
        if (animeSeleccionado != null) {
            menu.setHeaderTitle(animeSeleccionado.getNombre());
        }

        getMenuInflater().inflate(R.menu.menuctx_mylist, menu);
    }
    /**
     * Maneja las selecciones de elementos del menú contextual.
     *
     * @param item El elemento del menú que se ha seleccionado.
     * @return true si el evento se ha procesado correctamente, false en caso contrario.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.eliminardemilista:
                dialogodeborrar(position);
                return true;
            case R.id.verpaginamylista:
                showDialogWithOptions(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Muestra un diálogo de confirmación para borrar un elemento en una determinada posición de la lista.
     *
     * @param position La posición del elemento que se desea borrar.
     */
    private void dialogodeborrar(int position) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.borrar))
                .setIcon(R.drawable.warning)
                .setMessage(getString(R.string.areyousure))
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        borraranime(position);
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    /**
     * Elimina un anime de la lista personalizada.
     *
     * @param position La posición del anime en la lista.
     */
    public void borraranime(int position) {
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        Anime animeSeleccionado = animes.get(position);
        String tituloAnime = animeSeleccionado.getNombre();
        bd.eliminarDeLista(nombreUsuario, tituloAnime);
        cargarAnimesEnListView();
    }

    /**
     * Muestra un diálogo para ver la página web del anime seleccionado.
     *
     * @param position La posición del anime en la lista.
     */
    private void showDialogWithOptions(int position) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.verpag))
                .setMessage(getString(R.string.seethepage))
                .setIcon(R.drawable.logo)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Anime anime = animes.get(position);
                        if (anime != null) {
                            String url = anime.getLink();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    /**
     * Carga los animes en la lista personalizada.
     */
    private void cargarAnimesEnListView() {
        String username = getIntent().getStringExtra("nombreUsuario");
        animes = bd.obtenerAnimesEnLista(username);
        adapter = new AdaptadorMylist(this, animes);
        milistalv.setAdapter(adapter);
    }
}
