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


public class TwoNameActivity extends AppCompatActivity {

    public CharSequence player1 = "Player 1";
    public CharSequence player2 = "Player 2";
    public boolean selectedsingleplayer = false;
    private AdView mAdView;
    private EditText first;
    private EditText second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_two_name);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner_ad));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView5);
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


        final Button button = findViewById(R.id.button2);
        SoftKeyboardStateHelper.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {

                button.setVisibility(View.GONE);

            }

            @Override
            public void onSoftKeyboardClosed() {
                button.setVisibility(View.VISIBLE);
            }
        };

        final SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(getApplicationContext(), findViewById(R.id.parent));
        softKeyboardStateHelper.addSoftKeyboardStateListener(softKeyboardStateListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        first = findViewById(R.id.first_player);
        first.addTextChangedListener(new TextWatcher() {
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

        second = findViewById(R.id.second_player);
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        Button ds = findViewById(R.id.button2);
        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TwoNameActivity.this, PickPieceActivity.class);
                i.putExtra("selectedsingleplayer", selectedsingleplayer);
                CharSequence[] players = {player1, player2};
                i.putExtra("playersnames", players);

                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TwoNameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}