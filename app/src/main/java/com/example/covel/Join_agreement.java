package com.example.covel;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Join_agreement extends AppCompatActivity {
    ImageButton imgBtnBackButton, imgNextButton1, imgNextButton2, imgNextButton3;
    Button btnAgree;
    CheckBox chBAll, chBAge, chBService, chBCollection, chBProvision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_agreement);
        imgBtnBackButton=(ImageButton)findViewById(R.id.imgBtnBackButton);
        imgNextButton1=(ImageButton)findViewById(R.id.imgBtnNextButton1);
        imgNextButton2=(ImageButton)findViewById(R.id.imgBtnNextButton2);
        imgNextButton3=(ImageButton)findViewById(R.id.imgBtnNextButton3);
        btnAgree=(Button)findViewById(R.id.btnAgree);
        chBAll=(CheckBox)findViewById(R.id.chBAll);
        chBAge=(CheckBox)findViewById(R.id.chBAge);
        chBService=(CheckBox)findViewById(R.id.chBService);
        chBCollection=(CheckBox)findViewById(R.id.chBCollection);
        chBProvision=(CheckBox)findViewById(R.id.chBProvision);

        imgBtnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_login.class);
                startActivity(intent);
            }
        });//imgBtnBackButton

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Join_membership.class);
                startActivity(intent);
            }
        });//btnAgree, 에러 뜨는 이유는 아직 회원가입 페이지 만들어지지 않음.

        imgNextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Join_agreement.this);
                builder.setTitle("서비스 약관");//다이얼로그 창의 제목
                builder.setMessage("서비스 약관에 대한 설명입니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Join_agreement.this,"확인 버튼을 눌렀습니다.",
                                Toast.LENGTH_SHORT).show();
                    }// 토스트 메시지
                });//확인 버튼
                builder.show();
            }
        });//imgNextButton1

        imgNextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Join_agreement.this);
                builder.setTitle("개인정보 수집 이용 및 동의");//다이얼로그 창의 제목
                builder.setMessage("개인정보 수집에 동의하시면 확인 눌러주세요.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Join_agreement.this,"확인 버튼을 눌렀습니다.",
                                Toast.LENGTH_SHORT).show();
                    }// 토스트 메시지
                });//확인 버튼
                builder.show();
            }
        });//imgNextButton2

        imgNextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Join_agreement.this);
                builder.setTitle("개인정보 제 3자 제공 및 이용 동의");//다이얼로그 창의 제목
                builder.setMessage("개인정보를 제 3자에게 제공할 수 있습니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Join_agreement.this,"확인 버튼을 눌렀습니다.",
                                Toast.LENGTH_SHORT).show();
                    }// 토스트 메시지
                });//확인 버튼
                builder.show();
            }
        });//imgNextButton3

        //모두 동의 클릭시 전체 true, 전체 false로 변경
        chBAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chBAll.isChecked()){
                    chBAge.setChecked(true);
                    chBCollection.setChecked(true);
                    chBService.setChecked(true);
                    chBProvision.setChecked(true);
                }
                else {
                    chBAge.setChecked(false);
                    chBCollection.setChecked(false);
                    chBService.setChecked(false);
                    chBProvision.setChecked(false);
                }
            }
        });//chBAll
        chBAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chBAll.isChecked()){
                    chBAll.setChecked(false);
                }//전체 동의가 true면 false로 변경
                else if(chBAge.isChecked() && chBService.isChecked() && chBProvision.isChecked()
                && chBCollection.isChecked()){
                    chBAll.setChecked(true);
                }// 각 체크박스 체크 여부를 확인하여 전테동의 체크박스 변경
            }
        });//chBAge
        chBService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chBAll.isChecked()){
                    chBAll.setChecked(false);
                }//전체 동의가 true면 false로 변경
                else if(chBAge.isChecked() && chBService.isChecked() && chBProvision.isChecked()
                        && chBCollection.isChecked()){
                    chBAll.setChecked(true);
                }// 각 체크박스 체크 여부를 확인하여 전테동의 체크박스 변경
            }
        });//chBService
        chBCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chBAll.isChecked()){
                    chBAll.setChecked(false);
                }//전체 동의가 true면 false로 변경
                else if(chBAge.isChecked() && chBService.isChecked() && chBProvision.isChecked()
                        && chBCollection.isChecked()){
                    chBAll.setChecked(true);
                }// 각 체크박스 체크 여부를 확인하여 전테동의 체크박스 변경
            }
        });//chBCollection
        chBProvision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chBAll.isChecked()){
                    chBAll.setChecked(false);
                }//전체 동의가 true면 false로 변경
                else if(chBAge.isChecked() && chBService.isChecked() && chBProvision.isChecked()
                        && chBCollection.isChecked()){
                    chBAll.setChecked(true);
                }// 각 체크박스 체크 여부를 확인하여 전테동의 체크박스 변경
            }
        });//chBProvision
    }//onCreate
}//Join_agreement
