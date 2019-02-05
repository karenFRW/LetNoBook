package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityTea extends AppCompatActivity {
    private Button btn授課課表;
    private Button btn班級課表;
    private Button btn通知事項;
    private TextView txtSubTitle, txtTitle;
    private ListView listView;
    private String json = null;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private mySimpleAdapter2 adapter = null;
    private SharedPreferences table = null;
    private String teacherName = null;
    private Intent intent = null;
    private Integer teacherId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea);
        txtTitle = findViewById(R.id.txtTitle);
        txtSubTitle = findViewById(R.id.txtSubTitle);
        listView = findViewById(R.id.listView);

        btn授課課表 =  findViewById(R.id.btn老師授課課表);
        btn授課課表.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               open老師授課課表();
            }
        });

        btn班級課表 =  findViewById(R.id.btn老師班級課表);
        btn班級課表.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open老師班級課表();
            }
        });

        btn通知事項 =  findViewById(R.id.btn老師通知事項);
        btn通知事項.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open老師通知事項();
            }
        });

        new ParseTask().execute();

    }
    private class ParseTask  extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"txtItem", "txtId"};
            int[] to = {R.id.txtItem, R.id.txtId};
            HashMap<String, String> hashmap;

            //取得(使用者)老師姓名, 當作查 tStudents 的 Key
            table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            teacherName = table.getString(CDictionary.LoginAct_userName, null);
            txtTitle.setText(teacherName+" 老師首頁");
            Log.d("", "未抓:"+json + "SP_tch:" + teacherName);
            if(teacherName == null)
                teacherName = null;

            try {
                //抓學生資料表
                CHttpUrlConnection c = new CHttpUrlConnection();
                json = c.getTable("tStudents");

                Log.d("LetNoBook", "抓學生str"+json);
                JSONArray jArray = new JSONArray(json);
                arrayList.clear();

                for (int i = 0; i < jArray.length(); i++) {
                    //根據老師的姓名, 取得屬此(使用者)導師姓名的學生, 才能加入列表
                    JSONObject stu = jArray.getJSONObject(i);
                    String tName = stu.getString("f導師姓名");
                    String cId = stu.getString("f學生編號");
                    String name = stu.getString("f學生姓名");
                    hashmap = new HashMap<String, String>();
                    if(tName.equals(teacherName)){
                        hashmap.put("txtId", cId);
                        hashmap.put("txtItem", name);
                        Log.d("LetNoBook", "tStudentsList:" +cId+name);
                        arrayList.add(hashmap);
                    }
                }
                adapter = new mySimpleAdapter2(ActivityTea.this, arrayList, R.layout.item2, from, to);

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

    public void open老師通知事項() {
        teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));

        switch (teacherId){
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
                intent = new Intent(this, ActivityTeaInfo.class);
                startActivity(intent);
                Log.d("LetNoBook", "開啟通知事項");
                break;
            default:
                Log.d("LetNoBook", "查無教師詳細資料; 無法使用此功能");
                Toast.makeText(getApplicationContext()
                        , "查無教師詳細資料; 無法使用此功能"
                        , Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void open老師班級課表() {
        //班級課表
        teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));

        switch (teacherId){
            case 200:
                intent = new Intent(this, ActivitySchedule_200.class);
                startActivity(intent);
                Log.d("LetNoBook", "班級課表_403_1年1班");
                break;
            case 201:
                intent = new Intent(this, ActivitySchedule_201.class);
                startActivity(intent);
                Log.d("LetNoBook", "班級課表_401_1年2班");
                break;
            case 202:
                intent = new Intent(this, ActivitySchedule_202.class);
                startActivity(intent);
                Log.d("LetNoBook", "班級課表_402_1年3班");
                break;
            default:
                Log.d("LetNoBook", "不是導師, 查無授課課表");
                Toast.makeText(getApplicationContext()
                        , "不是導師, 查無班級課表"
                        , Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void open老師授課課表() {
        //授課課表
        teacherId = Integer.valueOf(table.getString(CDictionary.LoginAct_userId, null));
        switch (teacherId){
            case 200:
                intent = new Intent(this, ActivitySchedule_200_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_403_1年1班");
                break;
            case 201:
                intent = new Intent(this, ActivitySchedule_201_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_401_1年2班");
                break;
            case 202:
                intent = new Intent(this, ActivitySchedule_202_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_402_1年3班");
                break;
            case 203:
                intent = new Intent(this, ActivitySchedule_203_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_自然");
                break;
            case 204:
                intent = new Intent(this, ActivitySchedule_204_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_音樂");
                break;
            case 205:
                intent = new Intent(this, ActivitySchedule_205_t.class);
                startActivity(intent);
                Log.d("LetNoBook", "授課課表_體育");
                break;
            default:
                Log.d("LetNoBook", "無教師詳細資料; 查無授課課表");
                Toast.makeText(getApplicationContext()
                        , "無教師詳細資料; 查無授課課表"
                        , Toast.LENGTH_LONG).show();
                break;
        }

    }
}
