package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTea_Contact extends AppCompatActivity {
    private Button btnPost, btnPut,btnSign;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    private String str留言id, str日期,str師言,str家言, str學編, str班編;
    private Boolean is簽章;
    private TextView txtDate,txtCId,txtStuId, txtClsId;
    private TextView txtTeaMsg, txtParMsg;
    CCommunicationFactory commFactory;
    private Intent intent;
    public static String stuId, stuName, stuClsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        //@學生編號 @學生姓名 @學生班級編號 從接口 mySimpleAdapterT 傳過來
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewCommById);
        stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
        stuClsId = intent.getStringExtra(CDictionary.List_viewCommByClassId);

        commFactory = new CCommunicationFactory();
        InitialComponent();
    }

    private void InitialComponent() {
        txtCId = findViewById(R.id.txtCId);
        txtStuId = findViewById(R.id.txtStuId);
        txtDate = findViewById(R.id.txtDate);
        txtClsId = findViewById(R.id.txtClsId);
        txtTeaMsg = findViewById(R.id.txtTea);
        txtParMsg = findViewById(R.id.txtPar);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        btnFirst = findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(btnFirst_Click);
        btnLast = findViewById(R.id.btnLast);
        btnLast.setOnClickListener(btnLast_Click);

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(btnPost_Click);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setEnabled(false);
        btnPut = findViewById(R.id.btnPut);
        btnPut.setOnClickListener(btnPut_Click);
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);

    }

    private View.OnClickListener btnPost_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityTea_Contact.this, ActivityTea_Contact_post.class);
            intent.putExtra(CDictionary.List_viewCommById, stuId);
            intent.putExtra(CDictionary.List_viewCommByName, stuName);
            intent.putExtra(CDictionary.List_viewCommByClassId, stuClsId);

            startActivity(intent);
            Log.d("LetNoBook_TC", "Go編輯交流");
        }
    };

    private View.OnClickListener btnPut_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(txtParMsg.getText().length()<=0){
                Toast.makeText(ActivityTea_Contact.this,"親愛的導師, 家長沒交代事項需要您回覆喔!",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnPut_留言沒內容");
            }else {
                str留言id = txtCId.getText().toString();
                str日期 = txtDate.getText().toString();
                str師言 = txtTeaMsg.getText().toString();
                str家言 = txtParMsg.getText().toString();
                str學編 = txtStuId.getText().toString();
                if(btnSign.getText().toString().equals("已簽名")){
                    is簽章 = true;
                }else
                    is簽章 = false;
                str班編 = txtClsId.getText().toString();
                intent = new Intent(ActivityTea_Contact.this, ActivityTea_Contact_edit.class);
                intent.putExtra(CDictionary.List_editCommById, str留言id);
                intent.putExtra(CDictionary.List_editCommByDate, str日期);
                intent.putExtra(CDictionary.List_editCommTeaMsg, str師言);
                intent.putExtra(CDictionary.List_editCommParMsg, str家言);
                intent.putExtra(CDictionary.List_editCommStuId, str學編);
                intent.putExtra(CDictionary.List_editCommStuName, stuName);
                intent.putExtra(CDictionary.List_editCommSign, is簽章);
                intent.putExtra(CDictionary.List_editCommClsId, str班編);
                startActivity(intent);
                Log.d("LetNoBook_TC", "GO回覆留言");

            }

        }
    };

    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityTea_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnLoad"+data.toString());
            }
        }
    };

    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityTea_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnLastDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnFirst_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "日誌size<=0");
            }else {
                commFactory.MoveToFirst();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityTea_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnFirstDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "日誌size<=0");
            }else {
                commFactory.MoveToPrevious();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityTea_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnPreDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "日誌size<=0");
            }else {
                commFactory.MoveToNext();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityTea_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TC", "btnNextDay_Clicked"+data.toString());
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
