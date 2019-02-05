package com.example.user.myapplication;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static android.os.StrictMode.setThreadPolicy;

public class ActivityDbTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        StrictMode.ThreadPolicy l_policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(l_policy);

        button = (Button) findViewById(R.id.btnGetData);
        textview = (TextView) findViewById(R.id.txtResult);
        // 按鈕點擊事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();

            }
        });
    }

    // 查詢
    private void test() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String ret = DbManagerTest.QuerySQL();
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putString("result", ret);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        };
        new Thread(run).start();
    }

    // 消息顯示到控件
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    String str = msg.getData().getString("result");
                    textview.setText(str);
                    break;
                default:
                    break;
            }
        };
    };

    Button button;
    public static TextView textview;
}
