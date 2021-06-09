package com.example.covel.request;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NicknameVerificationRequest extends StringRequest {
    final static private String URL = "http://covel.dothome.co.kr/NicknameVerification.php";
    private Map<String, String> map;

    public NicknameVerificationRequest(String nickname, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("nickname", nickname);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
