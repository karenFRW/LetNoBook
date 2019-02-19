package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPar_Contact extends AppCompatActivity {
    private Button btnMsg, btnSign;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtTeaMsg, txtParMsg;
    private Intent intent;
    public static String studentId, studentName, classId;
    CCommunicationFactory commFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);



        //@學生編號 @學生姓名 @學生班級編號 從接口 mySimpleAdapterP 傳過來
        intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewCommById);
        studentName = intent.getStringExtra(CDictionary.List_viewCommByName);
        classId = intent.getStringExtra(CDictionary.List_viewCommByClassId);

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
        btnMsg.setOnClickListener(btnMsg_Click);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(btnSign_Click);
    }

    private View.OnClickListener btnMsg_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityPar_Contact.this, ActivityTea_Contact_edit.class);
            startActivity(intent);
            Log.d("LetNoBook_Stu", "Go編輯交流");
        }
    };
    private View.OnClickListener btnSign_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                //清單小於零

            }else {
                //清單正常

            }
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
        txtTeaMsg.setText(com.getF老師交代事項());
        txtParMsg.setText(com.getF家長交代事項());
        if(com.isF家長簽名())
            btnSign.setBackgroundColor(Color.rgb(255,106,106));
    }
}
