package com.example.user.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CCommunicationFactory {
    private ArrayList<CCommunication> list = new ArrayList<>();
    private int position = 0;
    private String jsonStr = new String();

    private void Load(){
        new Thread(){
            @Override
            public void run() {
                //取資料表
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tCommunications");

                String userId = ActivityLogin.user;
                Integer intUserId = Integer.valueOf(userId);

                //抓學生id
                String s = new String();
                if(intUserId<200){
                    s = userId;
                }else{
                    if(ActivityTea_Contact.stuId==null){
                        s = ActivityPar_Contact.studentId;
                    }else {
                        s = ActivityTea_Contact.stuId;
                    }
                }
                Log.d("LetNoBook", "取Ps:"+ActivityPar_Contact.studentId);
                Log.d("LetNoBook", "取Ts:"+ActivityTea_Contact.stuId);
                Log.d("LetNoBook", "取Us:"+ActivityLogin.user);
                list.clear();
                //解析json
                try {
                    JSONArray jArray = new JSONArray(jsonStr);
                    for (int i=0;i<jArray.length();i++){
                        JSONObject jo = jArray.getJSONObject(i);
                        String fd = jo.getString("f交流編號");
                        Log.d("LetNoBook", "取fd:"+fd);
                        int iId = Integer.parseInt(fd);
                        String d = jo.getString("f日期");
                        Log.d("LetNoBook", "取d:"+d);
                        String fDate = d.substring(0, 10);
                        String tMsg = jo.getString("f老師交代事項");
                        Log.d("LetNoBook", "取fd:"+fd);
                        String id = jo.getString("f學生編號");
                        Log.d("LetNoBook", "取id:"+id);
                        Boolean isSign = jo.getBoolean("f家長簽名");
                        Log.d("LetNoBook", "取isSign:"+isSign);
                        String cls = jo.getString("fClassId");
                        Log.d("LetNoBook", "取cls:"+cls);
                        String pMsg = jo.getString("f家長交代事項");

                        if(tMsg.equals(null) || tMsg.equals("null"))
                            tMsg = "空白";
                        if(pMsg.equals(null)|| pMsg.equals("null"))
                            pMsg = "空白";
                        Log.d("LetNoBook", "取tMsg:"+tMsg);
                        Log.d("LetNoBook", "取pMsg:"+pMsg);
                        if(id.equals(s)){
                            //(int f交流編號, String f日期, String f老師交代事項,
                            // String f家長交代事項, int f學生編號,
                            // boolean f家長簽名, int fClassId)
                            CCommunication cm = new CCommunication(iId,fDate,tMsg,pMsg,Integer.valueOf(s),isSign,Integer.valueOf(cls));
                            Log.d("LetNoBook", "取:"+cm.toString());
                            list.add(cm);
                            Log.d("LetNoBook", "size():" + list.size());
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public CCommunicationFactory(){
        Load();
        Log.d("LetNoBook", "CCommunicationFactory()");
    }
    public void MoveToFirst(){
        position=0;
    }
    public void MoveToPrevious(){
        position--;
        if(position<0)
            position=0;
    }
    public void MoveToNext(){
        position++;
        if(position>=list.size()){
            MoveToLast();
        }
    }
    public void MoveToLast(){
        position = list.size()-1;
    }
    public void MoveTo(int index){
        position = index;
    }
    public CCommunication getCurrent(){
        return list.get(position);
    }
    public CCommunication[] getAll(){
        return list.toArray(new CCommunication[list.size()]);
    }
    public int getSize(){
        return list.size();
    }
}
