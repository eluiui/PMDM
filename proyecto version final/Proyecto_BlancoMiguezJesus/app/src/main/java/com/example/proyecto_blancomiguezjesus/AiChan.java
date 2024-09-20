package com.example.proyecto_blancomiguezjesus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * La clase AiChan es una actividad que representa un asistente virtual simple.
 * Permite al usuario introducir texto y recibir una respuesta basada en el texto introducido.
 * Utiliza Glide para cargar una imagen GIF y muestra un saludo personalizado basado en el nombre del usuario.
 * La clase incluye varios métodos para inicializar componentes, gestionar entradas de texto y proporcionar respuestas.
 */
public class AiChan extends AppCompatActivity {

    private EditText escuchando;
    private TextView respuesta;
    private ImageView ayudaiv;
    private ImageView asistant;
    private ArrayList<Respuestas> respuestas;

    /**
     * Método onCreate se llama cuando la actividad es creada.
     *
     * @param savedInstanceState Estado de la instancia guardada de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_chan);
        inicializar();
    }

    /**
     * Método inicializar se encarga de configurar los componentes de la interfaz de usuario.
     * Inicializa los componentes de la actividad, carga el GIF animado en el ImageView del asistente
     * y obtiene el nombre del usuario para mostrar un saludo personalizado.
     */
    private void inicializar() {
        escuchando = findViewById(R.id.etEscuchado);
        respuesta = findViewById(R.id.tvRespuesta);
        asistant = findViewById(R.id.asistant);
        ayudaiv = findViewById(R.id.demostracion);
        Glide.with(this).asGif().load(R.raw.neko).into(asistant);
        respuestas = proveerDatos();
        nombreuser();
    }

    /**
     * Método nombreuser obtiene el nombre del usuario desde el Intent y lo muestra en el TextView de respuesta.
     * Si no se proporciona un nombre, no se muestra ningún saludo personalizado.
     */
    public void nombreuser() {
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        if (nombreUsuario != null) {
            respuesta.setText(getString(R.string.saludo) + " " + nombreUsuario);
        }
    }

    /**
     * Método enviarTexto se llama cuando se hace clic en un botón para enviar el texto introducido.
     *
     * @param v La vista que activó este método (normalmente un botón).
     *          Obtiene el texto del EditText, lo convierte a minúsculas y prepara la respuesta.
     */
    public void enviarTexto(View v) {
        String textoIntroducido = escuchando.getText().toString().toLowerCase();
        prepararRespuesta(textoIntroducido);
    }

    /**
     * Método prepararRespuesta toma el texto introducido y genera una respuesta.
     *
     * @param textoIntroducido El texto introducido por el usuario.
     *                         Llama al método obtenerRespuesta para obtener la respuesta adecuada y la muestra en el TextView de respuesta.
     */
    private void prepararRespuesta(String textoIntroducido) {
        String respuestaTexto = obtenerRespuesta(textoIntroducido);
        respuesta.setText(respuestaTexto);
    }

    /**
     * Este método busca una respuesta adecuada basada en el texto introducido por el usuario.
     * Recorre la lista de respuestas predefinidas y verifica si el texto introducido contiene
     * alguna de las preguntas conocidas. Si encuentra una coincidencia, devuelve la respuesta correspondiente
     * y muestra una imagen de ayuda relacionada si es necesario. Si no encuentra ninguna coincidencia,
     * muestra una imagen GIF por defecto.
     *
     * @param textoIntroducido El texto introducido por el usuario.
     * @return La respuesta adecuada basada en el texto introducido.
     */
    private String obtenerRespuesta(String textoIntroducido) {
        String respuestaTexto = respuestas.get(0).getRespuestas();
        boolean found = false;

        for (Respuestas respuesta : respuestas) {
            if (textoIntroducido.toLowerCase().contains(respuesta.getCuestion().toLowerCase())) {
                String cuestion = respuesta.getCuestion().toLowerCase();
                if (cuestion.equals(getString(R.string.agregar_anime).toLowerCase()) ||
                        cuestion.equals(getString(R.string.introducir_anime).toLowerCase()) ||
                        cuestion.equals(getString(R.string.anadir_anime).toLowerCase())) {
                    ayudaiv.setVisibility(View.VISIBLE);
                    ayudaiv.setImageResource(R.drawable.add);
                    respuestaTexto = respuesta.getRespuestas();
                } else if (cuestion.equals(getString(R.string.eliminar_anime).toLowerCase()) ||
                        cuestion.equals(getString(R.string.borrar_anime).toLowerCase())) {
                    ayudaiv.setVisibility(View.VISIBLE);
                    ayudaiv.setImageResource(R.drawable.delete);
                    respuestaTexto = respuesta.getRespuestas();
                } else {
                    ayudaiv.setVisibility(View.GONE);
                    respuestaTexto = respuesta.getRespuestas();
                }
                found = true;
                break;
            }
        }

        if (!found) {
            ayudaiv.setVisibility(View.VISIBLE);
            Glide.with(this).asGif().load(R.raw.gif).into(ayudaiv);
        }
        return respuestaTexto;
    }

