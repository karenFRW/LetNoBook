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

public class ActivityTea_Info extends AppCompatActivity {
    private TextView txtDate,txtIId,txtCId, txtTId, txtTitle;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnNew, btnPut,btnOne;
    private TextView txtHw, txtSf, txtOt, txtSub, txtCC;
    private Intent intent = null;
    private SharedPreferences table;
    private String str日編, str班編, str科目, str作業, str用品, str其他, str日期, str師編;
    public static String classId, teacherId;
    CInfoFactory infoFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea__info);

        //@ClassId 從 ActivityTea 傳過來
        intent = getIntent();
        classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);

        table = getSharedPreferences(CDictionary.LoginAct_userInfo, MODE_PRIVATE);
        teacherId = table.getString(CDictionary.LoginAct_userId, null);

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

        btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(btnNew_Click);
        btnPut = findViewById(R.id.btnPut);
        btnPut.setOnClickListener(btnPut_Click);

    }


    private View.OnClickListener btnNew_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //新增 只傳classId, teachId
            str班編 = classId;
            str師編 = teacherId;

            intent = new Intent(ActivityTea_Info.this, ActivityTea_Info_post.class);
            intent.putExtra(CDictionary.List_editInfoByCId, str班編);
            intent.putExtra(CDictionary.List_editInfoByTId, str師編);
            Log.d("LetNoBook_PI", "GO_pass: ClassId_"+str班編+", TeachId_"+str師編);
            startActivity(intent);
            Log.d("LetNoBook_PI", "GO_Post日誌");
        }
    };
    private View.OnClickListener btnPut_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //更改 傳所有參數
            str日編 = txtIId.getText().toString();
            str日期 = txtDate.getText().toString();
            str班編 = txtCId.getText().toString();
            str科目 = txtSub.getText().toString();
            str作業 = txtHw.getText().toString();
            str用品 = txtSf.getText().toString();
            str其他 = txtOt.getText().toString();

            intent = new Intent(ActivityTea_Info.this, ActivityTea_Info_put.class);
            intent.putExtra(CDictionary.List_editInfoByIId, str日編);
            intent.putExtra(CDictionary.List_editInfoByDate, str日期);
            intent.putExtra(CDictionary.List_editInfoByCId, str班編);
            intent.putExtra(CDictionary.List_editInfoByTId, str師編);
            intent.putExtra(CDictionary.List_editInfoBySub, str科目);
            intent.putExtra(CDictionary.List_editInfoByHw, str作業);
            intent.putExtra(CDictionary.List_editInfoBySf, str用品);
            intent.putExtra(CDictionary.List_editInfoByOt, str其他);
            startActivity(intent);
            Log.d("LetNoBook_PI", "Update: "+str日編+", "+str日期+", "+str班編+", "+str科目+", "+str作業+","+str用品+", "+str其他);
            Log.d("LetNoBook_PI", "GO_Update日誌");
        }
    };
    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "日誌size<=0");
            }else {
                infoFactory.MoveToLast();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityTea_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "btnLoad"+data.toString());
            }
        }
    };
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "日誌size<=0");
            }else {
                infoFactory.MoveToLast();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityTea_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "btnLastDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnFirst_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "日誌size<=0");
            }else {
                infoFactory.MoveToFirst();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityTea_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TI", "btnFirstDay_Clicked"+data.toString());
            }
        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "日誌size<=0");
            }else {
                infoFactory.MoveToPrevious();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityTea_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "btnPreDay_Clicked"+data.toString());
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infoFactory.getSize()<=0){
                Toast.makeText(ActivityTea_Info.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "日誌size<=0");
            }else {
                infoFactory.MoveToNext();
                CInfo data = infoFactory.getCurrent();
                DisplayInfo(data);
                Toast.makeText(ActivityTea_Info.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SI", "btnNextDay_Clicked"+data.toString());
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
