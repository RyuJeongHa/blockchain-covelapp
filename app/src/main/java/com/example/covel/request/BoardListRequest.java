package com.example.covel.request;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class BoardListRequest extends StringRequest {
    final static private String URL = "http://covel.dothome.co.kr/BoardList.php";
    private Map<String, String> map;

    public BoardListRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
