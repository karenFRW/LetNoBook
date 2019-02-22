package com.example.user.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.StrictMode.setThreadPolicy;

public class ActivityStu extends AppCompatActivity {
    //宣告變數
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabAdd, fabSchedule, fabInfo, fabComm;
    private Button btnPreDay, btnNextDay, btnFirst, btnLast, btnOne;
    private TextView txtDate, txtDiaryId, txtStuId;
    private TextView txtDiary, txtTeacherReply, txtHttp;
    private SharedPreferences table = null;
    SharedPreferences.Editor row = null;
    private Intent intent = null;
    public static String stuId = null,classId = null, stuName=null, familyId=null;
    public static String tId;
    public static String tName = null;
    private CDiaryFactory diaryFactory;
    private LayoutInflater inflater = null;
    CDiary data;
    private ProgressDialog pDialog;
    private LocationManager manager=null;
    String strLong = "";
    String strLat = "";
    String dataToStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);

        //取登入者資料
        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        stuId = table.getString(CDictionary.LoginAct_userId, null);
        stuName = table.getString(CDictionary.LoginAct_userName,null);
        classId = table.getString(CDictionary.LoginAct_userClassId, null);
        tId = table.getString(CDictionary.LoginAct_teacherId,null);
        tName = table.getString(CDictionary.LoginAct_teacherName,null);
        familyId = table.getString(CDictionary.LoginAct_userFamilyId, null);
        //取登入者資料

        diaryFactory = new CDiaryFactory();
//        diaryFactory.MoveToLast();
//        CDiary data = diaryFactory.getCurrent();
//        DisplayDiary(data);

        InitialComponent();

        new PostLocationTask().execute();
    }

    private void InitialComponent() {
        txtDiaryId = findViewById(R.id.txtDiaryId);
        txtStuId = findViewById(R.id.txtStuId);
        txtDate = findViewById(R.id.txtDate);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtTeacherReply = findViewById(R.id.txtReply);
        txtTeacherReply.getBackground().setAlpha(70);

        btnPreDay = findViewById(R.id.btnPreDay);
        btnPreDay.setOnClickListener(btnPreDay_Click);
        btnNextDay = findViewById(R.id.btnNextDay);
        btnNextDay.setOnClickListener(btnNextDay_Click);
        btnFirst = findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(btnFirst_Click);
        btnLast = findViewById(R.id.btnLast);
        btnLast.setOnClickListener(btnLast_Click);

        fabAdd = findViewById(R.id.fabAdd);
        fabSchedule = findViewById(R.id.fabSchedule);
        fabInfo = findViewById(R.id.fabInfo);
        fabComm = findViewById(R.id.fabComm);
        fabAdd.setOnClickListener(fabAdd_Click);
        fabSchedule.setOnClickListener(fabSchedule_Click);
        fabInfo.setOnClickListener(fabInfo_Click);
        fabComm.setOnClickListener(fabComm_Click);
        fabMenu = findViewById(R.id.fabMenu);
        txtHttp = findViewById(R.id.txtHttp);

        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(btnOne_Click);


    }
    private View.OnClickListener btnOne_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityStu.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToLast();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityStu.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnOne"+data.toString());
            }
        }
    };
    private View.OnClickListener btnFirst_Click= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityStu.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToFirst();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityStu.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityStu.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToLast();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityStu.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
            }
        }
    };
    private View.OnClickListener btnPreDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityStu.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToPrevious();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityStu.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
            }

        }
    };

    private View.OnClickListener btnNextDay_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("LetNoBook_S_list.", "size="+diaryFactory.getSize());
            if(diaryFactory.getSize()<=0){
                Toast.makeText(ActivityStu.this, "系統整理中, 請稍後再查詢",Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "日誌size<=0");
            }else {
                diaryFactory.MoveToNext();
                CDiary data = diaryFactory.getCurrent();
                DisplayDiary(data);
                Toast.makeText(ActivityStu.this, "載入中", Toast.LENGTH_LONG);
                Log.d("LetNoBook_SD", "btnNextDay_Clicked"+data.toString());
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
            intent = new Intent(ActivityStu.this, ActivityStu_Diary_edit.class);
            intent.putExtra(CDictionary.List_viewDiaryById,stuId);
            intent.putExtra(CDictionary.List_viewDiaryByName,stuName);
            intent.putExtra(CDictionary.LoginAct_teacherId, tId);
            intent.putExtra(CDictionary.LoginAct_teacherName, tName);
            startActivity(intent);
            Log.d("LetNoBook_Stu", "Go新增日誌");
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
            intent.putExtra(CDictionary.List_viewDiaryById,stuId);
            intent.putExtra(CDictionary.List_viewDiaryByName,stuName);
            intent.putExtra(CDictionary.LoginAct_teacherId, tId);
            intent.putExtra(CDictionary.LoginAct_teacherName, tName);

            startActivity(intent);
            Log.d("LetNoBook_Stu", "Go親師留言");
        }
    };

    //start//上傳位置
    private class PostLocationTask extends AsyncTask<Void, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ActivityStu.this);
            pDialog.setMessage("系統更新中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected String doInBackground(Void... voids) {
            //自動取得 GPS 位置--Start
            manager=(LocationManager)getSystemService(LOCATION_SERVICE);
            ////取得GPS需要權限驗證二次確認 --start
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},0);
            }
            ////取得GPS需要權限驗證二次確認 --end

            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                    manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            {
                //GPS啟動或網路啟動
                Location gps= manager.getLastKnownLocation("network");
                if(gps!=null){
                    strLong = Double.toString(gps.getLongitude());
                    strLat = Double.toString(gps.getLatitude());
                }
                else{
                    txtHttp.setText("定位不到目前位置");
                    strLong = Double.toString(120.2932448);
                    strLat = Double.toString(22.6280744);
                    Log.d("LetNoBook_Location_GPS", "定位不到目前位置");
                }
            }else{
                txtHttp.setText("沒有打開定位功能");
                Toast.makeText(ActivityStu.this,"請打開定位功能",Toast.LENGTH_SHORT);
                Log.d("LetNoBook_Location_GPS", "沒有打開定位功能");
            }

            //--End
            //抓網路資料要用到網路，要用網路要開policy--start
            StrictMode.ThreadPolicy l_policy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
            setThreadPolicy(l_policy);
            //--end
            CHttpPost cp = new CHttpPost();
            String path = "http://13.67.105.225/api01/ctrA/insert/?json=";

            //取得現在時間--Start
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fNow = sdf.format(now);
            //(String f姓名, String f經度, String f緯度, String f時間, int fClassID, int f家庭編號)
            CLocation data = new CLocation(stuName,strLong,strLat,fNow,Integer.parseInt(classId),Integer.parseInt(familyId));
            Log.d("LetNoBook_PostLocation", "拿-" + stuName+","+strLong+","+strLat+","+fNow+","+familyId+","+classId);
            dataToStr = data.toString();
            Log.d("LetNoBook_PostLocation", "取類=" + dataToStr);
            String result = cp.doPost(path,dataToStr);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            if(s.equals("新增成功")){
                Toast.makeText(ActivityStu.this,"系統更新成功",Toast.LENGTH_SHORT);
                Log.d("LetNoBook_PostLocation", "位置Execute:" + s);
            }else{
                Toast.makeText(ActivityStu.this,"因為無法定位, 僅更新部分",Toast.LENGTH_SHORT);
                Log.d("LetNoBook_PostLocation", "位置Execute:" + s);
            }
        }
    }
    //end//上傳位置



}
