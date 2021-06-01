package com.example.covel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.covel.request.JoinRequest;
import com.example.covel.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Covel_login extends AppCompatActivity {
    private EditText edtId, edtPw;
    private Button btnLogin, btnTerms;
    private long backBtnTime = 0;

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
                String userId = edtId.getText().toString();
                String password = edtPw.getText().toString();

                if(userId.trim().length()==0 || password.trim().length()==0) {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) { // 로그인 성공
                                String userId = jsonObject.getString("userId");
                                String password = jsonObject.getString("password");
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),Covel_home.class);
                                intent.putExtra("userId", userId);
                                intent.putExtra("password", password);
                                startActivity(intent);
                            } else { // 로그인 실패
                                Toast.makeText(getApplicationContext(), "등록되지 않은 회원이거나, 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // 서버로 Volley 이용해서 요청
                LoginRequest loginRequest = new LoginRequest(userId, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Covel_login.this);
                queue.add(loginRequest);

            }//btn-onClick
        });//btnLogin

        btnTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Join_agreement.class);
                startActivity(intent);
            }
        });//btnTerms(terms=약관)

    }//onCreate

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

}//Login