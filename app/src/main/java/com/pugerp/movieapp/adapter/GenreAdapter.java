package com.pugerp.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pugerp.movieapp.data.GenresItem;
import com.pugerp.movieapp.databinding.IGenreBinding;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {

    private List<GenresItem> data;
    private GenreListener listener;

    public GenreAdapter(List<GenresItem> data, GenreListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull IGenreBinding binding = IGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GenreHolder extends RecyclerView.ViewHolder {

        IGenreBinding binding;

        public GenreHolder(@NonNull IGenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GenresItem item) {
            binding.tvGenre.setText(item.getName());
            binding.tvGenre.setOnClickListener(view -> listener.onClicked(item));
        }
    }

    public interface GenreListener {
        void onClicked(GenresItem item);
    }
}
