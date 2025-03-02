package com.yanciar.tictoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Timer;
import java.util.TimerTask;

public class NameActivity extends AppCompatActivity {

    public CharSequence player1 = "1";
    public CharSequence player2 = "2";
    private int length;
    public boolean selectedsingleplayer = true;
    private AdView mAdView;
    private EditText nameAi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_name);

        nameAi = findViewById(R.id.player_name_ai);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner_ad));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        nameAi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                length = nameAi.getText().length();

            }
        }, 0, 2);//put here time 1000 milliseconds = 1 second


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (length > 1) {

                    Button ds = findViewById(R.id.button2);
                    ds.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(NameActivity.this, PickPieceActivity.class);
                            CharSequence[] players = {player1, player2};
                            i.putExtra("playersnames", players);
                            i.putExtra("selectedsingleplayer", selectedsingleplayer);
                            startActivity(i);
                        }
                    });

                }


            }
        }, 0, 20);//put here time 1000 milliseconds = 1 second


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}