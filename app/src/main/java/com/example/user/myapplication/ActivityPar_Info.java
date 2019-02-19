package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPar_Info extends AppCompatActivity {
    private Button btnPreDay, btnNextDay, btnList;
    private TextView txtDate, txtIId, txtCId;
    private TextView txtHw, txtHwSub, txtSfSub, txtSf, txtOtSub, txtOt;
    private Intent intent;
    public static String clsId, clsName;
    CInfoFactory infoFactory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        //@學生班級編號 @@學生班級名稱 從 ActivityPar_ViewDiary.java 的 fabInfo按鈕 傳過來
        intent = getIntent();
        clsId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
        clsName = intent.getStringExtra(CDictionary.List_viewInfoByClassName);
        infoFactory = new CInfoFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        txtIId = findViewById(R.id.txtIId);
        txtCId = findViewById(R.id.txtCId);
        btnList = findViewById(R.id.btnFirst);
        btnList.setOnClickListener(btnList_CLick);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtDate = findViewById(R.id.txtDate);
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

    private View.OnClickListener btnList_CLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final CInfo[] infoList = infoFactory.getAll();
            final String[] date = new String[infoList.length];
            int cid = Integer.parseInt(clsId);
            for (int i=0;i<infoList.length;i++){
                if(infoList[i].getFClassId()==cid){
                    date[i]=infoList[i].getF日期();
                }
            }
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ActivityPar_Info.this);
            builder1.setTitle("選擇日期").setItems(date, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    txtIId.setText(infoList[i].getfInfoId());
                    txtCId.setText(infoList[i].getFClassId());
                    txtDate.setText(infoList[i].getF日期());
                    txtHw.setText(infoList[i].getF作業通知());
                    txtSf.setText(infoList[i].getF用品通知());
                    txtOt.setText(infoList[i].getF其他通知());
                }
            }).show();

        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize() <=0){
                Toast.makeText(ActivityPar_Info.this, "系統整理中, 請稍後再查詢", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else{
                infoFactory.MoveToPrevious();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked");
            }
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize() <=0){
                Toast.makeText(ActivityPar_Info.this, "系通整理中, 請稍後再查詢", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else{
                infoFactory.MoveToNext();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityPar_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "btnNextDay_Clicked");
            }
        }
    };
    private void DisplayInfo(CInfo f) {
        txtIId.setText(f.getfInfoId());
        txtCId.setText(f.getFClassId());
        txtDate.setText(f.getF日期());
        txtHw.setText(f.getF作業通知());
        txtSf.setText(f.getF用品通知());
        txtOt.setText(f.getF其他通知());
        if(!f.getF作業通知().equals("null"))
            txtHwSub.setText(f.getF科目());
        else if(!f.getF用品通知().equals("null"))
            txtSfSub.setText(f.getF科目());
        else if(!f.getF其他通知().equals("null"))
            txtOtSub.setText(f.getF科目());
    }
}
