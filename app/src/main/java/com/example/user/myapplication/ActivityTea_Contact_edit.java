package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTea_Contact_edit extends AppCompatActivity {
    private SharedPreferences table;
    private Intent intent;
    private TextView txtTop, txtDate, txtSId, txtCId, txtTea, txtPar;
    private Button btnPP, btnCxl;
    private String stuId, stuName;
    private String userId;
    private int intUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        InitialComponent();

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewCommById);
        stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
        userId = table.getString(CDictionary.LoginAct_userId,null);
        txtSId.setText(stuId);

    }

    private void InitialComponent() {
        txtTop = findViewById(R.id.txtTop);
        txtDate = findViewById(R.id.txtDate);
        txtTea = findViewById(R.id.txtTea);
        txtTea.getBackground().setAlpha(70);
        txtPar = findViewById(R.id.txtPar);
        txtPar.setEnabled(false);
        txtPar.getBackground().setAlpha(70);
        btnPP = findViewById(R.id.btnPP);
        btnCxl = findViewById(R.id.btnCxl);

    }
}
