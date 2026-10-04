package com.example.recycleviewapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaPokemonsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Pokemon> lista_pokemons = new ArrayList<Pokemon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pokemons);

        if (savedInstanceState != null){
            lista_pokemons = (ArrayList<Pokemon>) savedInstanceState.getSerializable("lista");
        }else{
            lista_pokemons = (ArrayList<Pokemon>) getIntent().getSerializableExtra("lista_pokemon");
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv = findViewById(R.id.rView);
        Adapter adapter = new Adapter(lista_pokemons); // Novo Adapter

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent it = new Intent(ListaPokemonsActivity.this, PokemonInfoActivity.class);
                it.putExtra("nome", lista_pokemons.get(position).getNome());
                it.putExtra("tipo", lista_pokemons.get(position).getTipo());
                it.putExtra("desc", lista_pokemons.get(position).getDescricao());
                it.putExtra("imagem_url", lista_pokemons.get(position).getImagem());
                startActivity(it);
            }
            @Override
            public void onItemLongClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaPokemonsActivity.this);
                builder.setMessage("Deseja Remover o " + lista_pokemons.get(position).getNome() + " ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lista_pokemons.remove(position);
                        adapter.notifyItemRemoved(position);
                        Toast toast = Toast.makeText(ListaPokemonsActivity.this, lista_pokemons.get(position).getNome() + " Foi Removido!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nao faz nada
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("lista", lista_pokemons);
    }
}