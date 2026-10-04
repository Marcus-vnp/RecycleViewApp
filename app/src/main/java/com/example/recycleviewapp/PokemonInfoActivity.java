package com.example.recycleviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class PokemonInfoActivity extends AppCompatActivity {

    TextView nome;
    TextView tipo;
    TextView desc;
    ImageView imagem;
    Button bVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokemon_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nome = findViewById(R.id.nomePok);
        tipo = findViewById(R.id.tipoPok);
        desc = findViewById(R.id.descricaoPok);
        imagem = findViewById(R.id.imagemPok);
        bVoltar = findViewById(R.id.bvoltar);

        Intent it = getIntent();

        nome.setText(it.getStringExtra("nome"));
        tipo.setText("Tipo: " + it.getStringExtra("tipo"));
        desc.setText(it.getStringExtra("desc"));
        Glide.with(this).load(it.getStringExtra("imagem_url")).into(imagem);
    }

    public void Voltar(View view){
        Intent it = new Intent(PokemonInfoActivity.this, ListaPokemonsActivity.class);
        startActivity(it);
    }
}