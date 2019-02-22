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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPar extends AppCompatActivity {
    private Button btnKids;
    private ListView listView;
    private TextView txtTitle;
    private String jsonStr = null;
    private mySimpleAdapterP adapter = null;
    private SharedPreferences table;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    private Intent intent;
    private String familyId, parName, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par);

        txtTitle = findViewById(R.id.txtTitle);

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        userId = table.getString(CDictionary.LoginAct_userFamilyId,null);
        familyId = table.getString(CDictionary.LoginAct_userFamilyId,null);
        parName = table.getString(CDictionary.LoginAct_userName,null);
        btnKids = findViewById(R.id.btnKids);
        btnKids.setOnClickListener(btnKids_Click);

        listView = findViewById(R.id.listView);

        new ParseTask().execute();//取得小孩列表

    }


    private View.OnClickListener btnKids_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{

                table = getSharedPreferences(CDictionary.LoginAct_userInfo, MODE_PRIVATE);
                familyId = table.getString(CDictionary.LoginAct_userId, null);
                intent = new Intent(ActivityPar.this, ActivityPar_KidsLocation.class);
                intent.putExtra(CDictionary.LoginAct_userFamilyId, familyId);
                startActivity(intent);
                Log.d("LetNoBook_ActivityPar", "開啟 Kids Location");
            }catch (Exception e){
                e.printStackTrace();
                Log.d("LetNoBook_Par", "e:"+e.toString());
            }


        }
    };


    private class ParseTask extends AsyncTask<Void,Void,SimpleAdapter> {
        @Override
        protected SimpleAdapter doInBackground(Void... voids) {
            String[] from = {"stu_name", "stu_id", "txtCIsId", "familyId"};
            int[] to = {R.id.stu_name, R.id.stu_id, R.id.txtClsId, R.id.familyId};
            HashMap<String, String> hashmap;
            String sId;

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

                    sId = s.getString("f學生編號");
                    String name = s.getString("f學生姓名");
                    String t = s.getString("f導師姓名");
                    String cId = s.getString("fClassId");
                    String fd = s.getString("f家庭編號");

                    Log.d("LetNoBook", "Kids:" + sId + name + ",導師:" + t + ",班級:" + cId);

                    hashmap = new HashMap<String, String>();
                    hashmap.put("stu_id", sId);
                    hashmap.put("stu_name", name);
                    hashmap.put("txtCIsId", cId);
                    hashmap.put("familyId", fd);
                    Log.d("LetNoBook", "putIntoList==" + sId + name + "," + cId + "," + fd);
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

            Log.d("LetNoBook_ActivityPar", "取得小孩列表");
        }

    }


}
