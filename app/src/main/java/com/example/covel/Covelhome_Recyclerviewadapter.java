package com.example.covel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Covelhome_Recyclerviewadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private String[] novel = {"소설1", "소설2", "소설3", "소설4", "소설5"};

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
