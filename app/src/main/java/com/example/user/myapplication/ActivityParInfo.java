package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityParInfo extends AppCompatActivity {
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private LinearLayout HomeworkArea, StaffArea, OtherArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Inform);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnNextDay = findViewById(R.id.btnNextDay);
        txtDate = findViewById(R.id.txtDate);
        HomeworkArea = findViewById(R.id.HomeworkArea);
        HomeworkArea.getBackground().setAlpha(75);
        StaffArea = findViewById(R.id.StaffArea);
        StaffArea.getBackground().setAlpha(75);
        OtherArea = findViewById(R.id.OtherArea);
        OtherArea.getBackground().setAlpha(75);

    }
}
