package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity {
    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivityLogin.this, MainActivity.class);
        startActivity(intent);
        ActivityLogin.this.finish();
    }

    //宣告全域變數
    private EditText txt帳號;
    private EditText txt密碼;
    private CheckBox ckb記帳密;
    private Button btn忘記密碼;
    private Button btn登入;
    private Button btn取消登入;
    private Intent intent;
    private String uId ;
    private String uPw;
    private Boolean isRemember = false;

    private String jsonString = new String();
    private JSONObject jo = null;
    public static String user = null;
    public static String clsId = null;

    //元件初始化
    private void InitialComponent() {
        txt帳號 = findViewById(R.id.txtId);
        txt密碼 = findViewById(R.id.txtPw);
        ckb記帳密 = findViewById(R.id.ckbRmbIdPw);
        btn忘記密碼 = findViewById(R.id.btnForgotIdPw);
        btn忘記密碼.setOnClickListener(btn忘記密碼_click);
        btn登入 = findViewById(R.id.btnLogin);
        btn登入.setOnClickListener(btn登入_click);
        btn取消登入 = findViewById(R.id.btnExit);
        btn取消登入.setOnClickListener(btn取消登入_click);
        Log.d("LetNoBook_LoginAct","初始化");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitialComponent();

        ckb記帳密.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged
                    (CompoundButton buttonView, boolean isChecked) {
                isRemember = isChecked;
            }
        });
        SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        SharedPreferences.Editor row = table.edit();
        txt帳號.setText(table.getString(CDictionary.LoginAct_userId, null));
        txt密碼.setText(table.getString(CDictionary.LoginAct_userPw, null));
        ckb記帳密.setChecked(table.getBoolean("Remember", true));
        if(ckb記帳密.isChecked()){
            txt帳號.setText(table.getString(CDictionary.LoginAct_userId, null));
            txt密碼.setText(table.getString(CDictionary.LoginAct_userPw, null));
        }else{
            txt帳號.setText(null);
            txt密碼.setText(null);
        }
        Log.d("LetNoBook","LoginAct載入帳密(若使用者有選擇記住帳密)");

    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo, MODE_PRIVATE);
        SharedPreferences.Editor row = table.edit();

        String userId = txt帳號.getText().toString();
        String userPw = txt密碼.getText().toString();
        row.putString(CDictionary.LoginAct_userId, userId);
        row.putString(CDictionary.LoginAct_userPw, userPw);
        row.putBoolean("Remember", isRemember);
        row.commit();
    }

    private View.OnClickListener btn登入_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("LetNoBook","LoginAct_btn登入_click");
            checkIdPw();
            Log.d("LetNoBook","LoginAct_核對帳密");

        }
    };

    private View.OnClickListener btn取消登入_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("LetNoBook","LoginAct_btn取消登入_click");
            intent = new Intent(ActivityLogin.this, MainActivity.class);
            Log.d("LetNoBook","從登入頁返回首頁");
            startActivity(intent);
            ActivityLogin.this.finish();
            Log.d("LetNoBook","LoginAct_finish");
        }
    };
    private View.OnClickListener btn忘記密碼_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("LetNoBook","LoginAct_btn忘記密碼_click");
            if(uId!=""){
                intent = new Intent(ActivityLogin.this, ActivityGetPw.class);
                Log.d("LetNoBook","從登入頁至忘記密碼頁");
                startActivity(intent);
                ActivityLogin.this.finish();
                Log.d("LetNoBook","登入頁finish");
            }else{
                Toast.makeText(getApplicationContext()
                        , "請先輸入帳號"
                        , Toast.LENGTH_SHORT).show();
                Log.d("LetNoBook","btn忘記密碼_click--Toast : 請輸入帳號");
            }
        }
    };

    //帳密比對資料庫
    private void checkIdPw() {
        uId = txt帳號.getText().toString();
        uPw = txt密碼.getText().toString();

        if(uId != null && uPw != null){
            if(Integer.valueOf(uId)<200){
                //此為學生
                new Thread(){
                    @Override
                    public void run() {
                        String path = "institute/tStudents/"+ uId;
                        try {
                            CHttpUrlConnection c = new CHttpUrlConnection();
                            jsonString = c.getTable(path);
                            jo = new JSONObject(jsonString);
                            user = jo.getString("f學生編號");
                            String pwd = jo.getString("f學生密碼");
                            final String famId = jo.getString("f家庭編號");
                            clsId = jo.getString("fClassId");
                            final String uName = jo.getString("f學生姓名");

                            String tName = jo.getString("f導師姓名");
                            Integer tId = 0;
                            if(tName.equals("李旻峻")){
                                tId = 200;
                            }else if(tName.equals("陳艷晴")){
                                tId = 201;
                            }else if(tName.equals("李美磬")){
                                tId = 202;
                            }

                            //取得生日start -> 只取年月日 -> 去除-符號
                            String bD = jo.getString("f學生生日"); //yyyy-mm-dd Thh:mm:ss
                            String d = bD.substring(0,10); //yyyy-mm-dd
                            final String bDay = d.replaceAll("-",""); //yyyymmdd
                            //取得生日end

                            Log.d("LetNoBook", "f學生編號" + user);
                            Log.d("LetNoBook", "f學生密碼" + pwd);
                            Log.d("LetNoBook", "f學生姓名" + uName);
                            Log.d("LetNoBook", "f家庭編號" + famId);
                            Log.d("LetNoBook", "fClassId" + clsId);
                            Log.d("LetNoBook", "f學生生日" + bDay);

                            //將登入者資訊記入"LoginAct_userInfo"
                            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
                            SharedPreferences.Editor row = table.edit();
                            row.putString(CDictionary.LoginAct_userId, user);
                            row.putString(CDictionary.LoginAct_userFamilyId,famId);
                            row.putString(CDictionary.LoginAct_userName,uName);
                            row.putString(CDictionary.LoginAct_userBirthday,bDay);
                            row.putString(CDictionary.LoginAct_userClassId,clsId);
                            row.putInt(CDictionary.LoginAct_teacherId, tId);
                            row.commit();

                            if(uId.equals(user)){
                                if(uPw.equals(pwd)){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext()
                                                    , "歡迎 "+uName+" 登入"
                                                    , Toast.LENGTH_SHORT).show();
                                            intent = new Intent(ActivityLogin.this, ActivityStu.class);
                                            startActivity(intent);
                                            ActivityLogin.this.finish();
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }else if((Integer.valueOf(uId)>=200)&&(Integer.valueOf(uId)<300)){
                //此為老師
                new Thread(){
                    @Override
                    public void run() {
                        String path = "tTeachers/"+ uId;
                        try {
                            CHttpUrlConnection c = new CHttpUrlConnection();
                            jsonString = c.getTable(path);
                            jo = new JSONObject(jsonString);
                            user = jo.getString("f老師編號");
                            String pwd = jo.getString("f老師密碼");
                            final String uName = jo.getString("f老師姓名");

                            //取得生日start -> 只取年月日 -> 去除-符號
                            String bD = jo.getString("f老師生日"); //yyyy-mm-dd Thh:mm:ss
                            String d = bD.substring(0,10); //yyyy-mm-dd
                            String bDay = d.replaceAll("-",""); //yyyymmdd
                            //取得生日end

                            Log.d("LetNoBook", "f老師編號" + user);
                            Log.d("LetNoBook", "f老師密碼" + pwd);
                            Log.d("LetNoBook", "f老師姓名" + uName);
                            Log.d("LetNoBook", "f老師生日" + bDay);

                            //將登入者資訊記入"LoginAct_userInfo"
                            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
                            SharedPreferences.Editor row = table.edit();
                            row.putString(CDictionary.LoginAct_userId,user);
                            row.putString(CDictionary.LoginAct_userName, uName);
                            row.putString(CDictionary.LoginAct_userBirthday,bDay);
                            row.commit();

                            if(uId.equals(user)){
                                if(uPw.equals(pwd)){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext()
                                                    , "歡迎 "+ uName+" 登入"
                                                    , Toast.LENGTH_SHORT).show();
                                            intent = new Intent(ActivityLogin.this, ActivityTea.class);
                                            startActivity(intent);
                                            ActivityLogin.this.finish();
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }else if((Integer.valueOf(uId)>=300)&&(Integer.valueOf(uId)<400)){
                //此為家長
                new Thread(){
                    @Override
                    public void run() {
                        String path = "tParents/"+ uId;
                        try {
                            CHttpUrlConnection c = new CHttpUrlConnection();
                            jsonString = c.getTable(path);
                            jo = new JSONObject(jsonString);
                            user = jo.getString("f家庭編號");
                            String pwd = jo.getString("f家長密碼");
                            final String uName = jo.getString("f家長姓名");

                            //取得生日start -> 只取年月日 -> 去除-符號
                            String bD = jo.getString("f家長生日"); //yyyy-mm-dd Thh:mm:ss
                            String d = bD.substring(0,10); //yyyy-mm-dd
                            String bDay = d.replaceAll("-",""); //yyyymmdd
                            //取得生日end

                            Log.d("LetNoBook", "f家庭編號" + user);
                            Log.d("LetNoBook", "f家長密碼" + pwd);
                            Log.d("LetNoBook", "f家長姓名" + uName);
                            Log.d("LetNoBook", "f家長生日" + bDay);

                            //將登入者資訊記入"LoginAct_userInfo"
                            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
                            SharedPreferences.Editor row = table.edit();
                            row.putString(CDictionary.LoginAct_userFamilyId,user);
                            row.putString(CDictionary.LoginAct_userName,uName);
                            row.putString(CDictionary.LoginAct_userBirthday,bDay);
                            row.commit();

                            if(uId.equals(user)){
                                if(uPw.equals(pwd)){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext()
                                                    , "歡迎 "+uName+" 登入"
                                                    , Toast.LENGTH_SHORT).show();
                                            intent = new Intent(ActivityLogin.this, ActivityPar.class);
                                            startActivity(intent);
                                            ActivityLogin.this.finish();
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            else{
                Toast.makeText(getApplicationContext()
                        , "查無此編號"
                        , Toast.LENGTH_SHORT).show();
                Log.d("LetNoBook", "笨蛋--笨蛋--笨蛋--笨蛋--笨蛋--笨蛋");
            }
        }
        ////登入-假資料
//        if((uId!=null) && (uPw!=null)){
//            if(uId.equals("s01")){
//                if(uPw.equals("s01")){
//                    Toast.makeText(ActivityLogin.this,
//                            "歡迎 " + uId + "登入",
//                            Toast.LENGTH_SHORT).show();
//                    intent = new Intent(ActivityLogin.this, ActivityStu.class);
//                    startActivity(intent);
//                    ActivityLogin.this.finish();
//
//                }else {
//                    Toast.makeText(ActivityLogin.this,
//                            "密碼錯誤",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }else{
//                Toast.makeText(ActivityLogin.this,
//                        "沒有登入權限",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(ActivityLogin.this,
//                    "請輸入帳號密碼",
//                    Toast.LENGTH_SHORT).show();
//        }

        ////使用者有勾選記住帳密
        if(ckb記帳密.isChecked()){
            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            SharedPreferences.Editor row = table.edit();
            row.putString(CDictionary.LoginAct_userId,uId);
            row.putString(CDictionary.LoginAct_userPw,uPw);
            row.commit();
        }
        Log.d("LetNoBook", "LoginAct_ckeckIdPw()------比對帳密碼");
    }
}
