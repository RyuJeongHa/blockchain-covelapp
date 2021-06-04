package com.example.covel.request;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyInfoRequest extends StringRequest {
    final static private String URL = "http://covel.dothome.co.kr/MyInfo.php";
    private Map<String, String> map;

    public MyInfoRequest(int id, String nickname, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("nickname", nickname);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
