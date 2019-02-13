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
    private FloatingActionButton fabSchedule, fabInfo, fabComm;
    private TextView txtTop, txtDate;
    private Button btnPreDay, btnNextDay;
    private TextView txtDiary, txtReply;
    private Button btn班級課表, btn通知事項, btn親師留言;
    private CDiaryFactory diaryFactory;
    private Intent intent;
    private SharedPreferences table = null;
    SharedPreferences.Editor row = null;
    public static String studentId, studentName;
    public static String classId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par__view_diary);

        Intent intent = getIntent();
        studentId = intent.getStringExtra(CDictionary.List_viewDiaryById);
        studentName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
        diaryFactory = new CDiaryFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        fabSchedule = findViewById(R.id.fabSchedule);
        fabInfo = findViewById(R.id.fabInfo);
        fabComm = findViewById(R.id.fabComm);
        fabSchedule.setOnClickListener(fabSchedule_Click);
        fabInfo.setOnClickListener(fabInfo_Click);
        fabComm.setOnClickListener(fabComm_Click);
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
            diaryFactory.MoveToPrevious();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
            Log.d("LetNoBook_ParViewDiary", "btnNextDay_Clicked");
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diaryFactory.MoveToNext();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
            Log.d("LetNoBook_ParViewDiary", "btnNextDay_Clicked");
        }
    };

    private void DisplayDiary(CDiary d) {
        txtDate.setText(d.getF日期());
        txtDiary.setText(d.getF學生日誌文字());
        txtReply.setText(d.getF日誌批改());
    }
    private View.OnClickListener fabSchedule_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = getIntent();
            classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
            if(classId != null){
                Integer ci = Integer.valueOf(classId);
                switch (ci){
                    case 403:
                        intent = new Intent(ActivityPar_ViewDiary.this, ActivitySchedule_200.class);
                        startActivity(intent);
                        Log.d("LetNoBook_ParViewDiary", "班級課表_403_1年1班");
                        break;
                    case 401:
                        intent = new Intent(ActivityPar_ViewDiary.this, ActivitySchedule_201.class);
                        startActivity(intent);
                        Log.d("LetNoBook_ParViewDiary", "班級課表_401_1年2班");
                        break;
                    case 402:
                        intent = new Intent(ActivityPar_ViewDiary.this, ActivitySchedule_202.class);
                        startActivity(intent);
                        Log.d("LetNoBook_ParViewDiary", "班級課表_402_1年3班");
                        break;
                    default:
                        Log.d("LetNoBook_ParViewDiary", "查無班級課表");
                        Toast.makeText(getApplicationContext()
                                , "查無班級課表"
                                , Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };
    private View.OnClickListener fabInfo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = getIntent();
            classId = intent.getStringExtra(CDictionary.List_viewInfoByClassId);
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
            startActivity(intent);
            Log.d("LetNoBook_ParViewDiary", "開啟 " + classStr + " 的通知事項");
        }
    };
    private View.OnClickListener fabComm_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = getIntent();
            studentId =  intent.getStringExtra(CDictionary.List_viewDiaryById);
            intent = new Intent(ActivityPar_ViewDiary.this, ActivityPar_Contact.class);
            intent.putExtra(CDictionary.List_viewCommById, studentId);
            startActivity(intent);
            Log.d("LetNoBook_ParViewDiary", "Go親師留言");
        }
    };
}
