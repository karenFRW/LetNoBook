package com.example.user.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CStudentFactory {
    /* 宣告陣列存放資料
     * 宣告變數存放某筆資料的陣列位置
     * 新增 void 載入資料
     */
    private ArrayList<CStudent> list=new ArrayList<CStudent>();
    private int position=0;
    private String jsonStr = new String();

    public void MoveToFirst() {//至第一筆
        position=0;
    }
    public void MoveToPrevious(){//至上一筆
        position--; //當下的位置-1即是上一筆資料
        if(position<0) //若當下的位置-1小於0即是已在第一筆
            position=0;//若-1會小於0，使位置=0
    }
    public void MoveToNext(){//至下一筆
        position++;
        if(position>=list.size())
            MoveToLast();
    }
    public void MoveToLast(){//至最後一筆
        position=list.size()-1;
    }
    public void MoveTo(int idx){//至第?筆
        position=idx;
    }
    public CStudent GetCurrent(){
        return list.get(position) ;
    }
    public CStudent[] GetAll(){//取全部筆數
        return list.toArray(new CStudent[list.size()]);
    }
    public CStudentFactory(){
        LoadData();
        Log.i("LetNoBook", "CtStudentFactory()");
    }

    //經 WebApi 取得資料庫資料表的 JSON 檔
    private void LoadData() {
        new Thread() {
            @Override
            public void run() {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tStudents");
                Log.i("LetNoBook_StuFactory", "LoadData()" + jsonStr);

                Gson gson = null;
                Type listType = new TypeToken<ArrayList<CClass>>() {}.getType();
                ArrayList<CStudent> jsonArr = gson.fromJson(jsonStr, listType);
                for(CStudent s:jsonArr)
                    list.add(s);
            }
        }.start();

    }
}
