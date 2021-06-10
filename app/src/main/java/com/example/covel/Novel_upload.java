package com.example.covel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.covel.DTO.BoardDTO;
import com.example.covel.preferences.AppPreferences;
import com.example.covel.request.FindMyNovelListRequest;
import com.example.covel.request.NicknameVerificationRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class Novel_upload extends AppCompatActivity {
    ImageButton imgBackBtn5;
    Button btnUpload;
    TextView write_novel,file_name,upload_title, findNovelTextView, attached_file; //내 소설 찾기, 첨부파일
    ArrayList<String> titleList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_upload);


        //이미지 버튼
        imgBackBtn5=(ImageButton)findViewById(R.id.imgBackBtn5);

        //버튼
        btnUpload=(Button)findViewById(R.id.btnUpload);

        //TextView
        upload_title=(TextView) findViewById(R.id.upload_title);
        write_novel=(TextView) findViewById(R.id.write_novel);
        file_name=(TextView) findViewById(R.id.file_name);
        findNovelTextView=(TextView)findViewById(R.id.findNovelTextView);
        attached_file=(TextView)findViewById(R.id.attached_file);

        titleList = new ArrayList<String>();

        /*
        // 전송할 파일의 경로
        String szSendFilePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/test.jpg";
        File f = new File(szSendFilePath);
        if (!f.exists()) {
            Toast.makeText(this, "파일이 없습니다.", Toast.LENGTH_SHORT).show();
        }

         //File객체로부터 Uri값 생성
        final Uri fileUri = Uri.fromFile(f); */


        imgBackBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imgBackBtn 클릭 시 메뉴로 이동

        // 업로드 버튼 클릭 시 소설이 업로드 되며 home에 나타남, 소설 DB연동 필요
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Novel_upload.this);
                builder.setMessage("소설이 업로드 되었습니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),Covel_home.class);
                        startActivity(intent);// 홈화면으로 이동(바꾸셔도 됩니다)

                    }
                });//확인 버튼
                builder.show();
            }
        });//btnUpload // write_novel 에 쓴 내용이 있으면 그것도 등록이 되어야 함

        findNovelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload_title.setText(findNovelTextView.getText());

                int userId = AppPreferences.getId(getApplicationContext());
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                JSONArray titles = jsonObject.getJSONArray("result");

                                for(int i=0; i<titles.length(); i++) {
                                    JSONObject titleObj = titles.getJSONObject(i);
                                    String title = titleObj.getString("title");
                                    titleList.add(title);
                                }

                                System.out.println("titleList : "+ titleList);

                                /* 뷰에 목록(titleList) 세팅 */

                            } else {
                                Toast.makeText(Novel_upload.this, "등록된 소설이 없습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindMyNovelListRequest findMyNovelListRequest = new FindMyNovelListRequest(userId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Novel_upload.this);
                queue.add(findMyNovelListRequest);
            }
        });
        //findNovelTextView - upload_title(EditText)에서 친 내용을 내 소설 찾기 텍스트를 누르면
        // DB에 등록된 내 소설(제목과 설명과 표지가 들어간)을 찾아주기

        attached_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("application/*");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i.createChooser(i,"OPEN"));

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getDownloadCacheDirectory().getPath()+"/Download/");
                intent.setDataAndType(uri,"application/*");
                startActivity(intent.createChooser(intent,"OPEN"));

                Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("text/plain");
                Uri uri2=Uri.parse(Environment.getDownloadCacheDirectory().getPath()+"text/");
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it.createChooser(it,"OPEN"));
                file_name.setText(uri2.getLastPathSegment());






            }
        });//attached_file
        // 클릭하면 파일탐색기가 나와서 파일 첨부가 가능
        // 선택한 파일이 file_name칸에 보여져야 함

    }//onCreate




}//Novel_upload
