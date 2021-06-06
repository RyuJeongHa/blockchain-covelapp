package com.example.covel;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covel.preferences.AppPreferences;

public class Covel_menu extends AppCompatActivity {
    ImageButton imgBackBtn3, plus1, plus2, plus3;
    Button btnLogout;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covel_menu);
        imgBackBtn3=(ImageButton)findViewById(R.id.imgBackBtn3);
        plus1=(ImageButton)findViewById(R.id.plus1);
        plus2=(ImageButton)findViewById(R.id.plus2);
        plus3=(ImageButton)findViewById(R.id.plus3);
        btnLogout=(Button)findViewById(R.id.btnLogout);

        imgBackBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_home.class);
                startActivity(intent);
            }
        });//imgBackBtn3

        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),My_information.class);
                startActivity(intent);
            }
        });//plus1

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Novel_upload.class);
                startActivity(intent);
            }
        });//plus2

        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Novel_registration.class);
                startActivity(intent);
            }
        });//plus3

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppPreferences.logout();

                Intent intent=new Intent(getApplicationContext(),Covel_login.class);
                startActivity(intent);
                finish();
            }
        });//btnLogout

    }//onCreate
}//Covel_menu
