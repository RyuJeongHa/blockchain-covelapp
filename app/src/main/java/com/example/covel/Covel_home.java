package com.example.covel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ArrayAdapter;

public class Covel_home extends AppCompatActivity {

    ImageButton imageBackButton, imageMenuButton;
    private RecyclerView main_list;
    protected RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covel_home);
        imageBackButton=(ImageButton)findViewById(R.id.imageBackButton);
        imageMenuButton=(ImageButton)findViewById(R.id.imageMenuButton);
        main_list = (RecyclerView) findViewById(R.id.main_list);
        main_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        main_list.setLayoutManager(layoutManager);

        adapter = new Covelhome_Recyclerviewadapter();
        main_list.setAdapter(adapter);

        imageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_login.class);
                startActivity(intent);
            }
        });//imageBackButton 클릭 시 로그인 화면으로 이동

        imageMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imageMenuButton


    }
}