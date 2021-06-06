package com.example.covel;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Covelhome_Recyclerviewadapter extends RecyclerView.Adapter<Covelhome_Recyclerviewadapter.CustomViewHolder>{

    private ArrayList<Covel_home_item> arrayList;

    public Covelhome_Recyclerviewadapter(ArrayList<Covel_home_item> arrayList){
        this.arrayList = arrayList;
    }

     /*public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView novel_cover;
        public TextView novel_name;
        public TextView novelist_name;
        public TextView novel_explain;

        public ViewHolder(View itemView) {
            super(itemView);
            novel_cover = (ImageView) itemView.findViewById(R.id.novel_cover);
            novel_name = (TextView) itemView.findViewById(R.id.novel_name);
            novelist_name = (TextView) itemView.findViewById(R.id.novelist_name);
            novel_explain = (TextView) itemView.findViewById(R.id.novel_explain);

        }
    }*/

    @NonNull
    @Override
    public Covelhome_Recyclerviewadapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.covel_home_item,parent, false);
        CustomViewHolder holder = new CustomViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Covelhome_Recyclerviewadapter.CustomViewHolder holder, int position) {
        holder.novel_cover.setImageResource(arrayList.get(position).getNovel_cover());
        holder.novel_name.setText(arrayList.get(position).getNovel_name());
        holder.novelist_name.setText(arrayList.get(position).getNovel_name());
        holder.novel_explain.setText(arrayList.get(position).getNovel_explain());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String curName = holder.novel_name.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        protected ImageView novel_cover;
        protected TextView novel_name;
        protected TextView novelist_name;
        protected TextView novel_explain;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.novel_cover=(ImageView) itemView.findViewById(R.id.novel_cover);
            this.novel_name=(TextView) itemView.findViewById(R.id.novel_name);
            this.novelist_name=(TextView) itemView.findViewById(R.id.novelist_name);
            this.novel_explain=(TextView) itemView.findViewById(R.id.novel_explain);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

}