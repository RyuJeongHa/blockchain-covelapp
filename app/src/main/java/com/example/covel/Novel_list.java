package com.example.covel;


import androidx.annotation.Nullable;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class Novel_list extends AppCompatActivity {

    ImageButton list_Back;
    private ArrayList<Novel_list_item> arrayList;
    private RecyclerView novel_list;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_list);
        list_Back=(ImageButton)findViewById(R.id.list_Back);
        novel_list=(RecyclerView)findViewById(R.id.novel_list);


        list_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_home.class);
                startActivity(intent);
            }
        });//뒤로가기

        novel_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Novel_main.class);
                startActivity(intent);
            }
        });//회차로 이동



    }






}