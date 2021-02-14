package com.app.pengingatutang;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.wang.avi.AVLoadingIndicatorView;

public class SplashScreen extends AppCompatActivity {

    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;
    private static final String[] INDICATORS=new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("LineSpinFadeLoaderIndicator");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

                finish();
            }

        }, 3000);
    }

}