    /**
     * Este método proporciona una lista de respuestas predefinidas que el asistente puede utilizar
     * para responder a las preguntas del usuario. Cada respuesta está asociada a una pregunta específica.
     *
     * @return Una lista de objetos Respuestas, donde cada uno contiene una pregunta y una respuesta asociada.
     */
    public ArrayList<Respuestas> proveerDatos() {
        ArrayList<Respuestas> respuestas = new ArrayList<>();
        respuestas.add(new Respuestas("defecto", getString(R.string.respuesta_defecto)));
        respuestas.add(new Respuestas(getString(R.string.saludo), getString(R.string.respuesta_hola)));
        respuestas.add(new Respuestas("hi", getString(R.string.respuesta_hola)));
        respuestas.add(new Respuestas(getString(R.string.bien), getString(R.string.respuesta_bien)));
        respuestas.add(new Respuestas(getString(R.string.adios), getString(R.string.respuesta_adios)));
        respuestas.add(new Respuestas(getString(R.string.como_estas), getString(R.string.respuesta_como_estas)));
        respuestas.add(new Respuestas(getString(R.string.nombreai), getString(R.string.respuesta_nombre)));
        respuestas.add(new Respuestas(getString(R.string.llamas), getString(R.string.respuesta_llamas)));
        respuestas.add(new Respuestas(getString(R.string.eliminar_anime), getString(R.string.respuesta_borrar_anime)));
        respuestas.add(new Respuestas(getString(R.string.borrar_anime), getString(R.string.respuesta_borrar_anime)));
        respuestas.add(new Respuestas(getString(R.string.agregar_anime), getString(R.string.respuesta_agregar_anime)));
        respuestas.add(new Respuestas(getString(R.string.introducir_anime), getString(R.string.respuesta_agregar_anime)));
        respuestas.add(new Respuestas(getString(R.string.anadir_anime), getString(R.string.respuesta_agregar_anime)));
        respuestas.add(new Respuestas(getString(R.string.musica), getString(R.string.respuesta_musica)));
        respuestas.add(new Respuestas(getString(R.string.admin), getString(R.string.admin_respuesta)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_que_haces), getString(R.string.respuesta_que_haces)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_cual_es_tu_funcion), getString(R.string.respuesta_cual_es_tu_funcion)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_quien_te_creo), getString(R.string.respuesta_quien_te_creo)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_cual_es_tu_color_favorito), getString(R.string.respuesta_cual_es_tu_color_favorito)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_que_opinas_de_la_inteligencia_artificial), getString(R.string.respuesta_que_opinas_de_la_inteligencia_artificial)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_puedes_contarme_un_chiste), getString(R.string.respuesta_puedes_contarme_un_chiste)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_cual_es_tu_pelicula_favorita), getString(R.string.respuesta_cual_es_tu_pelicula_favorita)));
        respuestas.add(new Respuestas(getString(R.string.pregunta_que_tal_tu_dia), getString(R.string.respuesta_que_tal_tu_dia)));
        respuestas.add(new Respuestas("  ", "?"));
        return respuestas;
    }
}