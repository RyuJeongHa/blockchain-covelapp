package com.example.covel;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class Novel_registration extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    ImageView imageIllustration, imageView;
    EditText edtTitle, edtExplain;
    Button btnRegistration;
    ImageButton imgBackBtn6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_registration);

        imageIllustration=(ImageView)findViewById(R.id.imageIllustration);
        btnRegistration=(Button)findViewById(R.id.btnRegistration);
        edtTitle=(EditText)findViewById(R.id.edtTitle);
        edtExplain=(EditText)findViewById(R.id.edtExplain);
        imgBackBtn6=(ImageButton)findViewById(R.id.imgBackBtn6);

        imgBackBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imgBackBtn6

        imageIllustration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);//갤러리 액티비티로부터 가져온 결과 데이터를
                //처리하기 위해 StartActivityForResult() 함수를 통해 액티비티 실행
            }
        });//imageIllustration

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Novel_registration.this);
                builder.setMessage("소설을 등록하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),Novel_registration.class);
                        startActivity(intent);
                    }
                });//확인 버튼
                // 확인을 누르면 작성한 일러스트, 제목, 소설 설명이 DB에 등록되어야 함

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),Novel_registration.class);
                        startActivity(intent);
                    }
                });//취소 버튼
                builder.show();
            }
        });//btnRegistration



    }//onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if(requestCode == REQUEST_CODE){
                if(resultCode==RESULT_OK){
                    try{
                        InputStream in = getContentResolver().openInputStream(data.getData());

                        Bitmap img = BitmapFactory.decodeStream(in);
                        in.close();

                        imageIllustration.setImageBitmap(img);
                    }catch(Exception e)
                    {

                    }
                }
                else if (resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
                }
            }
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }//onActivityResult
    

}//Novel_registration
