package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityStu_Contact extends AppCompatActivity {
    private Button btnPost, btnPut,btnSign;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    private TextView txtDate,txtCId,txtStuId, txtClsId;
    private TextView txtTeaMsg, txtParMsg;
    CCommunicationFactory commFactory;
    private Intent intent;
    private String stuId, stuName, str師id, str師名;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        commFactory = new CCommunicationFactory();
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewDiaryById);
        stuName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        str師id = intent.getStringExtra(CDictionary.LoginAct_teacherId);
        str師名 = intent.getStringExtra(CDictionary.LoginAct_teacherName);

        InitialComponent();
    }

    private void InitialComponent() {
        txtDate = findViewById(R.id.txtDate);
        txtCId = findViewById(R.id.txtCId);
        txtStuId = findViewById(R.id.txtStuId);
        txtClsId = findViewById(R.id.txtClsId);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        btnFirst = findViewById(R.id.btnFirst);
        btnLast = findViewById(R.id.btnLast);
        btnFirst.setOnClickListener(btnFirst_Click);
        btnLast.setOnClickListener(btnLast_Click);

        txtTeaMsg = findViewById(R.id.txtTea);
        txtParMsg = findViewById(R.id.txtPar);
        btnPost = findViewById(R.id.btnPost);
        btnPost.setVisibility(View.INVISIBLE); //設隱藏
        btnSign = findViewById(R.id.btnSign);
        btnSign.setEnabled(false);
        btnPut = findViewById(R.id.btnPut);
        btnPut.setVisibility(View.INVISIBLE); //設隱藏
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);
    }

    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityStu_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "btnLoad"+data.toString());
            }
        }
    };
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityStu_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "btnLastDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnFirst_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "日誌size<=0");
            }else {
                commFactory.MoveToFirst();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityStu_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "btnFirstDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "日誌size<=0");
            }else {
                commFactory.MoveToPrevious();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityStu_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "btnPreDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "日誌size<=0");
            }else {
                commFactory.MoveToNext();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityStu_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SC", "btnNextDay_Clicked"+data.toString());
            }

        }
    };
    private void DisplayComm(CCommunication com) {
        txtDate.setText(com.getF日期());
        txtTeaMsg.setText(com.getF老師交代事項());
        txtParMsg.setText(com.getF家長交代事項());
        int s = com.getF學生編號();
        txtStuId.setText(String.valueOf(s));
        int ii = com.getF交流編號();
        txtCId.setText(String.valueOf(ii));
        int cc = com.getFClassId();
        txtClsId.setText(String.valueOf(cc));



        //true==紅色；false==綠色
        if(com.isF家長簽名()){
            btnSign.setBackgroundResource(R.drawable.ripple_green);
            btnSign.setText("已簽名");
        }else{
            btnSign.setBackgroundResource(R.drawable.ripple_red);
            btnSign.setText("未簽名");
        }

    }

}
