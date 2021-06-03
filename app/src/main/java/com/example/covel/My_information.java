package com.example.covel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class My_information extends AppCompatActivity {

    ImageButton imgBackBtn4;
    TextView textNickname, textId; //회원가입 한 닉네임, ID 연동
    EditText changeNic;
    Button btnVerification2, ChangeBtn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_information);
        imgBackBtn4=(ImageButton)findViewById(R.id.imgBackBtn4);
        textNickname=(TextView)findViewById(R.id.textNickname);
        textId=(TextView)findViewById(R.id.textId);
        changeNic=(EditText)findViewById(R.id.changeNic);
        btnVerification2=(Button)findViewById(R.id.btnVerification2);
        ChangeBtn=(Button)findViewById(R.id.ChangeBtn);

        imgBackBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imgBackButton 클릭 시 메뉴로 이동

        //textNickname, textId DB와 연동 필요
        //changeNic => 중복 확인 필요

        btnVerification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });//btnVerfication2 => 중복 확인 버튼

        ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });//ChangeBtn 이 버튼을 누르면 닉네임이 변경 되어야 함


    }//onCreate
}//M_information
