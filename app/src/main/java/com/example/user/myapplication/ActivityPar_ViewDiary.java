package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class ActivityPar_ViewDiary extends AppCompatActivity {
    private FloatingActionButton fabSchedule, fabInfo;
    private TextView txtTop, txtDate, txtDiaryId, txtStuId;
    private Button btnPreDay, btnNextDay;
    private TextView txtDiary, txtReply;
    private CDiaryFactory diaryFactory;
    private Intent intent;
    public static String studentId = new String(), studentName = new String();
    public static String classId = new String();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par__view_diary);

        //@學生編號 @學生姓名 @學生班級編號 從接口 mySimpleAdapterP 傳過來
        intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewDiaryById);
        studentName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId); //再傳去通知事項當參數

        diaryFactory = new CDiaryFactory();

        InitialComponent();
    }

    private void InitialComponent() {

        fabInfo = findViewById(R.id.fabInfo);
        fabInfo.setOnClickListener(fabInfo_Click);
        txtDiaryId = findViewById(R.id.txtDiaryId);
        txtStuId = findViewById(R.id.txtStuId);
        txtTop = findViewById(R.id.txtTop);
        txtTop.setText(studentName + " 寶貝首頁");
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
    }

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityPar_ViewDiary.this, "系統整理中, 請稍後再查詢", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PI", "日誌size<=0");
            }else {
                diaryFactory.MoveToPrevious();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityPar_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PVD", "btnNextDay_Clicked");
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityPar_ViewDiary.this, "系統整理中, 請稍後再查詢", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PVD", "日誌size<=0");
            }else {
                diaryFactory.MoveToNext();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityPar_ViewDiary.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_PVD", "btnNextDay_Clicked"+data.toString());
            }

        }
    };

    private void DisplayDiary(CDiary d) {
        int y = d.getF日誌編號();
        int k = d.getF學生編號();
        txtDiaryId.setText(String.valueOf(y));
        txtStuId.setText(String.valueOf(k));
        txtDate.setText(d.getF日期());
        txtDiary.setText(d.getF學生日誌文字());
        txtReply.setText(d.getF日誌批改());
    }
    private View.OnClickListener fabInfo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String classStr = new String();
            switch (classId){
                case "403":
                    classStr = "1年1班";
                    break;
                case "401":
                    classStr = "1年2班";
                    break;
                case "402":
                    classStr = "1年3班";
                    break;
                default:
                    Snackbar.make(findViewById(R.id.fabMenu), "登入者資料不完整, 查無通知事項", Snackbar.LENGTH_SHORT).show();
                    break;
            }
//            Snackbar.make(findViewById(R.id.fabMenu), "開啟 " + classStr + " 的通知事項", Snackbar.LENGTH_SHORT).show();

            intent = new Intent(ActivityPar_ViewDiary.this, ActivityPar_Info.class);
            intent.putExtra(CDictionary.List_viewInfoByClassId, classId);
            intent.putExtra(CDictionary.List_viewInfoByClassName, classStr);
            startActivity(intent);
            Log.d("LetNoBook_ParViewDiary", "開啟 " + classStr + " 的通知事項");
        }
    };
}
