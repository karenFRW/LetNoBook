package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityStu_Info extends AppCompatActivity {
    private Button btnPreDay, btnNextDay;
    private TextView txtDate,txtIId,txtCId;
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
        txtCId = findViewById(R.id.txtCId);
        txtIId = findViewById(R.id.txtIId);
        txtHwSub = findViewById(R.id.txtSub);
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
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "日誌size<=0");
            }else {
                infoFactory.MoveToPrevious();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityStu_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "btnNextDay_Clicked"+data.toString());
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityStu_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "日誌size<=0");
            }else {
                infoFactory.MoveToNext();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityStu_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "btnNextDay_Clicked"+data.toString());
            }

        }
    };
    private void DisplayInfo(CInfo f) {
        int id = f.getfInfoId();
        txtIId.setText(String.valueOf(id));
        int cd = f.getFClassId();
        txtCId.setText(String.valueOf(cd));
        txtDate.setText(f.getF日期());
        txtHw.setText(f.getF作業通知());
        txtSf.setText(f.getF用品通知());
        txtOt.setText(f.getF其他通知());

        if((!f.getF作業通知().equals("null")) || (!f.getF作業通知().equals("")))
            txtHwSub.setText(f.getF科目());
        else if(!f.getF用品通知().equals("null"))
            txtSfSub.setText(f.getF科目());
        else if(!f.getF其他通知().equals("null"))
            txtOtSub.setText(f.getF科目());
    }
}
