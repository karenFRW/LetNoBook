package com.example.user.myapplication;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CDiaryFactory {
    private ArrayList<CDiary> list=new ArrayList<CDiary>();
    private int position=0;
    private String jsonStr = new String();
    private String stuId;

    private void LoadData(){
        new Thread(){
            @Override
            public void run() {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tDiaries");

                String uId = ActivityLogin.user;
                Log.d("LetNoBook_diaryFac登入者id", uId);
                Integer userId = Integer.valueOf(uId);

                String s = new String();
                //判斷目前使用者的身分, 取得要裝入列表的參數@stuId
                if(userId < 200){
                    //登入者是學生
                    stuId = uId;
                }else if((userId>=200) && (userId<300)){
                    stuId = ActivityTea_ViewDiary.studentId;
                }else if(userId>=300){
                    stuId = ActivityPar_ViewDiary.studentId;
                }

                Log.d("LetNoBook_diaryFactory","stuId==="+stuId);
                Log.d("LetNoBook", "Sd="+uId);
                Log.d("LetNoBook", "Pd="+ActivityPar_ViewDiary.studentId);
                Log.d("LetNoBook", "Td="+ActivityTea_ViewDiary.studentId);
                //解析jsonStr
                list.clear();
                Log.d("LetNoBook_diaryFactory","清空list");
                try {
                    JSONArray jArray = new JSONArray(jsonStr);

                    for(int i=0; i<jArray.length();i++){
                        JSONObject diary = jArray.getJSONObject(i);
                        String fDId = diary.getString("f日誌編號");
                        Log.d("LetNoBook_diaryFactory", "fDId:"+fDId);
                        String fDiary = diary.getString("f學生日誌文字");
                        Log.d("LetNoBook_diaryFactory", "fDiary:"+fDiary);
                        String fPic = diary.getString("f學生日誌照片");
                        String r = diary.getString("f日誌批改");
                        String fReply;
                        if((r.equals("null")) || (r.equals("")) || (r==null)){
                            fReply = "";
                        }else {
                            fReply = r;
                        }
                        Log.d("LetNoBook_diaryFactory", "fReply:"+fReply);
                        String d = diary.getString("f日期");
                        String fDate = d.substring(0, 10);
                        Log.d("LetNoBook_diaryFactory", "fDate:"+fDate);
                        String studentId = diary.getString("f學生編號");
                        Log.d("LetNoBook_diaryFactory", "stuId:"+studentId);

                        Log.d("LetNoBook_diaryFactory", "s+stuId:"+s+"+"+studentId);
                        if(stuId.equals(studentId)){


                            //當參數@stuId等於jsonStr裡的學生編號, 將此筆日誌加入列表
                            Log.d("LetNoBook_diaryFactory", "s+stuId:"+s+"="+studentId);
                            //(int f日誌編號, String f學生日誌文字, String f學生日誌照片
                            // String f日誌批改, String f日期, int f學生編號)
                            CDiary dy = new CDiary(Integer.parseInt(fDId),fDiary,
                                    null,fReply,
                                    fDate,Integer.valueOf(studentId));
                            list.add(dy);
                            Log.d("LetNoBook", "list.add("+dy.toString()+")");
                            Log.d("LetNoBook", "list.size()="+list.size());
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
    public void MoveToFirst(){
        position=0;
    }
    public void MoveToPrevious(){
        position--;
        if(position<0)
            position=0;
        Log.d("LetNoBook_diaryFactory","上一筆");
    }
    public void MoveToNext(){
        position++;
        if(position>=list.size())
            MoveToLast();
        Log.d("LetNoBook_diaryFactory","下一筆");
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

//    public Map<String,String> ToMap(CDiary diary){
//
//    }

    public int getSize(){
        return list.size();
    }
}
