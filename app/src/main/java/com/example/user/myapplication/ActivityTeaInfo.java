package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ActivityTeaInfo extends AppCompatActivity {
    private LinearLayout HomeworkArea, StaffArea, OtherArea;
    private Button btnLastDay, btnNextDay;
    private ListView lvHomework, lvStaff, lvOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_info);
        InitialComponent();
    }

    private void InitialComponent() {
        HomeworkArea = findViewById(R.id.HomeworkArea);
        HomeworkArea.getBackground().setAlpha(75);
        StaffArea = findViewById(R.id.StaffArea);
        StaffArea.getBackground().setAlpha(75);
        OtherArea = findViewById(R.id.OtherArea);
        OtherArea.getBackground().setAlpha(75);

        btnLastDay = findViewById(R.id.btnLastDay);
        btnNextDay = findViewById(R.id.btnNextDay);

        lvHomework = findViewById(R.id.lvHomework);
        lvStaff = findViewById(R.id.lvStaff);
        lvOther = findViewById(R.id.lvOther);

    }


}
