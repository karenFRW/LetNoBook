package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityTea extends AppCompatActivity {
    private FloatingActionButton fabCS, fabMS, fabInfo;
    private TextView txtSubTitle, txtTitle;
    private ListView listView;
    private String json = null;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private mySimpleAdapterT adapter = null;
    private SharedPreferences table = null;
    private SharedPreferences.Editor row = null;
    private String teacherName = null;
    private Intent intent = null;
    private Integer teacherId = null;
    private Button btnKids=null;
    public static String strTId, strTName, strTCls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea);


        table = getSharedPreferences(CDictionary.LoginAct_userInfo, MODE_PRIVATE);
        strTId = table.getString(CDictionary.LoginAct_userId, null);
        strTName = table.getString(CDictionary.LoginAct_userName, null);
        strTCls = table.getString(CDictionary.LoginAct_userClassId, null);

        InitialComponent();
        new StuListTask().execute(); //學生清單

    }

    private void InitialComponent() {
        txtTitle = findViewById(R.id.txtTitle);
        txtSubTitle = findViewById(R.id.txtSubTitle);
        listView = findViewById(R.id.listView);

        fabMS =  findViewById(R.id.fabMS);
        fabMS.setOnClickListener(fabMS_Click);

        fabCS =  findViewById(R.id.fabCS);
        fabCS.setOnClickListener(fabCS_Click);

        fabInfo =  findViewById(R.id.fabInfo);
        fabInfo.setOnClickListener(fabInfo_Click);

        btnKids = findViewById(R.id.btnKids);
        btnKids.setOnClickListener(btnKids_Click);
    }


    private View.OnClickListener btnKids_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            teacherId = Integer.valueOf(strTId);

            switch (teacherId){
                case 200:
                    intent = new Intent(ActivityTea.this, ActivityTea_KidsLocation.class);
                    intent.putExtra(CDictionary.List_viewLocationByClassId, "403");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "200開啟 Students Location");
                    break;
                case 201:
                    intent = new Intent(ActivityTea.this, ActivityTea_KidsLocation.class);
                    intent.putExtra(CDictionary.List_viewLocationByClassId, "401");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "201開啟 Students Location");
                    break;
                case 202:
                    intent = new Intent(ActivityTea.this, ActivityTea_KidsLocation.class);
                    intent.putExtra(CDictionary.List_viewLocationByClassId, "402");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟 Students Location");
                    break;
                case 203:
                case 204:
                case 205:
                    Log.d("LetNoBook_ActivityTea", "不是導師; 無法使用此功能");
                    Toast.makeText(getApplicationContext()
                            , "不是導師; 無法使用此功能"
                            , Toast.LENGTH_LONG).show();
                    break;
                default:
                    Log.d("LetNoBook_ActivityTea", "查無教師詳細資料; 無法使用此功能");
                    Toast.makeText(getApplicationContext()
                            , "查無教師詳細資料; 無法使用此功能"
                            , Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };

    private View.OnClickListener fabMS_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //授課課表
            teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));
            switch (teacherId){
                case 200:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_200_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_403_1年1班");
                    break;
                case 201:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_201_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_401_1年2班");
                    break;
                case 202:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_202_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_402_1年3班");
                    break;
                case 203:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_203_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_自然");
                    break;
                case 204:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_204_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_音樂");
                    break;
                case 205:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_205_t.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "授課課表_體育");
                    break;
                default:
                    Log.d("LetNoBook_ActivityTea", "無教師詳細資料; 查無授課課表");
                    Toast.makeText(getApplicationContext()
                            , "無教師詳細資料; 查無授課課表"
                            , Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private View.OnClickListener fabCS_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //班級課表
            teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));

            switch (teacherId){
                case 200:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_200.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "班級課表_403_1年1班");
                    break;
                case 201:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_201.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "班級課表_401_1年2班");
                    break;
                case 202:
                    intent = new Intent(ActivityTea.this, ActivitySchedule_202.class);
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "班級課表_402_1年3班");
                    break;
                default:
                    Log.d("LetNoBook_ActivityTea", "不是導師, 查無授課課表");
                    Toast.makeText(getApplicationContext()
                            , "不是導師, 查無班級課表"
                            , Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private View.OnClickListener fabInfo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));

            switch (teacherId){
                case 200:
                    intent = new Intent(ActivityTea.this, ActivityTea_Info.class);
                    intent.putExtra(CDictionary.List_viewInfoByClassId, "403");
                    intent.putExtra(CDictionary.List_viewInfoByTeaId, "200");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟403通知事項");
                    break;
                case 201:
                    intent = new Intent(ActivityTea.this, ActivityTea_Info.class);
                    intent.putExtra(CDictionary.List_viewInfoByClassId, "401");
                    intent.putExtra(CDictionary.List_viewInfoByTeaId, "201");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟401通知事項");
                    break;
                case 202:
                    intent = new Intent(ActivityTea.this, ActivityTea_Info.class);
                    intent.putExtra(CDictionary.List_viewInfoByClassId, "402");
                    intent.putExtra(CDictionary.List_viewInfoByTeaId, "202");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟402通知事項");
                    break;
                case 203:
                    intent = new Intent(ActivityTea.this, ActivityTea_Info.class);
                    intent.putExtra(CDictionary.List_viewInfoByClassId, "403");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟通知事項");
                    break;
                case 204:
                case 205:
                    intent = new Intent(ActivityTea.this, ActivityTea_Info.class);
                    intent.putExtra(CDictionary.List_viewInfoByClassId, "403");
                    startActivity(intent);
                    Log.d("LetNoBook_ActivityTea", "開啟通知事項");
                    break;
                default:
                    Log.d("LetNoBook_ActivityTea", "查無教師詳細資料; 無法使用此功能");
                    Toast.makeText(getApplicationContext()
                            , "查無教師詳細資料; 無法使用此功能"
                            , Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    private class StuListTask extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"txtItem", "txtId", "txtClsId"};
            int[] to = {R.id.txtItem, R.id.txtId, R.id.txtClsId};
            HashMap<String, String> hashmap;

            //取得(使用者)老師姓名, 當作查 tStudents 的 Key
            table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            teacherName = table.getString(CDictionary.LoginAct_userName, null);
            txtTitle.setText(teacherName+" 老師首頁");

            if(teacherName == null)
                teacherName = null;

            try {
                //抓學生資料表
                CHttpUrlConnection c = new CHttpUrlConnection();
                json = c.getTable("institute/tStudents");

                Log.d("LetNoBook_ActivityTea", "抓學生str"+json);
                JSONArray jArray = new JSONArray(json);
                arrayList.clear();

                for (int i = 0; i < jArray.length(); i++) {
                    //根據登入者老師的姓名, 取得屬此導師(使用者)的學生, 才能加入列表
                    JSONObject stu = jArray.getJSONObject(i);
                    String tName = stu.getString("f導師姓名");
                    String sId = stu.getString("f學生編號");
                    String name = stu.getString("f學生姓名");
                    String clsId = stu.getString("fClassId");

                    hashmap = new HashMap<String, String>();
                    if(tName.equals(teacherName)){
                        hashmap.put("txtId", sId);
                        hashmap.put("txtItem", name);
                        hashmap.put("teacher", tName);
                        hashmap.put("txtClsId", clsId);
                        Log.d("LetNoBook_ActivityTea", "tStudentsList:" +sId+name);
                        arrayList.add(hashmap);
                    }
                }
                adapter = new mySimpleAdapterT(ActivityTea.this, arrayList, R.layout.item_t, from, to);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return adapter;
        }

        @Override
        protected void onPostExecute(SimpleAdapter adapter) {
            super.onPostExecute(adapter);
            listView.setAdapter(adapter);

            //非帶班導師不會有學生列表
            if (arrayList.size() == 0)
                txtSubTitle.setText("我的班級學生: \"不是導師, 查無學生名單\"");
        }
    }

}
