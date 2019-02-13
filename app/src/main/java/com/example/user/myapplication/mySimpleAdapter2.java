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

public class mySimpleAdapter2 extends SimpleAdapter {
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item1. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item1.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private int resource;
    private String[] from;
    private int[] to;
    private Intent intent;

    public mySimpleAdapter2(Context context, ArrayList<HashMap<String, String>> data, int resource, String[] from, int[] to) {
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
                        , "這是 " + data.get(p) + " 同學"
                        , Toast.LENGTH_SHORT).show();
            }
        });
        TextView txtItem = v.findViewById(R.id.txtItem);
        txtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "這是 " + data.get(p) + " 同學"
                        , Toast.LENGTH_SHORT).show();
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
        ImageButton btnContact = v.findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context
                        , "聯絡 " + data.get(p).get("txtItem") + " 家長"
                        , Toast.LENGTH_SHORT).show();
                intent = new Intent(context, ActivityTea_Contact.class);
                intent.putExtra(CDictionary.List_viewCommById, data.get(p).get("txtId"));
                context.startActivity(intent);
                Log.d("LetNoBook", "聯絡 " + data.get(p).get("txtItem") + " 家長");
            }
        });
        return  v;
    }
}
