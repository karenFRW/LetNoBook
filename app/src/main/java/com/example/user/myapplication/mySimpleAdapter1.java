package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class mySimpleAdapter1 extends SimpleAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private int resource;
    private String[] from;
    private int[] to;
    private Intent intent;
    private String stuId;
    private String stuName;
    private String clsId;

    public mySimpleAdapter1(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }
    public int getCount() {
        //list選項的數量
        return data.size();
    }

    public Object getItem(int position) {
        //listItem[position]
        return data.get(position);
    }

    public long getItemId(int position) {
        //取得index
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        final int p = position;
        TextView txtStuName = v.findViewById(R.id.stu_name);
        txtStuName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "這是 " + data.get(p) + " 寶貝"
                        , Toast.LENGTH_SHORT).show();
            }
        });
        TextView txtStuId = v.findViewById(R.id.stu_id);
        ImageButton btnDiary = v.findViewById(R.id.btnDiary);
        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuId = data.get(p).get("stu_id");
                stuName = data.get(p).get("stu_name");
                clsId = data.get(p).get("txtClass");
                Toast.makeText(context
                        , "開啟 " + stuName + " 的日誌"
                        , Toast.LENGTH_LONG).show();
                intent = new Intent(context, ActivityPar_ViewDiary.class);
                intent.putExtra(CDictionary.List_viewDiaryById, stuId);
                intent.putExtra(CDictionary.List_viewCommById, stuId);
                intent.putExtra(CDictionary.List_viewDiaryByName, stuName);
                intent.putExtra(CDictionary.List_viewInfoByClassId, clsId);
                context.startActivity(intent);
                Log.d("LetNoBook", "開啟 " + stuName + " 的日誌");
            }
        });

        ImageButton btnLocation = v.findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuName = data.get(p).get("stu_name");
                Toast.makeText(context
                        , "查看 " + stuName + " 的位置"
                        , Toast.LENGTH_LONG).show();
                intent = new Intent(context, ActivityPar_ViewLocation.class);
                intent.putExtra(CDictionary.List_viewLocationByName, stuName);
                context.startActivity(intent);
                Log.d("LetNoBook", "查看 " + stuName + " 的位置");
            }
        });
        ImageButton btnCtt = v.findViewById(R.id.btnCtt);
        btnCtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stuId = data.get(p).get("stu_id");
                stuName = data.get(p).get("stu_name");
                Toast.makeText(context
                        , "聯絡 " + stuName + " 的導師"
                        , Toast.LENGTH_LONG).show();
                intent = new Intent(context, ActivityParChat.class);
                intent.putExtra(CDictionary.List_viewChatById, stuId);
                context.startActivity(intent);
                Log.d("LetNoBook", "聯絡 " + stuName + " 的導師");
            }
        });

        return v;
    }
}
