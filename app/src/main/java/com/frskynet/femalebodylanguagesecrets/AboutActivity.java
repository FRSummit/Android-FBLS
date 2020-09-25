package com.frskynet.femalebodylanguagesecrets;

import androidx.appcompat.app.AppCompatActivity;

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

public class AboutActivity extends Activity {

    private TextView aboutDesc;
    private ProgressDialog loading;
    private JSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutDesc = findViewById(R.id.about_activity_desc_2);
        jsonParser = new JSONParser();

        loadFromSheet();
    }

    public void goToHomeBtnClick(View v) {
        Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void loadFromSheet() {
        loading = ProgressDialog.show(this, "Loading", "please wait ...", false, true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw667fmau8KmUufVwdQOYbVrRdURuz1vLNhPssP_P14wl0DIC4t/exec" + "?action=getIntroduction",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        aboutDesc.setText(jsonParser.parseItems(response, "Introduction"));
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
