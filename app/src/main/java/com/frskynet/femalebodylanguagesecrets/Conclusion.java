package com.frskynet.femalebodylanguagesecrets;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Conclusion extends Activity {

    private TextView desc;
    private ProgressDialog loading;

    private DataLoader dataLoader;
    private JSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);

        desc = findViewById(R.id.conclution_activity_desc);
        dataLoader = new DataLoader();
        jsonParser = new JSONParser();

        loadFromSheet();
    }

    public void goToHomeBtnClick(View view) {
        Intent intent = new Intent(Conclusion.this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void loadFromSheet() {
        loading = ProgressDialog.show(this, "Loading", "please wait ...", false, true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw667fmau8KmUufVwdQOYbVrRdURuz1vLNhPssP_P14wl0DIC4t/exec" + "?action=getConclusion",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        desc.setText(jsonParser.parseItems(response, "Conclusion"));
                        loading.dismiss();
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
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
