package com.example.covel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.covel.DTO.BoardDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Covel_home extends AppCompatActivity {

    ImageButton imageBackButton, imageMenuButton;
    private ArrayList<Covel_home_item> arrayList;
    private Covelhome_Recyclerviewadapter mainadapter;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    private long backBtnTime = 0;
    private JSONArray boards = null;
    ArrayList<BoardDTO> boardList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covel_home);

        imageBackButton=(ImageButton)findViewById(R.id.imageBackButton);
        imageMenuButton=(ImageButton)findViewById(R.id.imageMenuButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        boardList = new ArrayList<BoardDTO>();

        mainadapter = new Covelhome_Recyclerviewadapter(arrayList);
        recyclerView.setAdapter(mainadapter);

        loadBoardDB();

        imageMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Covel_menu.class);
                startActivity(intent);
            }
        });//imageMenuButton
    }

    private void loadBoardDB() {
        new Thread() {
            @Override
            public void run() {
                String uri = "http://covel.dothome.co.kr/BoardList.php";
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setDoInput(true);
                    con.setUseCaches(false);

                    InputStream is = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();
                    while(line!=null) {
                        buffer.append(line+"\n");
                        line = reader.readLine();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(buffer.toString());
                                boolean success = jsonObject.getBoolean("success");

                                if(success) {
                                    boards = jsonObject.getJSONArray("result");

                                    for(int i=0; i<boards.length(); i++) {
                                        JSONObject boardObj = boards.getJSONObject(i);
                                        int id = boardObj.getInt("id");
                                        String title = boardObj.getString("title");
                                        String content = boardObj.getString("content");
                                        String description = boardObj.getString("description");
                                        int userId = boardObj.getInt("userId");
                                        String imagePath = "http://covel.dothome.co.kr/"+ boardObj.getString("imagePath");

                                        BoardDTO boardDTO = new BoardDTO(id, title, content, description, userId, imagePath);
                                        boardList.add(boardDTO);
                                    }

                                    System.out.println("boardList :"+ boardList);

                                    /* 뷰에 목록(boardList) 세팅 */

                                } else {
                                    Toast.makeText(Covel_home.this, "목록 값 없음", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    } //loadBoardDB()

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

}