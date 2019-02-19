package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTea_Info extends AppCompatActivity {
    private TextView txtDate,txtIId,txtCId, txtTId, txtTitle;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast;
    private TextView txtHw, txtSf, txtOt, txtSub;
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

        txtHw = findViewById(R.id.txtHw);
        txtHw.getBackground().setAlpha(75);
        txtSf = findViewById(R.id.txtSf);
        txtSf.getBackground().setAlpha(75);
        txtOt = findViewById(R.id.txtOt);
        txtOt.getBackground().setAlpha(75);
        txtIId = findViewById(R.id.txtIId);
        txtCId = findViewById(R.id.txtCId);
        txtTId = findViewById(R.id.txtTId);



    }
}
