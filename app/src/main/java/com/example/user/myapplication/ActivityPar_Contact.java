package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPar_Contact extends AppCompatActivity {
//    //虛擬Bar-返回鍵 : 至首頁
//    @Override
//    public void onBackPressed() {
////        super.onBackPressed();
//        intent = new Intent(ActivityPar_Contact.this, MainActivity.class);
//        startActivity(intent);
//        ActivityPar_Contact.this.finish();
//    }

    private Button btnPost, btnPut,btnSign;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    private String str留言id, str日期,str師言,str家言, str學編, str班編;
    private Boolean is簽章;
    private TextView txtDate,txtCId,txtStuId, txtClsId;
    private TextView txtTeaMsg, txtParMsg;
    private Intent intent;
    public static String stuId, stuName, classId;
    CCommunicationFactory commFactory;
    private SharedPreferences table;
    private String userId, familyId, parName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);


        //@學生編號 @學生姓名 @學生班級編號 從接口 mySimpleAdapterP 傳過來
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewCommById);
        stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
        classId = intent.getStringExtra(CDictionary.List_viewCommByClassId);

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        userId = table.getString(CDictionary.LoginAct_userFamilyId,null);
        familyId = table.getString(CDictionary.LoginAct_userFamilyId,null);
        parName = table.getString(CDictionary.LoginAct_userName,null);

        commFactory = new CCommunicationFactory();
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
        btnPost.setOnClickListener(btnPost_click);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setEnabled(false);
        btnPut = findViewById(R.id.btnPut);
        btnPut.setOnClickListener(btnPut_Click);
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);

    }

    private View.OnClickListener btnPost_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{

                intent = new Intent(ActivityPar_Contact.this, ActivityPar_Contact_post.class);
                intent.putExtra(CDictionary.List_viewCommById, stuId);
                intent.putExtra(CDictionary.List_viewCommByName, stuName);
                intent.putExtra(CDictionary.List_viewCommByClassId, classId);
                startActivity(intent);
                Log.d("LetNoBook_PC", "Go編輯交流");
            }catch (Exception e){
                e.printStackTrace();
                Log.d("LetNoBook_PC", "btnPost_crash:"+e.toString());
            }

        }
    };
    private View.OnClickListener btnPut_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(txtTeaMsg.getText().length()<=0){
                Toast.makeText(ActivityPar_Contact.this,"親愛的家長, 導師沒交代事項需要您回覆喔!",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnPut_留言沒內容");
            }else{
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
                intent = new Intent(ActivityPar_Contact.this, ActivityPar_Contact_edit.class);
                intent.putExtra(CDictionary.List_editCommById, str留言id);
                intent.putExtra(CDictionary.List_editCommByDate, str日期);
                intent.putExtra(CDictionary.List_editCommTeaMsg, str師言);
                intent.putExtra(CDictionary.List_editCommParMsg, str家言);
                intent.putExtra(CDictionary.List_editCommStuId, str學編);
                intent.putExtra(CDictionary.List_editCommStuName, stuName);
                intent.putExtra(CDictionary.List_editCommSign, is簽章);
                intent.putExtra(CDictionary.List_editCommClsId, str班編);
                startActivity(intent);
                Log.d("LetNoBook_PC", "GO回覆留言");
            }

        }
    };
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //paste--start
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "日誌size<=0");
            }else {
                commFactory.MoveToPrevious();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityPar_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnPreDay_Clicked"+data.toString());
            }
            //paste--End

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //paste--start
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "日誌size<=0");
            }else {
                commFactory.MoveToNext();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityPar_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnNextDay_Clicked"+data.toString());
            }
            //paste--End

        }
    };


    // btnOne_Click paste--start
    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityPar_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnLoad"+data.toString());
            }
        }
    };
    // btnOne_Click paste--End


    //btnLast_Click paste -- Start
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "日誌size<=0");
            }else {
                commFactory.MoveToLast();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityPar_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnLastDay_Clicked"+data.toString());
            }
        }
    };
    //btnLast_Click paste -- End


    //btnFirst_Click paste--Start
    private View.OnClickListener btnFirst_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(commFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Contact.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "日誌size<=0");
            }else {
                commFactory.MoveToFirst();
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
                Toast.makeText(ActivityPar_Contact.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PC", "btnFirstDay_Clicked"+data.toString());
            }
        }
    };
    //btnFirst_Click paste -- End



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
