package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityStu_Info extends AppCompatActivity {
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtHw, txtHwSub, txtSfSub, txtSf, txtOtSub, txtOt;
    private Intent intent = null;
    private String stuId = null;
    private String classId = null;
    CInfoFactory infoFactory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        intent = getIntent();
        classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
        infoFactory = new CInfoFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtDate = findViewById(R.id.txtDate);
        txtHwSub = findViewById(R.id.txtHwSub);
        txtSfSub = findViewById(R.id.txtSfSub);
        txtOtSub = findViewById(R.id.txtOtSub);
        txtHw = findViewById(R.id.txtHw);
        txtHw.getBackground().setAlpha(75);
        txtSf = findViewById(R.id.txtSf);
        txtSf.getBackground().setAlpha(75);
        txtOt = findViewById(R.id.txtOt);
        txtOt.getBackground().setAlpha(75);
    }

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            infoFactory.MoveToPrevious();
            CInfo data = infoFactory.getCurrent();
            DisplayInfo(data);
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            infoFactory.MoveToNext();
            CInfo data = infoFactory.getCurrent();
            DisplayInfo(data);
        }
    };
    private void DisplayInfo(CInfo f) {
        txtDate.setText(f.getF日期());
        txtHw.setText(f.getF作業通知());
        txtSf.setText(f.getF用品通知());
        txtOt.setText(f.getF其他通知());
    }
}
