package com.wprotheus.pdm2atvpratica29jun2024_parte2.ui;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.wprotheus.pdm2atvpratica29jun2024_parte2.R;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.model.Partida;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.util.RankingAdapter;

import java.util.ArrayList;
import java.util.List;

public class VisualizarFragment extends Fragment {
    static final String PROVIDER_NAME = "com.wprotheus.pdm2atvpratica29jun2024";
    static final String URL = "content://" + PROVIDER_NAME + "/tb_partida";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private ListView lvPartidas;
    private RankingAdapter adapter;
    private List<Partida> partidas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizar, container, false);
        lvPartidas = view.findViewById(R.id.lvRanking);
        partidas = new ArrayList<>();
        adapter = new RankingAdapter(requireContext(), partidas);
        lvPartidas.setAdapter(adapter);
        solicitarPermissoes();
        return view;
    }

    public void acessarDados() {
        ContentResolver resolver = requireContext().getContentResolver();
        Cursor cursor = resolver.query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Partida p = new Partida();
                p.setId(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("ID"))));
                p.setNome(cursor.getString(cursor.getColumnIndexOrThrow("NOME")));
                p.setNumeros(cursor.getString(cursor.getColumnIndexOrThrow("NUMEROS")));
                p.setSoma(cursor.getString(cursor.getColumnIndexOrThrow("SOMA")));
                p.setResultado(cursor.getString(cursor.getColumnIndexOrThrow("RESULTADO")));
                p.setImagem(cursor.getString(cursor.getColumnIndexOrThrow("IMAGEM")));
                partidas.add(p);
            }
            cursor.close();
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(requireActivity(), "Erro ao carregar as partidas.", Toast.LENGTH_SHORT).show();
        }
    }

    public void solicitarPermissoes() {
        if (ContextCompat.checkSelfPermission(requireActivity(), "com.wprotheus.pdm2atvpratica29jun2024.PERMISSION_READ")
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(requireActivity(), "com.wprotheus.pdm2atvpratica29jun2024.PERMISSION_WRITE")
                        != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{"com.wprotheus.pdm2atvpratica29jun2024.PERMISSION_READ",
                            "com.wprotheus.pdm2atvpratica29jun2024.PERMISSION_WRITE"}, 100);
        else
            acessarDados();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                acessarDados();
            else
                Toast.makeText(requireActivity(), "Permissões necessárias não foram concedidas.", Toast.LENGTH_SHORT).show();
        }
    }
}