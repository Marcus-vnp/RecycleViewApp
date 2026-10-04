package com.example.recycleviewapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private ArrayList<Pokemon> pokemonLista;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public Adapter(ArrayList<Pokemon> list) {
        this.pokemonLista = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listalayout, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tNome.setText(pokemonLista.get(position).getNome());
        holder.tTipo.setText(pokemonLista.get(position).getTipo());
        holder.tDescricao.setText(pokemonLista.get(position).getDescricao());
        Glide.with (holder.itemView.getContext()).load(pokemonLista.get( position).getImagem()).into(holder.imagePokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonLista.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "RecycleViewapp";
        TextView tNome;
        TextView tTipo;
        TextView tDescricao;
        ImageView imagePokemon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tNome = itemView.findViewById(R.id.nome);
            tTipo = itemView.findViewById(R.id.tipo);
            tDescricao = itemView.findViewById(R.id.descricao);
            imagePokemon = itemView.findViewById(R.id.imagem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
}
