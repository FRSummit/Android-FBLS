package com.frskynet.femalebodylanguagesecrets;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;

public class C1_DistanceActivity extends Activity {

    private TextView distanceDesc;
    private ProgressDialog loading;
    private JSONParser jsonParser;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1_distance);

        distanceDesc = findViewById(R.id.distance_activity_desc);
        jsonParser = new JSONParser();

        loadFromSheet();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/8691691433"); // Interstitial
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(request);
        adEvent();
    }

    public void goToHomeBtnClick(View view) {
        Intent intent = new Intent(C1_DistanceActivity.this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void loadFromSheet() {
        loading = ProgressDialog.show(this, "Loading", "please wait ...", false, true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw667fmau8KmUufVwdQOYbVrRdURuz1vLNhPssP_P14wl0DIC4t/exec" + "?action=getDistance",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        distanceDesc.setText(jsonParser.parseItems(response, "Distance"));
                        loading.dismiss();
                        loadAc();
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

    private void loadAc() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                interBtnClick();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 30000);
    }

    public void interBtnClick() {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            Toast.makeText(this, "The interstitial wasn't loaded yet.", Toast.LENGTH_LONG).show();
        }
    }

    public void adEvent() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "Ad is loaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(getApplicationContext(), "Ad is clicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed", Toast.LENGTH_LONG).show();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}
