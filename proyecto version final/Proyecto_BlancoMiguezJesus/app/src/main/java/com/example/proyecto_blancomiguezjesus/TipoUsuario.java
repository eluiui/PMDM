package com.example.proyecto_blancomiguezjesus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Actividad que gestiona las distintas acciones de un usuario en la aplicación,
 * como iniciar sesión, registrarse, ver y modificar listas de anime, entre otras.
 */
public class TipoUsuario extends AppCompatActivity {
    private ImageView gifAdmin, imagenselecion;
    private LinearLayout adminLayout, userLayout, loginLayout, signinLayout, verificarlayout, creacionlayout, modificaranimell;
    private Button loginBtn, signinBtn, crearbtn, modificarbtn, crearanimebtn, moodificaranimebtn, mostrarcontraseñabtn, mostrarcontraseñabtnsignin;
    private EditText etcorreo, etpassword, etnombresign, etpasswordsign, verificaradmin, etlink, etnombreanime, etnombreanimenuevo, etsinopsis;
    private AutoCompleteTextView etnombre;
    private ListView listviewadmin;
    private  TextView recogernombreanime;
    private int intentos;
    private MediaPlayer musica;
    private static final int RESPUESTA = 2;
    private Uri imagenUriSeleccionada;
    private static final int RESPUESTA_SELECCION_ARCHIVO = 1;
    BD bd;
    private List<Anime> animes;
    private CheckBox recordarchk;
    AdaptadorAnimeAdmin adapter;
    SharedPreferences sharedPreferences;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipousuario);
        inicializar();
    }

    /**
     * Inicializa los componentes de la interfaz de usuario y configura el estado inicial de la actividad.
     * Este método se utiliza para configurar la interfaz de usuario y establecer el estado inicial de la actividad.
     */
    private void inicializar() {
        recogernombreanime= findViewById(R.id.tvnombreanime);
        etsinopsis = findViewById(R.id.etsinopsis);
        etnombreanimenuevo = findViewById(R.id.etanimenamenuevo);
        modificaranimell = findViewById(R.id.modificaranimell);
        crearanimebtn = findViewById(R.id.crearanime);
        moodificaranimebtn = findViewById(R.id.modificaranimenuevo);
        loginBtn = findViewById(R.id.login);
        signinBtn = findViewById(R.id.signin);
        loginLayout = findViewById(R.id.layoutlogin);
        signinLayout = findViewById(R.id.layoutsignin);
        adminLayout = findViewById(R.id.layoutadmin);
        userLayout = findViewById(R.id.layoutuser);
        gifAdmin = findViewById(R.id.gifadmin);
        etnombre = findViewById(R.id.etnombre);
        etpassword = findViewById(R.id.etpassword);
        etnombresign = findViewById(R.id.etnombresignin);
        etpasswordsign = findViewById(R.id.etpasswordsignin);
        creacionlayout = findViewById(R.id.layoutcreacion);
        etcorreo = findViewById(R.id.etcorreo);
        verificaradmin = findViewById(R.id.verificacionadmin);
        verificarlayout = findViewById(R.id.layoutverificar);
        etnombreanime = findViewById(R.id.etanimename);
        etlink = findViewById(R.id.etlink);
        mostrarcontraseñabtn = findViewById(R.id.mostrarcontraseñabtn);
        mostrarcontraseñabtnsignin = findViewById(R.id.mostrarcontraseñabtnsigin);
        crearbtn = findViewById(R.id.crearadmin);
        modificarbtn = findViewById(R.id.modificaradmin);
        listviewadmin = findViewById(R.id.listviewadmin);
        imagenselecion = findViewById(R.id.imageView);
        recordarchk = findViewById(R.id.recordarchk);
        animes = new ArrayList<>();
        bd = new BD(this);
        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        cargarAnimesEnListView();
        registerForContextMenu(listviewadmin);
        // Configurar visibilidad de la contraseña de sign in
        mostrarcontraseñabtnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    etpasswordsign.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mostrarcontraseñabtnsignin.setBackground(getDrawable(R.drawable.ojocerrado));
                } else {
                    etpasswordsign.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mostrarcontraseñabtnsignin.setBackground(getDrawable(R.drawable.ojoabierto));
                }
                etpassword.setSelection(etpassword.getText().length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
        // Configurar visibilidad de la contraseña de log in
        mostrarcontraseñabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    etpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mostrarcontraseñabtn.setBackground(getDrawable(R.drawable.ojocerrado));
                } else {
                    etpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mostrarcontraseñabtn.setBackground(getDrawable(R.drawable.ojoabierto));
                }
                etpassword.setSelection(etpassword.getText().length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
        // Configurar la interfaz según el tipo de usuario
        int tipo = getIntent().getIntExtra("btn", 0);
        switch (tipo) {
            case 1:
                verificarlayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                userSetup();
                break;
        }
        // Cargar usuarios guardados y configurar el AutoCompleteTextView
        Set<String> usuariosGuardados = sharedPreferences.getStringSet("usuariosGuardados", new HashSet<>());
        List<String> usuariosList = new ArrayList<>(usuariosGuardados);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, usuariosList);
        etnombre.setAdapter(adapter);


        //Restaura las credenciales guardadas (nombre de usuario y contraseña) si la opción de recordar está activada.
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        boolean isChecked = sharedPreferences.getBoolean("isChecked", false);

        etnombre.setText(savedUsername);
        etpassword.setText(savedPassword);
        recordarchk.setChecked(isChecked);

        recordarchk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("isChecked", isChecked).apply();
                if (!isChecked) {
                    sharedPreferences.edit()
                            .remove("username")
                            .remove("password")
                            .apply();
                }
            }
        });
    }

    /**
     * Guarda el nombre de usuario y la contraseña en las preferencias compartidas si la opción de recordar está activada.
     * Si el nombre de usuario y la contraseña no están vacíos y la casilla de recordar está marcada, guarda los datos en las preferencias compartidas.
     */
    private void guardarUsuarioYPass() {
        if (recordarchk.isChecked()) {
            String username = etnombre.getText().toString().trim();
            String password = etpassword.getText().toString().trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                sharedPreferences.edit()
                        .putString("username", username)
                        .putString("password", password)
                        .apply();
                guardarUsuarioEnLista(username);
            }
        }
    }

    /**
     * Guarda el nombre de usuario en una lista de usuarios guardados en las preferencias compartidas.
     *
     * @param username El nombre de usuario que se va a guardar en la lista de usuarios guardados.
     */
    private void guardarUsuarioEnLista(String username) {
        Set<String> usuariosGuardados = sharedPreferences.getStringSet("usuariosGuardados", new HashSet<>());
        usuariosGuardados.add(username);
        sharedPreferences.edit().putStringSet("usuariosGuardados", usuariosGuardados).apply();
    }

    /**
     * Verifica las credenciales de usuario ingresadas y muestra "PaginaListas".
     * Si el nombre de usuario y la contraseña no están vacíos, verifica si las credenciales son correctas. Si lo son,
     * guarda el usuario y la contraseña si la casilla de recordar está marcada, luego muestra la página de listas.
     * Si las credenciales no son correctas, muestra un mensaje de error.
     */
    public void verificaruser() {
        String nombre = etnombre.getText().toString().trim();
        String contraseña = etpassword.getText().toString().trim();

        if (!nombre.isEmpty() && !contraseña.isEmpty()) {
            boolean existe = bd.verificarUsuario(nombre, contraseña);

            if (existe) {
                guardarUsuarioYPass();
                Intent intent = new Intent(TipoUsuario.this, PaginaListas.class);
                intent.putExtra("nombreUsuario", nombre);
                startActivityForResult(intent, RESPUESTA);
                if (!recordarchk.isChecked()) {
                    etnombre.setText("");
                    etpassword.setText("");
                }
            } else {
                muchosfallos();
            }
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.allthecamps), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método llamado cuando se presiona el botón de modificación de anime.
     * Modifica un anime existente en la base de datos con la información ingresada por el usuario.
     *
     * @param view La vista del botón que se ha presionado.
     */
    public void onClickmodificacion(View view) {
        if (imagenUriSeleccionada != null) {
            modificaranime(imagenUriSeleccionada);
            imagenselecion.setImageResource(R.drawable.ponerimagen);
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.selectanimage), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método llamado cuando se crea el menú contextual al mantener presionado un elemento de la lista de animes.
     * Este método infla el menú contextual y establece el título del encabezado como el nombre del anime seleccionado.
     *
     * @param menu     El menú contextual que se va a crear.
     * @param v        La vista que ha activado el menú contextual.
     * @param menuInfo Información sobre la vista que ha activado el menú contextual.
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

        getMenuInflater().inflate(R.menu.menuctx_admin, menu);
    }

    /**
     * Método llamado cuando se selecciona una opción del menú contextual.
     * Este método maneja las acciones a realizar cuando se selecciona una opción del menú contextual.
     *
     * @param item El ítem del menú contextual seleccionado.
     * @return true si el evento ha sido procesado correctamente, false de lo contrario.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.modificar:
                recogernombreanime.setVisibility(View.VISIBLE);
                etnombreanime.setVisibility(View.GONE);
                Anime anime = animes.get(position);
                recogernombreanime.setText(anime.getNombre());
                etlink.setText(anime.getLink());
                etsinopsis.setText(anime.getSinopsis());
                imagenselecion.setImageBitmap(anime.getImagen());
                CrearModificarvisibility(true);
                VisibilityCrearModificarbtn(true);
                return true;
            case R.id.borrar:
                dialogodeborrar(position);
                return true;
            case R.id.animepageadmin:
                showDialogPage(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Cambia la visibilidad de la interfaz de usuario para crear o modificar un anime.
     *
     * @param mostrar true para mostrar la interfaz de modificación, false para mostrar la lista de animes.
     */
    private void VisibilityCrearModificarbtn(boolean mostrar) {
        if (mostrar) {
            moodificaranimebtn.setVisibility(View.VISIBLE);
            crearanimebtn.setVisibility(View.GONE);
            modificaranimell.setVisibility(View.VISIBLE);

        } else {
            moodificaranimebtn.setVisibility(View.GONE);
            crearanimebtn.setVisibility(View.VISIBLE);
            modificaranimell.setVisibility(View.GONE);
        }
    }

    /**
     * Muestra una notificación sobre el cambio de nombre de un anime.
     *
     * @param nombreAnime El nombre original del anime.
     * @param nuevoNombre El nuevo nombre del anime.
     */
    public void notificacionCambionombre(String nombreAnime, String nuevoNombre) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            Bitmap icono = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                    .setLargeIcon(icono)
                    .setSmallIcon(android.R.drawable.star_on)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(nombreAnime + getString(R.string.cambionombreanime) + nuevoNombre + getString(R.string.añadiralalistaotravez))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            notificationManager.notify(0, builder.build());
        }
    }

    /**
     * Modifica un anime existente en la base de datos con la información proporcionada.
     * Si la URI de la imagen, el nombre y el enlace del anime no están vacíos, se procede a modificar el anime en la base de datos.
     * Se muestra una notificación si se cambia el nombre del anime.
     * Si hay algún error al leer la imagen desde la URI o si falta algún campo, se muestra un mensaje de error.
     *
     * @param imagenUri La URI de la imagen seleccionada para el anime.
     */
    public void modificaranime(Uri imagenUri) {
        String nombre = recogernombreanime.getText().toString().trim();
        String nombrenuevo = etnombreanimenuevo.getText().toString().trim();
        String sinopsis = etsinopsis.getText().toString().trim();
        String link = etlink.getText().toString().trim();

        if (imagenUri != null && !nombre.isEmpty() && !link.isEmpty() && !nombrenuevo.isEmpty()) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(imagenUri);
                byte[] imagenBytes = new byte[inputStream.available()];
                inputStream.read(imagenBytes);
                inputStream.close();
                if (!nombre.equals(nombrenuevo)) {
                    notificacionCambionombre(nombre, nombrenuevo);
                }
                bd.modificarAnime(nombre, nombrenuevo, sinopsis, link, imagenBytes);
                etnombreanime.setVisibility(View.VISIBLE);
                recogernombreanime.setVisibility(View.GONE);
                recogernombreanime.setText("");
                etnombreanimenuevo.setText("");
                etsinopsis.setText("");
                etlink.setText("");
                modificaranimell.setVisibility(View.GONE);
                CrearModificarvisibility(false);
                cargarAnimesEnListView();

            } catch (IOException e) {
                Toast.makeText(TipoUsuario.this, getString(R.string.errorintheimage), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.allthecamps), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Muestra un diálogo de confirmación antes de borrar un elemento en una determinada posición de la lista.
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
                        Anime animeBorrar = animes.get(position);
                        borrarAnime(animeBorrar.getNombre());
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
     * Muestra un diálogo con opciones antes de abrir la página web del anime.
     *
     * @param position La posición del anime en la lista.
     */
    private void showDialogPage(int position) {
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
     * Método para borrar un anime de la base de datos y recargar la lista de animes en la interfaz.
     *
     * @param nombreAnime El nombre del anime a borrar.
     */
    public void borrarAnime(String nombreAnime) {
        bd.borrarAnime(nombreAnime);
        cargarAnimesEnListView();
    }

    /**
     * Método para cargar todos los animes de la base de datos en el ListView de administrador.
     */
    private void cargarAnimesEnListView() {
        animes = bd.obtenerTodosAnimes();
        adapter = new AdaptadorAnimeAdmin(this, animes);
        listviewadmin.setAdapter(adapter);
    }

    /**
     * Método para verificar si el administrador ha ingresado la contraseña correcta.
     *
     * @return true si la contraseña del administrador es correcta, false de lo contrario.
     */
    public boolean verificaradministrador() {
        return verificaradmin.getText().toString().trim().equals("renaido");
    }

    /**
     * Método llamado cuando se hace clic en algún elemento de la interfaz de usuario.
     *
     * @param view La vista que ha sido clicada.
     */
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                abrirSelectorArchivo();
                break;
            case R.id.verificacionadminbtn:
                if (verificaradministrador()) {
                    verificarlayout.setVisibility(View.GONE);
                    adminSetup();
                } else {
                    muchosfallos();
                }
                verificaradmin.setText("");
                break;
            case R.id.crearadmin:
                CrearModificarvisibility(true);
                VisibilityCrearModificarbtn(false);
                break;
            case R.id.crearanime:
                if (imagenUriSeleccionada != null) {
                    insertarAnime(imagenUriSeleccionada);
                    cargarAnimesEnListView();
                    imagenselecion.setImageResource(R.drawable.ponerimagen);
                } else {
                    Toast.makeText(TipoUsuario.this, getString(R.string.selectanimage), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.modificaradmin:
                CrearModificarvisibility(false);
                break;
            case R.id.saliradmin:
                showDialogGobackAdmin();
                break;
            case R.id.saliruser:
                showDialogGoback();
                break;
            case R.id.login:
                LogSignvisibility(true);
                break;
            case R.id.signin:
                LogSignvisibility(false);
                break;
            case R.id.enterlogin:
                verificaruser();
                break;
            case R.id.entersignin:
                insertarusuario();
                break;
        }
    }

    /**
     * Método para cambiar la visibilidad de la interfaz de usuario para crear o modificar un anime.
     * Si esta es true, muestra la interfaz de creación de anime; de lo contrario, muestra la lista de animes.
     *
     * @param esta true para mostrar la interfaz de creación, false para mostrar la lista de animes.
     */
    private void CrearModificarvisibility(boolean esta) {
        if (esta) {
            creacionlayout.setVisibility(View.VISIBLE);
            listviewadmin.setVisibility(View.GONE);
        } else {
            listviewadmin.setVisibility(View.VISIBLE);
            creacionlayout.setVisibility(View.GONE);
        }
        crearbtn.setVisibility(View.GONE);
        modificarbtn.setVisibility(View.GONE);
    }

    /**
     * Método para abrir el selector de archivos para seleccionar una imagen.
     * El usuario puede seleccionar una imagen de la galería de su dispositivo.
     */
    private void abrirSelectorArchivo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, RESPUESTA_SELECCION_ARCHIVO);
    }

    /**
     * Método llamado cuando se recibe el resultado de una actividad iniciada para obtener una imagen.
     * Aquí se maneja la respuesta de la actividad de selección de imagen.
     *
     * @param requestCode El código de solicitud de la actividad.
     * @param resultCode  El código de resultado de la actividad.
     * @param data        La intención que contiene los datos devueltos por la actividad.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA_SELECCION_ARCHIVO) {
            if (resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    imagenselecion.setImageURI(uri);
                    imagenUriSeleccionada = uri;
                }
            }
        } else if (requestCode == RESPUESTA) {
            if (resultCode == 4) {
                boolean resultOk = data.getBooleanExtra("resultOk", false);
                if (resultOk) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("salir", true);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        }
    }

    /**
     * Método para insertar un nuevo anime en la base de datos con la información proporcionada.
     * Se valida que se haya seleccionado una imagen y que los campos del nombre y el enlace no estén vacíos.
     * Si se cumplen las condiciones, se inserta el anime en la base de datos.
     *
     * @param imagenUri La URI de la imagen seleccionada para el anime.
     */
    public void insertarAnime(Uri imagenUri) {
        String nombre = etnombreanime.getText().toString().trim();
        String sinopsis = etsinopsis.getText().toString().trim();
        String link = etlink.getText().toString().trim();

        if (imagenUri != null && !nombre.isEmpty() && !link.isEmpty()) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(imagenUri);
                byte[] imagenBytes = new byte[inputStream.available()];
                inputStream.read(imagenBytes);
                inputStream.close();

                bd.insertarAnime(nombre, sinopsis, link, imagenBytes);
                notificacion(nombre);
                etnombreanime.setText("");
                etsinopsis.setText("");
                etlink.setText("");

            } catch (IOException e) {
                Toast.makeText(TipoUsuario.this, getString(R.string.errorintheimage), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.allthecamps), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para mostrar una notificación cuando se inserta un nuevo anime en la base de datos.
     * La notificación incluye el nombre del anime insertado.
     *
     * @param nombreAnime El nombre del anime insertado.
     */
    public void notificacion(String nombreAnime) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            Bitmap icono = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                    .setLargeIcon(icono)
                    .setSmallIcon(android.R.drawable.star_on)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(getString(R.string.notificacion) + nombreAnime)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            notificationManager.notify(0, builder.build());
        }
    }

    /**
     * Método para insertar un nuevo usuario en la base de datos y realizar la validación de los campos de entrada.
     * Si el correo electrónico no es válido o el nombre de usuario ya existe en la base de datos, muestra mensajes de error.
     * Si todos los campos son válidos, inserta el nuevo usuario en la base de datos.
     */
    public void insertarusuario() {
        String nombre = etnombresign.getText().toString().trim();
        String contraseña = etpasswordsign.getText().toString().trim();
        String correo = etcorreo.getText().toString().trim();

        if (!nombre.isEmpty() && !contraseña.isEmpty() && !correo.isEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, getString(R.string.correoincalida), Toast.LENGTH_SHORT).show();
                etcorreo.setText("");
            } else if (bd.existeUsuario(nombre)) {
                Toast.makeText(this, getString(R.string.usernameerror), Toast.LENGTH_SHORT).show();
            } else {
                bd.insertarUsuario(nombre, contraseña, correo);
                LogSignvisibility(true);
                recordarchk.setChecked(false);
                vaciar();
                etnombresign.setText("");
                etpasswordsign.setText("");
                etcorreo.setText("");
            }
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.allthecamps), Toast.LENGTH_SHORT).show();
            etnombresign.setText("");
            etpasswordsign.setText("");
            etcorreo.setText("");
        }
    }

    /**
     * Método para vaciar los campos de nombre y contraseña.
     */
    public void vaciar() {
        etnombre.setText("");
        etpassword.setText("");
    }

    /**
     * Método para manejar el caso en el que se ingresan muchas credenciales incorrectas.
     * Muestra un diálogo de advertencia y finaliza la actividad si se supera el número máximo de intentos.
     */
    private void muchosfallos() {
        intentos++;
        if (intentos >= 3) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                playAudio(R.raw.ahahah);
            }
            showDialogFails();
        } else {
            Toast.makeText(TipoUsuario.this, getString(R.string.pswordandusererror), Toast.LENGTH_SHORT).show();
            vaciar();
        }
    }

    /**
     * Reproduce el audio asociado a un fallo específico.
     *
     * @param audioid Recurso de audio que se reproducirá.
     */
    private void playAudio(int audioid) {
        stopAndReleaseMediaPlayer();
        musica = MediaPlayer.create(this, audioid);
        if (musica != null) {
            musica.start();
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
     * Muestra un diálogo de advertencia con un botón de opción cuando se excede el número de fallos.
     */
    private void showDialogFails() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setIcon(R.drawable.warning)
                .setMessage(getString(R.string.fallos))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    /**
     * Cambia la visibilidad de los componentes de inicio de sesión y registro.
     *
     * @param esta True si se va a mostrar el diseño de inicio de sesión, False si se va a mostrar el diseño de registro.
     */
    private void LogSignvisibility(boolean esta) {
        if (esta) {
            loginLayout.setVisibility(View.VISIBLE);
            signinLayout.setVisibility(View.GONE);
            loginBtn.setVisibility(View.GONE);
            signinBtn.setVisibility(View.VISIBLE);
        } else {
            signinLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
            signinBtn.setVisibility(View.GONE);
        }
    }

    /**
     * Muestra un diálogo de advertencia con opciones al intentar retroceder en la interfaz de administrador.
     */
    private void showDialogGobackAdmin() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setIcon(R.drawable.warning)
                .setMessage(getString(R.string.goback))
                .setNeutralButton(getString(R.string.GotoHome), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (creacionlayout.getVisibility() == View.VISIBLE |
                                listviewadmin.getVisibility() == View.VISIBLE) {
                            creacionlayout.setVisibility(View.GONE);
                            listviewadmin.setVisibility(View.GONE);
                            imagenselecion.setImageResource(R.drawable.ponerimagen);
                            etnombreanime.setText("");
                            etnombreanime.setVisibility(View.VISIBLE);
                            recogernombreanime.setVisibility(View.GONE);
                            recogernombreanime.setText("");
                            etlink.setText("");
                            etsinopsis.setText("");
                            etnombreanimenuevo.setText("");
                            crearbtn.setVisibility(View.VISIBLE);
                            modificarbtn.setVisibility(View.VISIBLE);
                        } else {
                            showDialogDaleGoBack();
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
     * Muestra un diálogo personalizado al intentar retroceder en ciertos escenarios.
     */
    private void showDialogDaleGoBack() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialogopersonalizado, null);
        ImageView gifImageView = dialogLayout.findViewById(R.id.gifdialog);
        Glide.with(this).asGif().load(R.raw.sorry).into(gifImageView);
        TextView messageTextView = dialogLayout.findViewById(R.id.messagetv);
        messageTextView.setText(getString(R.string.youarein));

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setIcon(R.drawable.warning)
                .setView(dialogLayout)
                .show();
    }

    /**
     * Muestra un diálogo de advertencia al intentar retroceder en la actividad.
     */
    private void showDialogGoback() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setIcon(R.drawable.warning)
                .setMessage(getString(R.string.goback))
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
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
     * Configura la interfaz de usuario para un administrador.
     */
    private void adminSetup() {
        adminLayout.setVisibility(View.VISIBLE);
        userLayout.setVisibility(View.GONE);
        Glide.with(this).asGif().load(R.raw.gilgames).into(gifAdmin);
    }

    /**
     * Configura la interfaz de usuario para un usuario normal.
     */
    private void userSetup() {
        adminLayout.setVisibility(View.GONE);
        userLayout.setVisibility(View.VISIBLE);
    }
}