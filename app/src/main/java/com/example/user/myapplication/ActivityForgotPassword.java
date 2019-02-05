package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityForgotPassword extends AppCompatActivity {
    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(ActivityForgotPassword.this, MainActivity.class);
        startActivity(intent);
        ActivityForgotPassword.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
}
