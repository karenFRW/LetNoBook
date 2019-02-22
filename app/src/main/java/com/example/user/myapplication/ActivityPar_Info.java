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

public class ActivityPar_Info extends AppCompatActivity {
    private TextView txtDate,txtIId,txtCId, txtTId, txtTitle;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    private TextView txtHw, txtSf, txtOt, txtSub, txtCC;
    private Intent intent = null;
    private SharedPreferences table;
    public static String classId, clsName;
    CInfoFactory infoFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        //@學生班級編號 @@學生班級名稱 從 ActivityPar_ViewDiary.java 的 fabInfo按鈕 傳過來
        intent = getIntent();
        classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
        clsName = intent.getStringExtra(CDictionary.List_viewInfoByClassName);
        infoFactory = new CInfoFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        txtDate = findViewById(R.id.txtDate);
        txtSub = findViewById(R.id.txtSub);
        txtCC = findViewById(R.id.txtCC);

        txtHw = findViewById(R.id.txtHw);
        txtHw.getBackground().setAlpha(75);
        txtSf = findViewById(R.id.txtSf);
        txtSf.getBackground().setAlpha(75);
        txtOt = findViewById(R.id.txtOt);
        txtOt.getBackground().setAlpha(75);
        txtIId = findViewById(R.id.txtClsId);
        txtCId = findViewById(R.id.txtCId);
        txtTId = findViewById(R.id.txtTId);

        btnFirst = findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(btnFirst_Click);
        btnLast = findViewById(R.id.btnLast);
        btnLast.setOnClickListener(btnLast_Click);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);


    }

    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else {
                infoFactory.MoveToLast();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_P", "日誌size<=0");
            }else {
                infoFactory.MoveToLast();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnFirst_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else {
                infoFactory.MoveToFirst();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else {
                infoFactory.MoveToPrevious();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked"+data.toString());
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else {
                infoFactory.MoveToNext();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked"+data.toString());
            }

        }
    };
    private void DisplayInfo(CInfo f) {
        int id = f.getfInfoId();
        txtIId.setText(String.valueOf(id));
        int cd = f.getFClassId();
        switch (cd){
            case 403:
                txtCId.setText("一年一班");
                break;
            case 401:
                txtCId.setText("一年二班");
                break;
            case 402:
                txtCId.setText("一年三班");
                break;
        }
        int tid = f.getF老師編號();
        txtTId.setText(String.valueOf(tid));
        txtCC.setText(String.valueOf(cd));

        txtDate.setText(f.getF日期());
        txtSub.setText(f.getF科目());
        txtHw.setText(f.getF作業通知());
        txtSf.setText(f.getF用品通知());
        txtOt.setText(f.getF其他通知());


    }
}
