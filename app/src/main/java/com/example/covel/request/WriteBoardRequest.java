package com.example.covel.request;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WriteBoardRequest extends StringRequest {

    final static private String URL = "http://covel.dothome.co.kr/WriteBoard.php";
    private Map<String, String> map;

    public WriteBoardRequest(String title, String description, int userId, String imagePath, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("title", title);
        map.put("description", description);
        map.put("userId", String.valueOf(userId));
        map.put("imagePath", imagePath);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }


}
