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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivitySetting extends AppCompatActivity {
    Intent intent;
    private ArrayList<CCommunication> list = new ArrayList<>();
    CCommunicationFactory comFactory;

    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivitySetting.this, MainActivity.class);
        startActivity(intent);
        ActivitySetting.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        comFactory = new CCommunicationFactory();
        btnLast = findViewById(R.id.btnLast);
        btnLast.setOnClickListener(btnLast_Click);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(btnNext_Click);
        txtH = findViewById(R.id.txtH);
        txtO = findViewById(R.id.txtO);
        txtS = findViewById(R.id.txtS);
        new GetCommTask().execute();
    }
    private View.OnClickListener btnLast_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            comFactory.MoveToPrevious();
            CCommunication data = comFactory.getCurrent();
            txtH.setText(data.getF老師交代事項());
            txtO.setText(data.getF家長交代事項());
            txtS.setText(data.getF日期());
        }
    };
    private View.OnClickListener btnNext_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            comFactory.MoveToNext();
            CCommunication data = comFactory.getCurrent();
            txtH.setText(data.getF老師交代事項());
            txtO.setText(data.getF家長交代事項());
            txtS.setText(data.getF日期());
        }
    };
    private class GetCommTask extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            try {
                url = new URL(path);//宣告連線變數
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(8000);
                conn.setRequestMethod("GET");
                conn.connect();

                //宣告串流變數 = 取連線的串流
                InputStream inputStream = conn.getInputStream();
                //緩存串流
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //串流給字串
                String str;
                while((str = bufferedReader.readLine()) != null){
                    data = data + str + "\n";
                    result.append(str);
                }
                bufferedReader.close();
                inputStream.close();

                Log.d("LetNoBook", "getComm結果==" + result);
                conn.disconnect();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }catch (final IOException e){
                Log.d("LetNoBook", "getComm-Error", e);
            }
            return result.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jArray = new JSONArray(s);
                for(int i=0;i<jArray.length();i++){
                    JSONObject Comm = jArray.getJSONObject(i);
                    String d = Comm.getString("f日期");
                    String fDate = d.substring(0, 10);
                    String tMsg = Comm.getString("f老師交代事項");
                    String id = Comm.getString("f學生編號");
                    Boolean isSign = Comm.getBoolean("f家長簽名");
                    String cls = Comm.getString("fClassId");

                    if(tMsg.equals(null)|| tMsg.equals("null"))
                        tMsg = "空白";
                    String pMsg = Comm.getString("f家長交代事項");
                    if(pMsg.equals(null)|| pMsg.equals("null"))
                        pMsg = "空白";
                    Log.d("LetNoBook", "d-"+fDate+"tMsg-"+tMsg+"pMsg-"+pMsg+"s-"+id+"cls-"+cls);

                    CCommunication cmm;
                    list.add(new CCommunication(fDate, tMsg, pMsg,Integer.valueOf(id),isSign,Integer.valueOf(cls)));
                    Log.d("LetNoBook","list.size():" +list.size());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    private Button btnLast, btnNext;
    private TextView txtH,txtS,txtO;
    private StringBuilder result = new StringBuilder();
    private String data = new String();
    private HttpURLConnection conn = null;
    private BufferedReader bufferedReader = null;
    private String path = "http://13.67.105.225/api/getComm.aspx/?searchStuId=111";
}
