package com.example.user.myapplication;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CDiaryFactory {
    private ArrayList<CDiary> list=new ArrayList<CDiary>();
    private int position=0;
    private String jsonStr = new String();
    private SharedPreferences table = null;
    private String stuId = null;

    private void LoadData(){
        new Thread(){
            @Override
            public void run() {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tDiaries");

                String uId = ActivityLogin.user;
                Integer userId = Integer.valueOf(uId);

                String s = new String();

                //判斷目前使用者的身分, 取得要裝入列表的參數@stuId
                if((userId < 200)){
                    //登入者是學生
                    stuId = userId.toString();
                }else if((userId>=200) && (userId <=400)){
                    //登入者是導師或家長
                    //判斷此學生id從哪個Activity讀取
                    if(ActivityTea_ViewDiary.studentId != null){
                        s = ActivityTea_ViewDiary.studentId;
                    }else {
                        s = ActivityPar_ViewDiary.studentId;
                    }
                    Integer diaryById = Integer.valueOf(s);
                    stuId = diaryById.toString();
                }

                //解析jsonStr
                list.clear();
                try {
                    JSONArray jArray = new JSONArray(jsonStr);
                    for(int i=0; i<jArray.length();i++){
                        JSONObject diary = jArray.getJSONObject(i);
                        String fDiary = diary.getString("f學生日誌文字");
                        String r = diary.getString("f日誌批改");
                        String fReply;
                        if(!r.equals("null")){
                            fReply = r;
                        }else {
                            fReply = "尚未回應";
                        }
                        String d = diary.getString("f日期");
                        String fDate = d.substring(0, 10);
                        String studentId = diary.getString("f學生編號");

                        //當參數@stuId等於jsonStr裡的學生編號, 將此筆日誌加入列表
                        if(stuId.equals(studentId)){
                            CDiary sdiary = new CDiary(fDiary,fReply,fDate,Integer.valueOf(studentId));
                            list.add(sdiary);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public CDiaryFactory(){
        LoadData();
        Log.i("LetNoBook", "CDiaryFactory()");
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
    public CDiary getCurrent(){
        return list.get(position);
    }
    public CDiary[] getAll(){
        return list.toArray(new CDiary[list.size()]);
    }
}
