package com.example.covel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Novel_main  extends AppCompatActivity {

    ImageButton main_Back;
    TextView novel;//소설 내용 보여주는 곳


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_main);
        main_Back=(ImageButton)findViewById(R.id.main_Back);


        main_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Novel_list.class);
                startActivity(intent);
            }
        });//뒤로가기


    }
}
