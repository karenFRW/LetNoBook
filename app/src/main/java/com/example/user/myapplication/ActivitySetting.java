package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivitySetting extends AppCompatActivity {
    Intent intent;
    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivitySetting.this, MainActivity.class);
        startActivity(intent);
        ActivitySetting.this.finish();
    }
    private TextView txtTest;
    private ListView listView;
    private String json = null;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //繼承AppCompatActivity要用↓達成全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        txtTest = findViewById(R.id.txtTest);
        listView = findViewById(R.id.listView);
        new ParseTask().execute();
    }
    private class ParseTask  extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"stu_item", "stu_id_item"};
            int[] to = {R.id.txtItem, R.id.txtId};
            HashMap<String, String> hashmap;

            //取得(使用者)老師姓名, 當作查 tStudents 的 Key
            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            String teacherName = table.getString(CDictionary.LoginAct_userName, null);

            if(teacherName == null)
                teacherName = null;

            try {
                //抓學生資料表
                CHttpUrlConnection c = new CHttpUrlConnection();
                json = c.getTable("tStudents");
                Log.d("LetNoBook", "抓學生str"+json);
                JSONArray jArray = new JSONArray(json);
                Log.d("LetNoBook", "抓學生jA"+jArray.toString());
                arrayList.clear();

                for (int i = 0; i < jArray.length(); i++) {
                    //根據老師的姓名, 取得屬此(使用者)導師姓名的學生, 才能加入列表
                    JSONObject stu = jArray.getJSONObject(i);
                    String tName = stu.getString("f導師姓名");
                    String cId = stu.getString("f學生編號");
                    String name = stu.getString("f學生姓名");
                    hashmap = new HashMap<String, String>();
                    if(tName.equals(teacherName)){
                        hashmap.put("stu_id_item", cId);
                        hashmap.put("stu_item", name);
                        Log.d("LetNoBook", "tStudentsList:" +cId+name);
                        arrayList.add(hashmap);
                    }
                }
                adapter = new SimpleAdapter(ActivitySetting.this, arrayList, R.layout.item2, from, to);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return adapter;
        }

        @Override
        protected void onPostExecute(SimpleAdapter adapter) {
            super.onPostExecute(adapter);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String sId = String.valueOf(arrayList.get(position).get("stu_id_item"));
                    txtTest.setText(sId);
                }
            });
        }
    }

}
