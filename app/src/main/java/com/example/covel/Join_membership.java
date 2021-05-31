package com.example.covel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Join_membership extends AppCompatActivity {
    ImageButton imgBackBtn2;
    EditText memId, memPw, memRePw, memNick, memName, memRe1, memRe2;
    Button btnVerification, btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_membership);
        imgBackBtn2=(ImageButton)findViewById(R.id.imgBackBtn2);
        memId=(EditText)findViewById(R.id.memId);
        memPw=(EditText)findViewById(R.id.memPw);
        memRePw=(EditText)findViewById(R.id.memRePw);
        memNick=(EditText)findViewById(R.id.memNick);
        memName=(EditText)findViewById(R.id.memName);
        memRe1=(EditText)findViewById(R.id.memRe1);
        memRe2=(EditText)findViewById(R.id.memRe2);
        btnVerification=(Button)findViewById(R.id.btnVerification);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);

        imgBackBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Join_agreement.class);
                startActivity(intent);
            }
        });//imgBackBtn2

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Join_membership.this);
                builder.setMessage(R.string.sign_up);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),Covel_login.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });//btnSignUp


    }//onCreate
}//Join_membership
