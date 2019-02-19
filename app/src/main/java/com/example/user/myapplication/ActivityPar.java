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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPar extends AppCompatActivity {
    private Button btnKids;
    private ListView listView;
    private String jsonStr = null;
    private mySimpleAdapterP adapter = null;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private SharedPreferences table = null;
    private Intent intent;
    private String familyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par);

        btnKids = findViewById(R.id.btnKids);
        btnKids.setOnClickListener(btnKids_Click);

        listView = findViewById(R.id.listView);

        new ParseTask().execute();//取得小孩列表

    }
    private View.OnClickListener btnKids_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            table = getSharedPreferences(CDictionary.LoginAct_userInfo, MODE_PRIVATE);
            familyId = table.getString(CDictionary.LoginAct_userId, null);
            intent = new Intent(ActivityPar.this, ActivityPar_KidsLocation.class);
            intent.putExtra(CDictionary.LoginAct_userFamilyId, familyId);
            startActivity(intent);
            Log.d("LetNoBook_ActivityPar", "開啟 Kids Location");
            }
    };
    private class ParseTask extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"stu_name", "stu_id", "familyId"};
            int[] to = {R.id.stu_name, R.id.stu_id, R.id.familyId};
            HashMap<String, String> hashmap;
            Integer sId = 0;

            table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
            String famId = table.getString(CDictionary.LoginAct_userId, null);

            if(famId == null)
                famId = null;

            try {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tParents/"+famId);

                JSONObject json = new JSONObject(jsonStr);
                JSONArray jArray = json.getJSONArray("tStudents");
                arrayList.clear();

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject s = jArray.getJSONObject(i);

                    sId = Integer.valueOf( s.getString("f學生編號"));
                    String name = s.getString("f學生姓名");
                    String t = s.getString("f導師姓名");
                    String cId = s.getString("fClassId");
                    String fd = s.getString("f家庭編號");

                    Log.d("LetNoBook", "tStudent:" + sId + name + ",導師:" + t + ",班級:" + cId);

                    hashmap = new HashMap<String, String>();
                    hashmap.put("stu_id", sId.toString());
                    hashmap.put("stu_name", name);
                    hashmap.put("txtClass", cId);
                    hashmap.put("familyId", fd);
                    arrayList.add(hashmap);
                }

                adapter = new mySimpleAdapterP(ActivityPar.this, arrayList, R.layout.item_p, from, to);
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

            Log.d("LetNoBook_ActivityPar", "取得小孩列表");
        }

    }


}
