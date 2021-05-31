package com.example.covel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Covel_login extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLogin, btnTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covel_login);
        edtId=(EditText)findViewById(R.id.edtId);
        edtPw=(EditText)findViewById(R.id.edtPw);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnTerms=(Button)findViewById(R.id.btnTerms);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Covel_home.class);
                startActivity(intent);
            }
        });//btnLogin 에러 뜨는 이유는 아직 홈 화면이 만들어지지 않음

        btnTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Join_agreement.class);
                startActivity(intent);
            }
        });//btnTerms(terms=약관)


    }//onCreate
}//Login