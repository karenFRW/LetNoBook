package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityStu_Contact extends AppCompatActivity {
    private Button btn1, btn2;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtTeaMsg, txtParMsg;
    CCommunicationFactory commFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        commFactory = new CCommunicationFactory();
        InitialComponent();
    }

    private void InitialComponent() {
        txtDate = findViewById(R.id.txtDate);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtTeaMsg = findViewById(R.id.txtTea);
        txtParMsg = findViewById(R.id.txtPar);
        btn1 = findViewById(R.id.btnMsg);
        btn1.setClickable(false);
        btn2 = findViewById(R.id.btnSign);
        btn2.setClickable(false);
    }
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commFactory.MoveToPrevious();
            if(commFactory.getSize()<=0){
                DisplayNone();
            }else {
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
            }
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            commFactory.MoveToNext();
            if(commFactory.getSize()<=0){
                Log.d("LetNoBook", "getSize():" + commFactory.getSize());
                DisplayNone();
            }else {
                CCommunication data = commFactory.getCurrent();
                DisplayComm(data);
            }

        }
    };
    private void DisplayComm(CCommunication com) {
        txtDate.setText(com.getF日期());
        Log.d("LetNoBook_StuCon","cmmDate" + com.getF日期());
        txtTeaMsg.setText(com.getF老師交代事項());
        Log.d("LetNoBook_StuCon","cmmTea" + com.getF老師交代事項());
        txtParMsg.setText(com.getF家長交代事項());
        Log.d("LetNoBook_StuCon","cmmPar" + com.getF家長交代事項());
    }
    private void DisplayNone(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 E", Locale.TAIWAN);
        txtDate.setText(dateFormat.format(now));
        txtTeaMsg.setText("查無資料");
        txtParMsg.setText("查無資料");
    }

}
