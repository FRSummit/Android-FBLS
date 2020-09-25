package com.frskynet.femalebodylanguagesecrets;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by F R Summit on 24th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class DataLoader {
//    ProgressDialog loading;
    String desc = "";

     public String getContent(Context context) {
        loadFromSheet(context);
        System.out.println("desc : " + desc);
        return desc;
    }

    public void loadFromSheet(Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw667fmau8KmUufVwdQOYbVrRdURuz1vLNhPssP_P14wl0DIC4t/exec" + "?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    public void parseItems(String jsonResponse) {
        String description = "";
        try {
            JSONObject jobj = new JSONObject(jsonResponse);
            JSONArray jarray = jobj.getJSONArray("items");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String date = jo.getString("Conclusion");

                description += "    " + date + "\n";
            }
            System.out.println("description : " + description);
            desc = description;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
