package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTea_ViewDiary extends AppCompatActivity {
    TextView txtTitle, txtDate, txtDiary, txtReply;
    Button btnPreDay, btnNextDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_view_diary);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnNextDay = findViewById(R.id.btnNextDay);
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
    }
}
