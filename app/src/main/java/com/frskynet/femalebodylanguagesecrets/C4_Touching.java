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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class C4_Touching extends Activity {

    private TextView desc;
    private ProgressDialog loading;
    private JSONParser jsonParser;
    private AdView adView;
    private BannerAdEvents bannerAdEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c4__touching);

        desc = findViewById(R.id.touching_activity_desc);
        jsonParser = new JSONParser();

        loadFromSheet();

        adView = findViewById(R.id.distance_activity_title_banner_ad);
        MobileAds.initialize(this, this.getString(R.string.banner_app_id)); //App Id from string values
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        bannerAdEvents = new BannerAdEvents();
        bannerAdEvents.loadAd(this.getApplicationContext(), adView);
    }

    public void goToHomeBtnClick(View view) {
        Intent intent = new Intent(C4_Touching.this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void loadFromSheet() {
        loading = ProgressDialog.show(this, "Loading", "please wait ...", false, true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.getString(R.string.app_script_url) + "?action=getTouching",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        desc.setText(jsonParser.parseItems(response, "Touching"));
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        desc.setText("No data found...\nLoading...");
                        loading.dismiss();
                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
