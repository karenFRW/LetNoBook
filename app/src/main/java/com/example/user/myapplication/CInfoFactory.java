package com.example.user.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CInfoFactory {
    private ArrayList<CInfo> list = new ArrayList<>();
    private ArrayList<CInfo> listByDate = new ArrayList<>();
    private int position = 0;
    private String jsonStr = new String();
    String userClsId = new String();

    private void Load(){
        new Thread(){
            @Override
            public void run() {
                CHttpUrlConnection c = new CHttpUrlConnection();
                jsonStr = c.getTable("institute/tInfoes");
                String userId = ActivityLogin.user;
                Integer intUserId = Integer.valueOf(userId);


                //判斷目前使用者的身分, 取得要裝入列表的參數@userClsId
                if((intUserId < 200)){
                    //登入者是學生
                    userClsId = ActivityStu.classId;
                }else if((intUserId>=200) && (intUserId <=400)){
                    //登入者是導師或家長
                    //判斷此學生id從哪個Activity讀取
                    if(ActivityTea_Info.classId != null){
                        userClsId = ActivityTea_Info.classId;
                    }else {
                        userClsId = ActivityPar_Info.classId;
                    }
                }

                list.clear();
                //抓 @班級 的通知事項; 解析jsonStr
                try {
                    JSONArray jArray = new JSONArray(jsonStr);
                    for(int i=0;i<jArray.length();i++){
                        JSONObject info = jArray.getJSONObject(i);
                        int fInfoId = Integer.parseInt(info.getString("fInfoId"));
                        String d = info.getString("f日期");
                        String fDate = d.substring(0, 10);
                        String ss = info.getString("f科目");
                        String sj;
                        if((ss.equals("")) || (ss.equals(null)) || (ss.equals("null")))
                            sj = "";
                        else
                            sj = ss;
                        String h = info.getString("f作業通知");
                        String sf = info.getString("f用品通知");
                        String o = info.getString("f其他通知");
                        String hw;
                        if((h.equals("")) || (h.equals(null)) || (h.equals("null")))
                            hw = "";
                        else
                            hw = h;

                        String staff;
                        if((sf.equals("")) || (sf.equals(null)) || (sf.equals("null")))
                            staff = "";
                        else
                            staff = sf;

                        String other;
                        if((o.equals("")) || (o.equals(null)) || (o.equals("null")))
                            other = "";
                        else
                            other = o;

                        String cc = info.getString("fClassId");
                        String tt = info.getString("f老師編號");
                        int cd = 0, tn = 0;
                        if(cc.isEmpty() || cc.equals(null) || cc.equals("null"))
                            cd = 0;
                        else
                            cd = Integer.parseInt(cc);
                        if(tt.isEmpty() || tt.equals(null) || tt.equals("null"))
                            tn = 0;
                        else
                            tn = Integer.parseInt(tt);

                        Log.d("LetNoBook","登入者C:"+userClsId+",jSonC:"+cc);
                        Log.d("LetNoBook", "including=" + fDate + "," +sj + "," +hw + "," +staff + "," +other + "," + cd + "," + tn);

                        if(userClsId.equals(cc)){
                            //(int fInfoId, String f日期, String f科目,
                            // String f作業通知, String f用品通知,
                            // String f其他通知, int fClassId, int f老師編號)
                            CInfo cinfo = new CInfo(fInfoId,fDate,sj,hw,staff,other,cd,tn);
                            list.add(cinfo);
                            Log.d("LetNoBook","list.size():" +list.size());

//                            Date date = new Date();
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
//                            String now = simpleDateFormat.format(date);
//                            String strDate = list.get(position).getF日期();
//                            if(now.equals(strDate))

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
    public int getSize(){
        return list.size();
    }
    public void ShowByDate(){


    }
}
