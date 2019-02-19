package com.example.user.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CHttpPost {
    private URLConnection conn=null;
    private StringBuilder html = new StringBuilder();
    private OutputStream os = null;
    private BufferedWriter writer = null;

    protected String doPost(String path,String json){
    //path為api路徑, json是上傳的參數
        try {
            URL url = new URL(path+json);
            //執行連線(一行)
            conn=url.openConnection();
            //取串流(一行)
            InputStream streamIn=conn.getInputStream();
            //解碼,把剛剛的串流讀進 reader -- START
            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            StringBuilder html = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                html.append(line);
            }
            Log.d("LetNoBook_新增", "傳回結果"+html.toString());
            return html.toString();
        }catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("LetNoBook_insertDiary","URL錯誤:"+e);

        }catch (IOException e) {
            e.printStackTrace();
            Log.d("LetNoBook_insertDiary","IO錯誤:"+e);
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("LetNoBook_insertDiary","Exception錯誤:"+e);
        }

        return html.toString();
    }
    public static void main(String[] args){
        new CHttpPost();
    }
}
