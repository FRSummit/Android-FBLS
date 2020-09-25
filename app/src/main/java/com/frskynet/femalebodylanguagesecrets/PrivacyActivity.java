package com.frskynet.femalebodylanguagesecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrivacyActivity extends Activity {

    TextView privacyDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        privacyDesc = findViewById(R.id.privacy_activity_desc_2);

        privacyDesc.setText("Do not get rid of real life to apply this. There is no Privacy in this app. Thank you");
    }

    public void goToHomeBtnClick(View view) {
        Intent intent = new Intent(PrivacyActivity.this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
