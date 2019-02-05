package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class ActivityStu extends AppCompatActivity {
    //宣告變數
    private Button btn班級課表;
    private Button btn聯絡事項;
    private Button btn通知事項;
    private Button btn學生日誌;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtDiary, txtTeacherReply;
    private SharedPreferences table = null;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);

        btn班級課表 =  findViewById(R.id.btn學生班級課表);
        btn班級課表.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open學生班級課表();
            }
        });

        btn通知事項 =  findViewById(R.id.btn學生通知事項);
        btn通知事項.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open學生通知事項();
            }
        });

        btn聯絡事項 =  findViewById(R.id.btn學生聯絡事項);
        btn聯絡事項.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              open學生聯絡事項();
            }
        });

        btn學生日誌=  findViewById(R.id.btn學生日誌編輯);
        btn學生日誌.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open學生日誌編輯();
            }
        });

        btnPreDay = findViewById(R.id.btnPreDay);
        btnNextDay = findViewById(R.id.btnNextDay);





    }


    public void open學生日誌編輯() {
        intent = new Intent(this,ActivityStuDiary.class);
        startActivity(intent);

    }


    public void open學生聯絡事項() {
        intent = new Intent(this,ActivityStuContact.class);
        startActivity(intent);
    }

    public void open學生通知事項() {
        intent = new Intent(this,ActivityStuInfo.class);
        startActivity(intent);

    }

    public void open學生班級課表() {
        //Master test
        intent = new Intent(this,ActivityStuClassSyllabus.class);
        startActivity(intent);

    }
}
