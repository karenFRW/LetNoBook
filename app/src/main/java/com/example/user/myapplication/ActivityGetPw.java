package com.example.user.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityGetPw extends AppCompatActivity {
    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivityGetPw.this, ActivityLogin.class);
        startActivity(intent);
        ActivityGetPw.this.finish();
    }
    //宣告全域變數
    Intent intent;
    String d="1980-01-01";
    String ubday;
    EditText txtbirthday;
    EditText txtId;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //繼承AppCompatActivity要用↓達成全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_get_pw);
        InitialComponent();
    }

    private void InitialComponent() {
        txtbirthday = findViewById(R.id.txtbirthday);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(btnSubmit_click);
        txtId = findViewById(R.id.txtId);
    }

    private View.OnClickListener btnSubmit_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkVertification();
        }
    };

    //比對生日=資料庫的資料
    private void checkVertification() {
        //取得資料庫使用者的生日
        String strId = "";
        String strBDay = "";
        //核對使用者是否與儲存userInfo的同一位
        SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        SharedPreferences.Editor row = table.edit();
//        strId = table.getString()

        //d="1980-01-01"

        //使用者輸入的生日
        ubday = txtbirthday.getText().toString();

        //比對
        if(ubday.equals(d)){
            //設定密碼=d

            //建立提示視窗
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGetPw.this);
            builder.setTitle("重設密碼");
            builder.setMessage("密碼重置為生日YYYY/MM/DD");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext()
                            , "轉回登入頁"
                            , Toast.LENGTH_SHORT).show();
                    intent = new Intent(ActivityGetPw.this, ActivityLogin.class);
                    startActivity(intent);
                    ActivityGetPw.this.finish();
                }
            });
            Dialog msg = builder.create();
            msg.show();
        }else{
            Toast.makeText(getApplicationContext()
                    , "輸入錯誤"
                    , Toast.LENGTH_SHORT).show();

        }
    }


}
