package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityPar_ViewLocation extends AppCompatActivity {
    private Intent intent;
    public static String clsId, stuName, familyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_kids_location);

        //@學生姓名 @學生班級編號 @家庭編號 從接口 mySimpleAdapterP 傳過來
        intent = getIntent();
        clsId = intent.getStringExtra(CDictionary.List_viewLocationByClassId);
        stuName = intent.getStringExtra(CDictionary.List_viewLocationByName);
        familyId = intent.getStringExtra(CDictionary.List_viewLocationByFamilyId);

    }
}
