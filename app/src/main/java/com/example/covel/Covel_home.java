package com.example.covel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Covel_home extends AppCompatActivity {

    ImageButton imageBackButton, imageMenuButton;
    private ArrayList<Covel_home_item> arrayList;
    private Covelhome_Recyclerviewadapter mainadapter;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    private long backBtnTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covel_home);

        imageBackButton=(ImageButton)findViewById(R.id.imageBackButton);
        imageMenuButton=(ImageButton)findViewById(R.id.imageMenuButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();

        mainadapter = new Covelhome_Recyclerviewadapter(arrayList);
        recyclerView.setAdapter(mainadapter);


        //imageBackButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Intent intent=new Intent(getApplicationContext(),Covel_login.class);
                //startActivity(intent);
            //}
        //});//imageBackButton 클릭 시 로그인 화면으로 이동

        imageMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imageMenuButton
    }

    @Override
    public void onBackPressed() { // 초기화면에서 1초 안에 뒤로가기 버튼 두번 클릭 시 앱 종료
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && gapTime <= 1000) {
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }//onBackPressed

}