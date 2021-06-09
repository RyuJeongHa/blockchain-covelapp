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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covel.preferences.AppPreferences;
import com.example.covel.request.WriteBoardRequest1;
import com.example.covel.request.WriteBoardRequest2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Novel_registration extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    ImageView imageIllustration, imageView;
    EditText edtTitle, edtExplain;
    Button btnRegistration;
    ImageButton imgBackBtn6;
    boolean isResponseError = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_registration);

        imageIllustration=(ImageView)findViewById(R.id.imageIllustration);
        btnRegistration=(Button)findViewById(R.id.btnRegistration);
        edtTitle=(EditText)findViewById(R.id.edtTitle);
        edtExplain=(EditText)findViewById(R.id.edtExplain);
        imgBackBtn6=(ImageButton)findViewById(R.id.imgBackBtn6);
//        imageView=(ImageView)findViewById(R.id.imageView);

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
                String title = edtTitle.getText().toString();
                String description = edtExplain.getText().toString();
                int userId = AppPreferences.getId(getApplicationContext());

                if(title.trim().length() < 1) {
                    Toast.makeText(Novel_registration.this, "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder= new AlertDialog.Builder(Novel_registration.this);
                builder.setMessage("소설을 등록하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    //System.out.println("res :" + response);
                                    //System.out.println("jo :" + jsonObject);
                                    boolean success = jsonObject.getBoolean("success");

                                    if (success) {
                                        Toast.makeText(getApplicationContext(), "소설 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Covel_home.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "소설 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    System.out.println("소설 등록 : 통신 실패");
                                    Toast.makeText(getApplicationContext(), "소설 등록 : 통신 실패", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        };
                        if(description.trim().length() == 0) {
                            WriteBoardRequest2 writeBoardRequest2 = new WriteBoardRequest2(title, userId, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(Novel_registration.this);
                            queue.add(writeBoardRequest2);
                        } else {
                            WriteBoardRequest1 writeBoardRequest1 = new WriteBoardRequest1(title, description, userId, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(Novel_registration.this);
                            queue.add(writeBoardRequest1);
                        }
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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
//            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                imageView.setImageBitmap(imageBitmap);
//            }
        }
    }//onActivityResult
    

}//Novel_registration
