package com.example.covel.request;

import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class WriteBoardRequest1 extends StringRequest {

    final static private String URL = "http://covel.dothome.co.kr/WriteBoard1.php";
    final static int[] status_code = new int[1];
    private Map<String, String> map;

    public WriteBoardRequest1(String title, String description, int userId, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("title", title);
        map.put("description", description);
        map.put("userId", String.valueOf(userId));
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        status_code[0] = response.statusCode;
        return super.parseNetworkResponse(response);
    }

    public static int getStatus_code() {
        return status_code[0];
    }
}
