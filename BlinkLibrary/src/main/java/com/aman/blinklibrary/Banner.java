package com.aman.blinklibrary;

import android.content.Context;
import android.util.Log;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Banner {

    Response response;
    Context context;
    RequestQueue queue;

    public Banner(Response response, Context context) {
        this.response = response;
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void getBanner() {

        String url = "";
        try {
            url = "https://api.prod.aman.in/aman/banner/get/v3" +
                    "?appId=" + "1" +
                    "&userId=" + "-1" +
                    "&pageNo=" + 0 +
                    "&pageSize=" + 10 +
                    "&isNew=" + true +
                    "&bussinessId=" + "1";
        } catch (Exception e) {
            Log.e("Response", e.getMessage());
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.trim(), r -> {

            try {
                JSONObject jsonObject = new JSONObject(r);
                response.onResponse(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, error -> Log.e("Response", error.getMessage()));
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);

    }

    public interface Response {
        void onResponse(JSONObject jsonObject);
    }
}

