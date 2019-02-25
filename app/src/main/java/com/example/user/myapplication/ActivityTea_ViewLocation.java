package com.example.user.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityTea_ViewLocation extends AppCompatActivity {
    JSONArray Jarray ;
    JSONObject jsonobject;
    Intent intent;
    String classId, stuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_kids_location);

        intent = getIntent();
        classId = intent.getStringExtra(CDictionary.List_viewLocationByClassId);
        stuName = intent.getStringExtra(CDictionary.List_viewLocationByName);
        Log.d("LetNoBook_Tea_Kids", "classId:"+classId);

        A();

        //執行抓位置
        try {
            Thread.sleep(1000);
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map)
            ).getMapAsync(onMapReady);
            Log.d("LetNoBookTea_KidsMap", "onCreate()");

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("LetNoBookTea_KidsMap", "onCreate()__Error="+e);
        } catch (NoClassDefFoundError noCls){
            noCls.printStackTrace();
            Log.d("LetNoBookTea_KidsMap", "onCreate()__Error="+noCls);
        }

    }

    //地圖元件
    OnMapReadyCallback onMapReady = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            for (int i = 0; i < Jarray.length(); i++) {

                try {
                    jsonobject = Jarray.getJSONObject(i);
                    String strName = jsonobject.getString("f姓名");
                    String strLong = jsonobject.getString("f經度");
                    String strLat = jsonobject.getString("f緯度");
                    String strTime = jsonobject.getString("f時間");

                    double douLong = Double.parseDouble(strLong);
                    double douLat = Double.parseDouble(strLat);

                    LatLng Jgps = new LatLng(douLat , douLong);

                    Log.d("LetNoBook_loadStuMaps", "getStuName:"+strName);
                    if(strName.equals(stuName)){
                        googleMap.addMarker(new MarkerOptions().
                                position(Jgps).
                                title(strName).
                                snippet(strTime));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Log.d("LetNoBookTea_KidsMap", "foreachend");

            LatLng gps = new LatLng(22.628881, 120.294568);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gps, 16));

            Log.d("LetNoBookTea_KidsMap", "onMapReady__End");

        }
    };


    //連線
    public JSONArray A() {


        new Thread() {
            @Override
            public void run() {

                //建立一個OkHttpClient物件
                OkHttpClient okHttpClient = new OkHttpClient();


                //建立一個請求物件

                Request request = new Request.Builder()
                        .url("http://52.246.164.133/api01/ctra/classgps/?id=403")
                        .build();
                //http://13.67.105.225/API03/ctra/classgps/?id=401
                //http://13.67.105.225/API03/ctra/familygps/?id=310
                //傳送請求獲取響應
                Response response = null;

                try {
                    response = okHttpClient.newCall(request).execute();
                    String Jsondata = response.body().string();
                    Jarray = new JSONArray(Jsondata);


                } catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.start();
        Log.d("LetNoBookTea_KidsMap", "End");

        return Jarray;

    }
}
