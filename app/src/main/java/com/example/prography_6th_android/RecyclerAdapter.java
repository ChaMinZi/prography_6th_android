package com.example.prography_6th_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Movie> movies = null;

    public RecyclerAdapter(ArrayList<Movie> movieList) {
        movies = movieList;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, View v, int positon);
    }
    private OnItemClickListener itemClickListener = null;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieIdTextView;
        TextView titleTextView;
        TextView directorTextView;
        TextView release_dateTextView;

        OnItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieIdTextView = itemView.findViewById(R.id.movie_id);
            titleTextView = itemView.findViewById(R.id.title);
            directorTextView = itemView.findViewById(R.id.director);
            release_dateTextView = itemView.findViewById(R.id.release_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.itemClickListener = listener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.movielist_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);

        return (ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieIdTextView.setText(Integer.toString(position+1)+". ");
        holder.titleTextView.setText(movies.get(position).getTitle());
        holder.directorTextView.setText("Director : "+movies.get(position).getDirector());
        holder.release_dateTextView.setText("Release Date : "+movies.get(position).getReleaseDate());

        holder.setOnItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
