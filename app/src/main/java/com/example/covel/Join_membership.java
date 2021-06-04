package com.example.covel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.covel.request.JoinRequest;
import com.example.covel.request.NicknameVerificationRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Join_membership extends AppCompatActivity {
    ImageButton imgBackBtn2;
    EditText memId, memPw, memRePw, memNick, memName, memRe1, memRe2;
    Button btnVerification, btnSignUp;
    private boolean isVerify = false;

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

        btnVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = memNick.getText().toString();

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
                // 서버로 Volley 이용해서 요청
                NicknameVerificationRequest nicknameVerificationRequest = new NicknameVerificationRequest(nickname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Join_membership.this);
                queue.add(nicknameVerificationRequest);
            }//btnVerification-onClick
        });//btnVerification

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = memId.getText().toString();
                String password = memPw.getText().toString();
                String passwordRe = memRePw.getText().toString();
                String nickname = memNick.getText().toString();
                String name = memName.getText().toString();
                String rgnumber1 = memRe1.getText().toString();
                String rgnumber2 = memRe2.getText().toString();

                if (userId.trim().length() == 0 || password.trim().length() == 0 || nickname.trim().length() == 0 ||
                        name.trim().length() == 0 || rgnumber1.trim().length() == 0 || rgnumber2.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isVerify) { // 닉네임 중복 확인 or 실패
                    Toast.makeText(getApplicationContext(), "닉네임 중복 확인을 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(passwordRe)) { // 비밀번호 불일치
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) { // 회원가입 성공
                                AlertDialog.Builder builder = new AlertDialog.Builder(Join_membership.this);
                                builder.setMessage(R.string.sign_up);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(getApplicationContext(), Covel_login.class);
                                        startActivity(intent);
                                    }
                                });
                                builder.show();
                            } else { // 회원가입 실패
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // 서버로 Volley 이용해서 요청
                JoinRequest joinRequest = new JoinRequest(userId, password, nickname, name, rgnumber1, rgnumber2, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Join_membership.this);
                queue.add(joinRequest);

            }//btnSignUp-onClick
        });//btnSignUp


    }//onCreate
}//Join_membership
