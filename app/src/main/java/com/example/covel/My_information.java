package com.example.covel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.covel.preferences.AppPreferences;
import com.example.covel.request.MyInfoRequest;
import com.example.covel.request.NicknameVerificationRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class My_information extends AppCompatActivity {

    ImageButton imgBackBtn4;
    TextView textNickname, textId; //회원가입 한 닉네임, ID 연동
    EditText changeNic;
    Button btnVerification2, ChangeBtn;
    private boolean isVerify = false;

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

        textNickname.setText(AppPreferences.getNickname(getApplicationContext()));
        textId.setText(AppPreferences.getUserId(getApplicationContext()));

        imgBackBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imgBackButton 클릭 시 메뉴로 이동

        btnVerification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = changeNic.getText().toString();

                if(nickname.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(), "사용하실 수 있는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                                isVerify = true;
                            } else {
                                Toast.makeText(getApplicationContext(), "사용하실 수 없는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                                isVerify = false;
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                NicknameVerificationRequest nicknameVerificationRequest = new NicknameVerificationRequest(nickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(My_information.this);
                queue.add(nicknameVerificationRequest);
            }
        });//btnVerfication2 => 중복 확인 버튼

        ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = AppPreferences.getId(getApplicationContext());
                String nickname = changeNic.getText().toString();

                if(!isVerify) {
                    Toast.makeText(getApplicationContext(), "닉네임 중복 확인을 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(), "닉네임 변경에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                AppPreferences.setNickname(getApplicationContext(), nickname);

                                Intent intent = getIntent();
                                finish(); //현재 액티비티 종료
                                overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                                startActivity(intent); //현재 액티비티 재실행 실시
                                overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                            } else {
                                Toast.makeText(getApplicationContext(), "닉네임 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                MyInfoRequest myInfoRequest = new MyInfoRequest(id, nickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(My_information.this);
                queue.add(myInfoRequest);
            }
        });//ChangeBtn 이 버튼을 누르면 닉네임이 변경 되어야 함


    }//onCreate
}//M_information
