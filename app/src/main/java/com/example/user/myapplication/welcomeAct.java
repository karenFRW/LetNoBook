package com.example.user.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

public class welcomeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeact);

        avi = findViewById(R.id.avi);
        startAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomeAct.this, MainActivity.class));
                finish();
            }
        }, 3500);
    }

    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    AVLoadingIndicatorView avi;

}
