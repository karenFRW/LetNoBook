package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTea_ViewDiary extends AppCompatActivity {
    TextView txtTitle, txtDate, txtDiary, txtReply;
    Button btnPreDay, btnNextDay;
    CDiaryFactory diaryFactory = null;
    public static String studentId, studentName, classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_view_diary);
        Log.d("LetNoBook_TeaViewDiary", "_onCreate");

        Intent intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewDiaryById);
        studentName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        diaryFactory = new CDiaryFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(studentName + " 學生首頁");
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
    }
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diaryFactory.MoveToPrevious();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
            Log.d("LetNoBook_TeaViewDiary", "btnNextDay_Clicked");
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diaryFactory.MoveToNext();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
            Log.d("LetNoBook_TeaViewDiary", "btnNextDay_Clicked");
        }
    };

    private void DisplayDiary(CDiary d) {
        txtDate.setText(d.getF日期());
        txtDiary.setText(d.getF學生日誌文字());
        txtReply.setText(d.getF日誌批改());
    }

}
