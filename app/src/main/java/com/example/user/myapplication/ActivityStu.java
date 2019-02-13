package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class ActivityStu extends AppCompatActivity {
    //宣告變數
    private FloatingActionButton fabAdd, fabSchedule, fabInfo, fabComm;
    private Button btnPreDay, btnNextDay;
    private TextView txtDate;
    private TextView txtDiary, txtTeacherReply;
    private SharedPreferences table = null;
    SharedPreferences.Editor row = null;
    private Intent intent = null;
    public static String stuId = null;
    public static String classId = null;
    private CDiaryFactory diaryFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        stuId = table.getString(CDictionary.LoginAct_userId, null);
        Log.d("LetNoBook_Stu", "學生:"+stuId);
        classId = table.getString(CDictionary.LoginAct_userClassId, null);
        diaryFactory = new CDiaryFactory();

        InitialComponent();
    }

    private void InitialComponent() {
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtTeacherReply = findViewById(R.id.txtReply);
        txtTeacherReply.getBackground().setAlpha(70);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);

        fabAdd = findViewById(R.id.fabAdd);
        fabSchedule = findViewById(R.id.fabSchedule);
        fabInfo = findViewById(R.id.fabInfo);
        fabComm = findViewById(R.id.fabComm);
        fabAdd.setOnClickListener(fabAdd_Click);
        fabSchedule.setOnClickListener(fabSchedule_Click);
        fabInfo.setOnClickListener(fabInfo_Click);
        fabComm.setOnClickListener(fabComm_Click);
    }

    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diaryFactory.MoveToPrevious();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diaryFactory.MoveToNext();
            CDiary data = diaryFactory.getCurrent();
            DisplayDiary(data);
        }
    };

    private void DisplayDiary(CDiary d) {
        txtDate.setText(d.getF日期());
        txtDiary.setText(d.getF學生日誌文字());
        txtTeacherReply.setText(d.getF日誌批改());
    }

    private View.OnClickListener fabInfo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityStu.this, ActivityStu_Info.class);
            startActivity(intent);
            Log.d("LetNoBook_Stu", "Go通知事項");
        }
    };
    private View.OnClickListener fabAdd_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };
    private View.OnClickListener fabSchedule_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            classId = table.getString(CDictionary.LoginAct_userClassId, null);
            if(classId != null){
                Integer ci = Integer.valueOf(classId);
                switch (ci){
                    case 403:
                        intent = new Intent(ActivityStu.this, ActivitySchedule_200.class);
                        startActivity(intent);
                        Log.d("LetNoBook_Stu", "班級課表_403_1年1班");
                        break;
                    case 401:
                        intent = new Intent(ActivityStu.this, ActivitySchedule_201.class);
                        startActivity(intent);
                        Log.d("LetNoBook_Stu", "班級課表_401_1年2班");
                        break;
                    case 402:
                        intent = new Intent(ActivityStu.this, ActivitySchedule_202.class);
                        startActivity(intent);
                        Log.d("LetNoBook_Stu", "班級課表_402_1年3班");
                        break;
                    default:
                        Log.d("LetNoBook_ActivityStu", "查無班級課表");
                        Toast.makeText(getApplicationContext()
                                , "查無班級課表"
                                , Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    private View.OnClickListener fabComm_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityStu.this, ActivityStu_Contact.class);
            startActivity(intent);
            Log.d("LetNoBook_Stu", "Go親師留言");
        }
    };

}
