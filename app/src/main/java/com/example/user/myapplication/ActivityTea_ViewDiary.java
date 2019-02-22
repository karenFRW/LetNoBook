package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class ActivityTea_ViewDiary extends AppCompatActivity {
    TextView txtTitle, txtDate, txtDiaryId, txtSId,txtDiary,txtReply;
    Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    FloatingActionButton fabReply;
    private CDiaryFactory diaryFactory;
    public static String studentId = new String();
    public static String studentName = new String();
    public static String classId = new String();
    private String 日誌id,學生id,日期,日誌,師評;
    private Intent intent;

    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivityTea_ViewDiary.this, ActivityTea.class);
        startActivity(intent);
        ActivityTea_ViewDiary.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_view_diary);
        Log.d("LetNoBook_TeaViewDiary", "_onCreate");

        intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewDiaryById);
        studentName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        diaryFactory = new CDiaryFactory();

        InitialComponent();

    }

    private void InitialComponent() {
        txtDiaryId = findViewById(R.id.txtDiaryId);
        txtSId = findViewById(R.id.txtStuId);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        btnFirst = findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(btnFirst_Click);
        btnLast = findViewById(R.id.btnLast);
        btnLast.setOnClickListener(btnLast_Click);
        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("正在看"+studentName + " 的日誌");
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
        txtReply.setClickable(false);

        fabReply = findViewById(R.id.fabReply);
        fabReply.setOnClickListener(fabReply_Click);

    }

    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityTea_ViewDiary.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "日誌size<=0");
            }else {
                diaryFactory.MoveToLast();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityTea_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "btnOne"+data.toString());
            }
        }
    };
    private View.OnClickListener btnFirst_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityTea_ViewDiary.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToFirst();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityTea_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityTea_ViewDiary.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToLast();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityTea_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener fabReply_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityTea_ViewDiary.this,ActivityTea_Diary_edit.class);
            //日誌id,學生id,日期,日誌,師評
            日誌id = txtDiaryId.getText().toString();
            學生id = txtSId.getText().toString();
            日期 = txtDate.getText().toString();
            日誌 = txtDiary.getText().toString();
            師評 = txtReply.getText().toString();
            intent.putExtra(CDictionary.List_viewDiaryId,日誌id);
            intent.putExtra(CDictionary.List_viewDiaryById,學生id);
            intent.putExtra(CDictionary.List_viewDiaryDate,日期);
            intent.putExtra(CDictionary.List_viewDiaryStu,日誌);
            intent.putExtra(CDictionary.List_viewDiaryTea,師評);
            intent.putExtra(CDictionary.List_viewDiaryByName, studentName);
            startActivity(intent);


            Log.d("LetNoBook_TD", "GO回覆");

        }
    };

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityTea_ViewDiary.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "日誌size<=0");
            }else {
                diaryFactory.MoveToPrevious();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityTea_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "btnNextDay_Clicked"+data.toString());
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityTea_ViewDiary.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "日誌size<=0");
            }else {
                diaryFactory.MoveToNext();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityTea_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_TD", "btnNextDay_Clicked:"+data.toString());
            }

        }
    };

    private void DisplayDiary(CDiary d) {
        txtDiaryId.setText(String.valueOf(d.getF日誌編號()));
        txtSId.setText(String.valueOf(d.getF學生編號()));
        txtDate.setText(d.getF日期());
        txtDiary.setText(d.getF學生日誌文字());
        txtReply.setText(d.getF日誌批改());
    }

}
