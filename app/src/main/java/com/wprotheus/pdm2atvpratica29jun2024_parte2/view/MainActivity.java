package com.wprotheus.pdm2atvpratica29jun2024_parte2.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.wprotheus.pdm2atvpratica29jun2024_parte2.R;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.databinding.ActivityMainBinding;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.ui.HomeFragment;
import com.wprotheus.pdm2atvpratica29jun2024_parte2.ui.VisualizarFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private HomeFragment hFragment;
    private VisualizarFragment vFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        hFragment = new HomeFragment();
        vFragment = new VisualizarFragment();
        listenerFragmentos();
        alternandoFragmentos(hFragment);
    }

    private void listenerFragmentos() {
        mBinding.btnHome.setOnClickListener(v -> alternandoFragmentos(hFragment));
        mBinding.btnVisualizar.setOnClickListener(v -> alternandoFragmentos(vFragment));
    }

    private void alternandoFragmentos(Fragment destino) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragmentos, destino).commit();
    }
}