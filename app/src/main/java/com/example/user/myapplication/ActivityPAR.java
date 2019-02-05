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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPAR extends AppCompatActivity {
    private ListView listView;
    private String jsonStr = null;
    private SimpleAdapter adapter = null;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par);

        listView = findViewById(R.id.listView);

        new ParseTask().execute();

    }
    private class ParseTask extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"name_item", "class_item", "teacher_item"};
            int[] to = {R.id.name_item, R.id.class_item, R.id.teacher_item};
            HashMap<String, String> hashmap;
            Integer id = 0;

            SharedPreferences table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            String famId = table.getString(CDictionary.LoginAct_userId, null);

            if(famId == null)
                famId = null;

            try {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("tParents/"+famId);

                JSONObject json = new JSONObject(jsonStr);
                JSONArray jArray = json.getJSONArray("tStudents");
                Log.d("LetNoBook", "小孩"+jArray.toString());
                arrayList.clear();

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject s = jArray.getJSONObject(i);

                    id = Integer.valueOf( s.getString("fClassId"));
                    String name = s.getString("f學生姓名");
                    String t = s.getString("f導師姓名");

                    Log.d("LetNoBook", "tStudent:" + id + name + t);

                    hashmap = new HashMap<String, String>();
                    hashmap.put("class_item", id.toString());
                    hashmap.put("teacher_item", t);
                    hashmap.put("name_item", name);
                    arrayList.add(hashmap);
                }

                adapter = new SimpleAdapter(ActivityPAR.this, arrayList, R.layout.item, from, to);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return adapter;
        }

        @Override
        protected void onPostExecute(final SimpleAdapter adapter) {
            super.onPostExecute(adapter);
            listView.setAdapter(adapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //設定選單為單選
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                }
            });

        }
    }


}
