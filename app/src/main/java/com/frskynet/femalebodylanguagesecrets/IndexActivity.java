package com.frskynet.femalebodylanguagesecrets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class IndexActivity extends Activity {

    private int[] images = {
            R.drawable.distance,
            R.drawable.eyes_and_female_body_language,
            R.drawable.hand_talk,
            R.drawable.touching,
            R.drawable.smile,
            R.drawable.legs_and_feet,
            R.drawable.mirroring_body_language,
            R.drawable.on_dance_floor,
            R.drawable.signals_are_you_sending,
            R.drawable.welcome_women_logo
    };

    private String[] title = {
            "Distance",
            "The Eyes And Female Body Language",
            "Hand Talk",
            "Touching",
            "What Kind of Smile?",
            "Legs And Feet",
            "Mirroring Body Language",
            "Body Language On The Dance Floor",
            "What Signals Are You Sending?",
            "Conclusion"
    };

    private String[] subtitle = {
            "Let’s start with the most basic thing of all...",
            "All right, so you’ve just met this woman and...",
            "What she is doing with her hands can convey...",
            "Let’s go into a little more depth about touching...",
            "If you’re in a polite conversation, chances are...",
            "Although not as expressive as the eyes...",
            "One final and rather a subtle cue that a woman is interested...",
            "All of the above involves social situations where standing...",
            "While paying all this attention to what signals she’s sending...",
            "Put together, the signals we’ve been looking ..."
    };

    private ListView lView;
    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        lView = (ListView) findViewById(R.id.index_activity_list_view);
        lAdapter = new ListAdapter(IndexActivity.this, title, subtitle, images);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(IndexActivity.this, i + " " + title[i] + "\n" + subtitle[i], Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 0: {
                        Toast.makeText(IndexActivity.this, "Distance", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C1_DistanceActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 1: {
                        Toast.makeText(IndexActivity.this, "The Eyes And Female Body Language", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C2_EyesAndFemaleBodyLanguage.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 2: {
                        Toast.makeText(IndexActivity.this, "Hand Talk", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C3_HandTalk.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 3: {
                        Toast.makeText(IndexActivity.this, "Touching", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C4_Touching.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 4: {
                        Toast.makeText(IndexActivity.this, "What Kind of Smile?", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C5_Smile.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 5: {
                        Toast.makeText(IndexActivity.this, "Legs And Feet", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C6_LegsAndFeet.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 6: {
                        Toast.makeText(IndexActivity.this, "Mirroring Body Language", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C7_MirroringBodyLanguage.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 7: {
                        Toast.makeText(IndexActivity.this, "Body Language On The Dance Floor", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C8_DanceFloor.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 8: {
                        Toast.makeText(IndexActivity.this, "What Signals Are You Sending?", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, C9_Signals.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    case 9: {
                        Toast.makeText(IndexActivity.this, "Conclusion", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(IndexActivity.this, Conclusion.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }

    public void goToHomeBtnClick(View v) {
        Intent intent = new Intent(IndexActivity.this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
