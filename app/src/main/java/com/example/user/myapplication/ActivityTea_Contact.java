package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTea_Contact extends AppCompatActivity {
    private Button btnMsg, btn2;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate,txtCId, txtStuId;
    private TextView txtTeaMsg, txtParMsg;
    CCommunicationFactory commFactory;
    private Intent intent;
    public static String stuId, stuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewCommById);

        stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
        commFactory = new CCommunicationFactory();
        InitialComponent();
    }

    private void InitialComponent() {
        txtCId = findViewById(R.id.txtCId);
        txtStuId = findViewById(R.id.txtStuId);
        txtDate = findViewById(R.id.txtDate);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtTeaMsg = findViewById(R.id.txtTea);
        txtParMsg = findViewById(R.id.txtPar);
        btnMsg = findViewById(R.id.btnMsg);
        btnMsg.setOnClickListener(btnMsg_Click);
        btn2 = findViewById(R.id.btnSign);
        btn2.setClickable(false);


    }

    private View.OnClickListener btnMsg_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityTea_Contact.this, ActivityTea_Contact_edit.class);
            intent.putExtra(CDictionary.List_viewCommById, stuId);
            intent.putExtra(CDictionary.List_viewCommByName, stuName);
            startActivity(intent);
            Log.d("LetNoBook_Tea", "Go編輯交流");
        }
    };
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
        txtCId.setText(String.valueOf(com.getF交流編號()));
        txtStuId.setText(String.valueOf(com.getF學生編號()));
        txtDate.setText(com.getF日期());
        txtTeaMsg.setText(com.getF老師交代事項());
        txtParMsg.setText(com.getF家長交代事項());

        if(com.isF家長簽名())
            btn2.setBackgroundColor(Color.rgb(255,106,106));
        else
            btn2.setBackgroundColor(Color.rgb(100,255,20));
    }
}
