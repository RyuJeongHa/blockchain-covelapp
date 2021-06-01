package com.example.covel.request;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class JoinRequest extends StringRequest {

    final static private String URL = "http://covel.dothome.co.kr/Join.php";
    private Map<String, String> map;

    public JoinRequest(String userId, String password, String nickname, String name, String rgnumber1, String rgnumber2,
                       Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("nickname", nickname);
        map.put("name", name);
        map.put("rgnumber1", rgnumber1);
        map.put("rgnumber2", rgnumber2);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
