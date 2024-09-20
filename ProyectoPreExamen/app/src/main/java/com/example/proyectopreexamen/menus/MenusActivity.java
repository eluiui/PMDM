package com.example.proyectopreexamen.menus;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopreexamen.MainActivity;
import com.example.proyectopreexamen.R;

public class MenusActivity extends AppCompatActivity {

    private TextView tvContextMenu;
    private ListView listViewContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus_activity);

        tvContextMenu = findViewById(R.id.tvContextMenu);

        listViewContextMenu = findViewById(R.id.listViewContextMenu);

        //ASOCIA EL MENÚ CONTEXTUAL A LA TEXTVIEW
        registerForContextMenu(tvContextMenu);

        //ASOCIA EL MENÚ A LA LISTVIEW
        registerForContextMenu(listViewContextMenu);

    }


    //CREA EL MENÚ CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        String titulo;

        // PARA QUE RECONOZCA QUE ELEMENTO HAY SELECCIONADO EN LA LISTVIEW

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        try {
            titulo=listViewContextMenu.getAdapter().getItem(info.position).toString();
        } catch (Exception e) {
            titulo="Menú Contextual";
        }
        menu.setHeaderTitle(titulo);

        //FIN DE COMPROBACION DE LA LISTVIEW

        inflater.inflate(R.menu.contextmenu, menu);

    }


    //ESCUCHA EL ITEM SELECCIONADO EN EL MENÚ CONTEXTUAL
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.opc1:

                MainActivity.sacarToast(this, String.format("Has seleccionado %s", getString(R.string.strOpc1)));
                break;

            case R.id.opc2:
                MainActivity.sacarToast(this, String.format("Has seleccionado %s", getString(R.string.strOpc2)));
                break;

        }

        return super.onContextItemSelected(item);
    }


    //CREA EL MENÚ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);
    }


    //ESCUCHA EL ITEM SELECCIONADO EN EL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.opc1:

                MainActivity.sacarToast(this, String.format("Has pulsado %s", getString(R.string.strOpc1)));
                break;

            case R.id.opc2:

                MainActivity.sacarToast(this, String.format("Has pulsado %s", getString(R.string.strOpc2)));
                break;

        }

        return super.onOptionsItemSelected(item);
    }



    //FINALIZA LA ACTIVITY
    public void onClickBtnMenu(View view) {

        if (view.getId() == R.id.btnAtrasMenu){

            Intent intent = MainActivity.finalizarActivityResult("Has salido desde Menús");

            setResult(RESULT_OK, intent);


            finish();

        }

    }
}