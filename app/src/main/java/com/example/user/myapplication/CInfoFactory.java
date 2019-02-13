package com.example.user.myapplication;

import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CInfoFactory {
    private ArrayList<CInfo> list = new ArrayList<>();
    private int position = 0;
    private String jsonStr = new String();

    private void Load(){
        new Thread(){
            @Override
            public void run() {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tInfoes");
                String userId = ActivityLogin.user;
                Integer intUserId = Integer.valueOf(userId);

                String userClsId = new String();
                //判斷目前使用者的身分, 取得要裝入列表的參數@userClsId
                if((intUserId < 200)){
                    //登入者是學生
                    userClsId = ActivityStu.classId;
                }else if((intUserId>=200) && (intUserId <=400)){
                    //登入者是導師或家長
                    //判斷此學生id從哪個Activity讀取
                    if(ActivityTea_ViewDiary.classId != null){
                        userClsId = ActivityTea_ViewDiary.classId;
                    }else {
                        userClsId = ActivityPar_ViewDiary.classId;
                    }
                }

                list.clear();
                //抓 @班級 的通知事項; 解析jsonStr
                try {
                    JSONArray jArray = new JSONArray(jsonStr);
                    for(int i=0;i<jArray.length();i++){
                        JSONObject info = jArray.getJSONObject(i);
                        String d = info.getString("f日期");
                        String fDate = d.substring(0, 10);
                        String hw = info.getString("f作業通知");
                        String staff = info.getString("f用品通知");
                        String other = info.getString("f其他通知");
                        String cc = info.getString("fClassId");
                        String tt = info.getString("f老師編號");
                        Integer cd = 0, tn = 0;
                        if(cc.isEmpty() || cc.equals(null) || cc.equals("null"))
                            cd = 0;
                        else
                        cd = Integer.parseInt(cc);
                        if(tt.isEmpty() || tt.equals(null) || tt.equals("null"))
                            tn = 0;
                        else
                            tn = Integer.parseInt(tt);

                        Log.d("LetNoBook", "selectedCls" + userClsId + "cc" + cc + "tt" + tt);

                        if(userClsId.equals(cc)){
                            CInfo cinfo = new CInfo(fDate,hw,staff,other, Integer.valueOf(cc),Integer.valueOf(tt));
                            list.add(cinfo);
                            Log.d("LetNoBook","list.size():" +list.size());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    public CInfoFactory(){
        Load();
        Log.d("LetNoBook", "CInfoFactory()");
    }
    public void MoveToPrevious(){
        position--;
        if(position<0)
            position=0;
    }
    public void MoveToNext(){
        position++;
        if(position>=list.size()){
            position=list.size()-1;
        }
    }
    public void MoveToLast(){
        position = list.size()-1;
    }
    public void MoveTo(int index){
        position = index;
    }
    public CInfo getCurrent(){
        return list.get(position);
    }
    public CInfo[] getAll(){
        return list.toArray(new CInfo[list.size()]);
    }
}
