package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityStu_Diary_edit extends AppCompatActivity {
    private TextView txtTop, txtDate, txtDId, txtSId;
    private EditText txtDiary, txtReply;
    private Button btnPP, btnCxl;
    private String userId, userName, tId, tName;
    private Intent intent;
    private SharedPreferences table = null;
    private Date today = new Date();
    private String str日誌編號=null, str日誌=null, str批改=null, str日期=null, str學編;
    private String dataToJson;
    private ProgressDialog pDialog;
    private int s=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        InitialComponent();
    }

    private void InitialComponent() {
        txtTop = findViewById(R.id.txtTop);
        txtDate = findViewById(R.id.txtDate);
        txtSId = findViewById(R.id.txtStuId);
        txtDId = findViewById(R.id.txtDiaryId);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
        txtReply.setFocusableInTouchMode(false);
        btnPP = findViewById(R.id.btnPP);
        btnPP.setOnClickListener(btnPP_Click);
        btnCxl = findViewById(R.id.btnCxl);
        btnCxl.setOnClickListener(btnCxl_Click);

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        intent = getIntent();
        userId = intent.getStringExtra(CDictionary.List_viewDiaryById);

        userName = intent.getStringExtra(CDictionary.List_viewDiaryByName);
        txtTop.setText(userName+" 正在編輯日誌頁");

        tId = intent.getStringExtra(CDictionary.LoginAct_teacherId);
        tName = intent.getStringExtra(CDictionary.LoginAct_teacherName);
        Log.d("LetNoBook_Diary_edit", "登:"+userId+","+userName+"," +tId+","+tName);
        txtSId.setText(userId);

        if(txtDate.getText().equals("") || txtDate.getText().equals(null)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
            txtDate.setText(simpleDateFormat.format(today));
        }
    }

    private View.OnClickListener btnPP_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str日誌 = txtDiary.getText().toString();
            str批改 = txtReply.getText().toString();
            str日期 = txtDate.getText().toString();
            str學編 = txtSId.getText().toString();
            //新增
            if(!txtDiary.getText().equals("") && !txtDiary.getText().equals(null))
            {
                //(String f學生日誌文字, String f日誌批改, String f日期, int f學生編號)
                CDiary data = new CDiary(0,str日誌,null,str批改,str日期,Integer.parseInt(str學編));
                dataToJson = data.toString();
                new PostDiaryTask().execute();
            }else
                Toast.makeText(ActivityStu_Diary_edit.this,"內容怎麼空白呢, 不要偷懶唷^^",Toast.LENGTH_LONG);
            Log.d("LetNoBook_btn送出","diary類" + dataToJson);
        }
    };
    //start//新增日誌
    private class PostDiaryTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_SDT","作業中");
            pDialog = new ProgressDialog(ActivityStu_Diary_edit.this);
            pDialog.setMessage("新增中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();
            String path = "http://52.246.164.133/api01/ctrA/insertDiary/?json=";
//            cp.doPost(path,dataToJson);
            String result = cp.doPost(path, dataToJson);
            Log.d("LetNoBook_SDT","上傳中");
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("新增成功"))
                Toast.makeText(ActivityStu_Diary_edit.this,
                        "無法上傳日誌,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ActivityStu_Diary_edit.this,
                        "成功新增!",
                        Toast.LENGTH_LONG).show();
            Log.d("LetNoBook_DiaryTask", "新增:" +result);
        }
    }
    //end//新增日誌

    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //取消新增,　回前頁
            intent = new Intent(ActivityStu_Diary_edit.this, ActivityStu.class);
            startActivity(intent);
        }
    };
}
