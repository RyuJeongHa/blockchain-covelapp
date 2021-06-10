package com.example.covel;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.example.covel.preferences.AppPreferences;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Novel_registration extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private static final int REQUEST_IMAGE_CAPTURE=0;
    ImageView imageIllustration;
    EditText edtTitle, edtExplain;
    Button btnRegistration;
    ImageButton imgBackBtn6;
    private String imagePath = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_registration);

        imageIllustration=(ImageView)findViewById(R.id.imageIllustration);
        btnRegistration=(Button)findViewById(R.id.btnRegistration);
        edtTitle=(EditText)findViewById(R.id.edtTitle);
        edtExplain=(EditText)findViewById(R.id.edtExplain);
        imgBackBtn6=(ImageButton)findViewById(R.id.imgBackBtn6);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionResult == PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,REQUEST_IMAGE_CAPTURE);
            }
        }

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
                    Toast.makeText(Novel_registration.this, "제목을 작성하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = "http://covel.dothome.co.kr/WriteBoardMultiPart.php";
                AlertDialog.Builder builder= new AlertDialog.Builder(Novel_registration.this);
                builder.setMessage("소설을 등록하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SimpleMultiPartRequest simpleMultiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
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
                                    e.printStackTrace();
                                }
                            }
                        }, null);

                        simpleMultiPartRequest.addStringParam("title", title);
                        simpleMultiPartRequest.addStringParam("description", description);
                        simpleMultiPartRequest.addStringParam("userId", String.valueOf(userId));
                        simpleMultiPartRequest.addFile("imagePath", imagePath);

                        RequestQueue requestQueue= Volley.newRequestQueue(Novel_registration.this);
                        requestQueue.add(simpleMultiPartRequest);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) // 사용자가 허가 했다면
            {
                Toast.makeText(this, "외부 메모리 읽기/쓰기 사용 가능", Toast.LENGTH_SHORT).show();

            }else{// 거부했다면
                Toast.makeText(this, "외부 메모리 읽기/쓰기 제한", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageIllustration.setImageBitmap(img);

                    /* 이미지 - db 연동 */
                    Uri uri = data.getData();
                    imagePath = getFullPathFromUri(this, uri);
                } catch(Exception e) {
                    new AlertDialog.Builder(this).setMessage("해당 이미지는 업로드할 수 없습니다.").create().show();
                    e.printStackTrace();
                }
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }//onActivityResult

    public static String getFullPathFromUri(Context ctx, Uri fileUri) {
        String fullPath = null;
        final String column = "_data";
        Cursor cursor = ctx.getContentResolver().query(fileUri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            if (document_id == null) {
                for (int i=0; i < cursor.getColumnCount(); i++) {
                    if (column.equalsIgnoreCase(cursor.getColumnName(i))) {
                        fullPath = cursor.getString(i);
                        break;
                    }
                }
            } else {
                document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
                cursor.close();

                final String[] projection = {column};
                try {
                    cursor = ctx.getContentResolver().query(
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            projection, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        fullPath = cursor.getString(cursor.getColumnIndexOrThrow(column));
                    }
                } finally {
                    if (cursor != null) cursor.close();
                }
            }
        }
        return fullPath;
    }//getFullPathFromUri
}//Novel_registration
