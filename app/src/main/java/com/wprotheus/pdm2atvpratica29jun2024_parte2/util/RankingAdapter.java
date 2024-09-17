package com.wprotheus.pdm2atvpratica29jun2024_parte2.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wprotheus.pdm2atvpratica29jun2024_parte2.R;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.model.Partida;

import java.util.List;

public class RankingAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Partida> partidaList;

    public RankingAdapter(Context context, List<Partida> partidaList) {
        this.inflater = LayoutInflater.from(context);
        this.partidaList = partidaList;
    }

    @Override
    public int getCount() {
        return partidaList.size();
    }

    @Override
    public Object getItem(int position) {
        return partidaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Partida p = partidaList.get(position);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.ranking_layout, null);

        ((TextView) convertView.findViewById(R.id.tvPartida)).setText(p.getId());
        ((TextView) convertView.findViewById(R.id.tvNomeJogador)).setText(p.getNome());
        ((TextView) convertView.findViewById(R.id.tvNumerosGerados)).setText(p.getNumeros());
        ((TextView) convertView.findViewById(R.id.tvSomaNumeros)).setText(p.getSoma());
        ((TextView) convertView.findViewById(R.id.tvResultado)).setText(p.getResultado());
        int img = Integer.parseInt(p.getImagem());
        if (img == 0)
            ((ImageView) convertView.findViewById(R.id.ivAcertouErrou)).setImageResource(R.drawable.gatoerrou);
        else
            ((ImageView) convertView.findViewById(R.id.ivAcertouErrou)).setImageResource(R.drawable.gatoacertou);
        return convertView;
    }
}