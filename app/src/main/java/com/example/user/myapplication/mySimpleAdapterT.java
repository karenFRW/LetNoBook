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

public class mySimpleAdapterT extends SimpleAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private int resource;
    private String[] from;
    private int[] to;
    private Intent intent;

    public mySimpleAdapterT(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        final int p = position;

        TextView txtId = v.findViewById(R.id.txtId);
        txtId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        ,  data.get(p).get("txtItem") + " 同學, 學生編號:"+data.get(p).get("txtId")
                        , Toast.LENGTH_SHORT).show();
                Log.d("LetNoBook_mySA:",data.get(p).toString());
            }
        });

        TextView txtItem = v.findViewById(R.id.txtItem);
        txtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , data.get(p).get("txtItem") + " 同學, 學生編號:"+data.get(p).get("txtId")
                        , Toast.LENGTH_SHORT).show();
                Log.d("LetNoBook_mySA:",data.get(p).toString());
            }
        });

        ImageButton btnView = v.findViewById(R.id.btnDiary);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "開啟 " + data.get(p).get("txtItem") + " 的日誌"
                        , Toast.LENGTH_SHORT).show();
                intent = new Intent(context, ActivityTea_ViewDiary.class);
                intent.putExtra(CDictionary.List_viewDiaryById, data.get(p).get("txtId"));
                intent.putExtra(CDictionary.List_viewDiaryByName, data.get(p).get("txtItem"));
                context.startActivity(intent);
                Log.d("LetNoBook", "開啟 " + data.get(p).get("txtItem") + " 的日誌");
            }
        });

        ImageButton btnLocation = v.findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "查看 " + data.get(p).get("txtItem") + " 的位置"
                        , Toast.LENGTH_LONG).show();
                intent = new Intent(context, ActivityPar_ViewLocation.class);
                intent.putExtra(CDictionary.List_viewLocationByClassId, data.get(p).get("txtClsId"));
                intent.putExtra(CDictionary.List_viewLocationByName, data.get(p).get("txtItem"));
                context.startActivity(intent);
                Log.d("LetNoBook", "查看 " + data.get(p).get("stu_name") + " 的位置");
            }
        });

        TextView txtClsId = v.findViewById(R.id.txtClsId);
        ImageButton btnContact = v.findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "聯絡 " + data.get(p).get("txtItem") + " 家長"
                        , Toast.LENGTH_SHORT).show();
                intent = new Intent(context, ActivityTea_Contact.class);
                intent.putExtra(CDictionary.List_viewCommById, data.get(p).get("txtId"));
                intent.putExtra(CDictionary.List_viewCommByName, data.get(p).get("txtItem"));
                intent.putExtra(CDictionary.List_viewCommByClassId, data.get(p).get("txtClsId"));
                context.startActivity(intent);

                Log.d("LetNoBook", "留言板: " + data.get(p));
            }
        });
        return  v;
    }
}
