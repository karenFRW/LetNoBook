package com.example.user.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CHttpUrlConnection {
    private StringBuilder result = new StringBuilder();
    private String data = new String();
    private HttpURLConnection conn = null;
    private BufferedReader bufferedReader = null;
    private String path = "http://13.67.105.225/api/";
    protected String getTable(String tableName) {
        try {
            //ip位址
            URL url = new URL(path + tableName);
            //宣告連線變數
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

            Log.d("LetNoBook", "連線結果=jsonStr=" + result);
            conn.disconnect();
        }catch (final IOException e){
            Log.d("LetNoBook", "連線結果=Error", e);
        }
        return result.toString();
    }
    public static void main(String[] args){
        new CHttpUrlConnection();
    }
}
