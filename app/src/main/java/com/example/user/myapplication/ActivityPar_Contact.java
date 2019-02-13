package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPar_Contact extends AppCompatActivity {
    private Button btnMsg, btnSign;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtTeaMsg, txtParMsg;
    private Intent intent;
    public static String studentId;
    CCommunicationFactory commFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewDiaryById);

        commFactory = new CCommunicationFactory();
        InitialComponent();
    }

    private void InitialComponent() {
        txtDate = findViewById(R.id.txtDate);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtTeaMsg = findViewById(R.id.txtTea);
        txtParMsg = findViewById(R.id.txtPar);
        btnMsg = findViewById(R.id.btnMsg);
        btnMsg.setClickable(false);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setClickable(false);
    }
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commFactory.MoveToPrevious();
            CCommunication data = commFactory.getCurrent();
            DisplayComm(data);
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commFactory.MoveToNext();
            CCommunication data = commFactory.getCurrent();
            DisplayComm(data);
        }
    };
    private void DisplayComm(CCommunication com) {
        txtDate.setText(com.getF日期());
        txtTeaMsg.setText(com.getF家長交代事項());
        txtParMsg.setText(com.getF家長交代事項());
    }
}
