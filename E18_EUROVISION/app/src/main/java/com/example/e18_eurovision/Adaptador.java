package com.example.e18_eurovision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adaptador extends ArrayAdapter<Eurovision> {

    private Context context;
    private List<Eurovision> listaResultados;

    private static class ViewHolder {
        TextView tvPais,tvInterprete,tvCancion,tvVotosJurado,tvVotosAudiencia,tvTotalVotos;
    }

    public Adaptador(Context context, List<Eurovision> listaResultados) {
        super(context, R.layout.adaptador, listaResultados);
        this.context = context;
        this.listaResultados = listaResultados;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adaptador, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvPais = convertView.findViewById(R.id.tvPais);
            viewHolder.tvInterprete = convertView.findViewById(R.id.tvInterprete);
            viewHolder.tvCancion = convertView.findViewById(R.id.tvCancion);
            viewHolder.tvVotosJurado = convertView.findViewById(R.id.tvVotosJurado);
            viewHolder.tvVotosAudiencia = convertView.findViewById(R.id.tvVotosAudiencia);
            viewHolder.tvTotalVotos=convertView.findViewById(R.id.tvTotalVotos);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Eurovision resultado = listaResultados.get(position);
        viewHolder.tvPais.setText(resultado.getPais());
        viewHolder.tvInterprete.setText(resultado.getInterprete());
        viewHolder.tvCancion.setText(resultado.getCancion());
        viewHolder.tvVotosJurado.setText(String.valueOf(resultado.getVotosJurado()));
        viewHolder.tvVotosAudiencia.setText(String.valueOf(resultado.getVotosAudiencia()));
        viewHolder.tvTotalVotos.setText(String.valueOf(resultado.getVotosJurado()+resultado.getVotosAudiencia()));

        return convertView;
    }
}