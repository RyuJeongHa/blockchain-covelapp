package com.example.covel;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.covel.preferences.AppPreferences;
import com.example.covel.request.FindMyNovelListRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Novel_upload extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private static final int SELECT_FILE = 0;
    private static final int FILE_SELECT_OK = 0;
    ImageButton imgBackBtn5;
    Button btnUpload;
    EditText write_novel;
    TextView file_name,upload_title, findNovelTextView, attached_file;
    ArrayList<String> titleList;
    private static final int READ_REQUEST_CODE = 123;

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
        write_novel=(EditText) findViewById(R.id.write_novel);
        file_name=(TextView) findViewById(R.id.file_name);
        findNovelTextView=(TextView)findViewById(R.id.findNovelTextView);
        attached_file=(TextView)findViewById(R.id.attached_file);

        titleList = new ArrayList<String>();

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

                 Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                 it.setType("text/plain");

                 it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                 startActivityForResult(it.createChooser(it,"Select a file"),
                         READ_REQUEST_CODE);
            }

        });//attached_file
        // 클릭하면 파일탐색기가 나와서 파일 첨부가 가능
        // 선택한 파일이 file_name칸에 보여져야 함

    } //onCreate

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK){
            Uri selectedfile = data.getData();
            //경로 정보: selectedfile.getPath();
            //전체 URI 정보: selectedfile.to(String);

            System.out.println("경로 :"+ selectedfile.getPath());
            System.out.println("전체 URI 정보 :"+ selectedfile.toString());

            Toast.makeText(getApplicationContext(),getFileNameFromUri(selectedfile),
                    Toast.LENGTH_LONG).show();

            file_name.setText(getFileNameFromUri(selectedfile));

            write_novel.setText(readTextFromUri(selectedfile));

        }
    }//onActivityResult

    //URI에서 파일명 얻기
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getFileNameFromUri(Uri uri){
        String fileName = "";
        Cursor cursor = getContentResolver().query(uri,null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            fileName=cursor.getString(
                cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            Log.i(TAG,"Display Name: "+fileName);
            }
        cursor.close();
        return fileName;
    }

    private String readTextFromUri(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}//Novel_upload

