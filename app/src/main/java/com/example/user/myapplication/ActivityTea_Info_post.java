package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityTea_Info_post extends AppCompatActivity {
    private String str日編, str班編, str科目, str作業, str用品, str其他, str日期, str師編;
    private String strDetails;
    private Intent intent;
    private ProgressDialog pDialog;
    private String dataToJson;
    private TextView txtTId, txtCId, txtDate, txtCls, txtSub;
    private EditText txtDetails;
    private Button btnPost, btnCxl;
    private Spinner spnCls, spnSub, spnInfo;
    private String str選班, str選科, str選項;
    private Date today = new Date();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea__info_post);

        InitialComponent();

        CheckOptions();
    }
    private void InitialComponent() {
        txtTId = findViewById(R.id.txtTId);
        txtCId = findViewById(R.id.txtClsId);
        txtDate = findViewById(R.id.txtDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        txtDate.setText(simpleDateFormat.format(today));

        txtCls = findViewById(R.id.txtCls);
        txtSub = findViewById(R.id.txtSub);
        txtDetails = findViewById(R.id.txtDetails);
        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(btnPost_Click);
        btnCxl = findViewById(R.id.btnCxl);
        btnCxl.setOnClickListener(btnCxl_Click);
        spnCls = findViewById(R.id.spnCls);
        spnSub = findViewById(R.id.spnSub);
        spnInfo = findViewById(R.id.spnInfo);
    }


    private View.OnClickListener btnPost_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str班編 = txtCId.getText().toString();
            str科目 = txtSub.getText().toString();

            str日期 = txtDate.getText().toString();

            str師編 = txtTId.getText().toString();

            str選班 = spnCls.getSelectedItem().toString();
            String cls = new String();

            switch (str選班){
                case "1年1班":
                    cls = "403";
                    break;
                case "1年2班":
                    cls = "402";
                    break;
                case "1年3班":
                    cls = "401";
                    break;
                    default:
                        break;
            }
            str選科 = spnSub.getSelectedItem().toString();

            //"今日作業","明日用品","其他提醒"
            str選項 = spnInfo.getSelectedItem().toString();

            //新增
            //(int fInfoId, String f日期, String f科目,
            //String f作業通知,
            //String f用品通知,
            //String f其他通知, int fClassId, int f老師編號
            CInfo data;
            switch (str選項){
                case "今日作業":
                    strDetails = txtDetails.getText().toString();
                    data = new CInfo(str日期,str選科,strDetails,"","",Integer.parseInt(cls),Integer.parseInt(str師編));
                    Log.d("LetNoBook_TIP_btn送出","Info類" + data.toString());
                    dataToJson = data.toString();
                    break;
                case "明日用品":
                    strDetails = txtDetails.getText().toString();
                    data = new CInfo(str日期,str選科,"",strDetails,"",Integer.parseInt(cls),Integer.parseInt(str師編));
                    Log.d("LetNoBook_TIP_btn送出","Info類" + data.toString());
                    dataToJson = data.toString();
                    break;
                case "其他提醒":
                    strDetails = txtDetails.getText().toString();
                    data = new CInfo(str日期,str選科,"","",strDetails,Integer.parseInt(cls),Integer.parseInt(str師編));
                    Log.d("LetNoBook_TIP_btn送出","Info類" + data.toString());
                    dataToJson = data.toString();
                    break;
            }

            new PostInfoTask().execute();

            // Toast.makeText(ActivityStu_Diary_edit.this,"內容怎麼空白呢, 不要偷懶唷^^",Toast.LENGTH_LONG);
            Log.d("LetNoBook_TIP_btn送出","Info類" + dataToJson);

        }
    };
    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //取消新增,　回前頁
            intent = new Intent(ActivityTea_Info_post.this, ActivityTea_Info.class);
            startActivity(intent);
        }
    };

    //start//新增通知
    private class PostInfoTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_TIP","作業中");
            pDialog = new ProgressDialog(ActivityTea_Info_post.this);
            pDialog.setMessage("新增中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();
            String path = "http://52.246.164.133/api01/ctra/insertINFO/?json=";
            //[{"f日期":"2019-02-20", "f科目":"本土語言", "f作業通知":"內容 M", "f用品通知":"", "f其他通知":"", "fClassId": 403, "f老師編號"=200}]
//            cp.doPost(path,dataToJson);
            String result = cp.doPost(path, dataToJson);
            Log.d("LetNoBook_TIP","上傳中");
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("新增成功"))
                Toast.makeText(ActivityTea_Info_post.this,
                        "無法上傳,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ActivityTea_Info_post.this,
                        "成功新增!",
                        Toast.LENGTH_LONG).show();
            Log.d("LetNoBook_TIP", "新增:" +result);
        }
    }
    //end//新增通知

    private void CheckOptions() {
        // @參數 從 ActivityTea_Info 傳過來
        intent = getIntent();
        str班編 = intent.getStringExtra(CDictionary.List_editInfoByCId );
        txtCId.setText(str班編);
        Log.d("LetNoBook_TIP", "收CId:"+str班編);
        str師編 = intent.getStringExtra(CDictionary.List_editInfoByTId);
        txtTId.setText(str師編);
        Log.d("LetNoBook_TIP", "收SId:"+str師編);
        int tid = Integer.parseInt(str師編);
        final ArrayAdapter<String> adapter班;
        final ArrayAdapter<String> adapter科;
        final String[] item班;
        final String[] item科;

        final String[] item事項 = new String[]{"今日作業","明日用品","其他提醒"};
        //事項選擇: 建ArrayAdapter
        ArrayAdapter<String> adapter事項=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_single_choice,item事項);
        spnInfo.setAdapter(adapter事項);
        /**选项选择监听*/
        spnInfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item事項[position], Toast.LENGTH_SHORT).show();
                Log.d("LetNoBook_TIP", "選項:"+ item事項[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spnInfo.setSelection(0);
            }
        });

        switch (tid){
            case 200:
                item班 = new String[]{"1年1班"} ;
                item科 = new String[]{"本土語言","國語","數學","綜合","生活","健康","閱讀",""};

                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            case 201:
                item班 = new String[]{"1年2班"} ;
                item科 = new String[]{"本土語言","國語","數學","綜合","生活","健康","閱讀",""};
                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            case 202:
                item班 = new String[]{"1年3班"} ;
                item科 = new String[]{"本土語言","國語","數學","綜合","生活","健康","閱讀",""};
                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            case 203:
                item班 = new String[]{"1年1班","1年2班"} ;
                item科 = new String[]{"自然"};
                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            case 204:
                item班 = new String[]{"1年1班","1年3班"} ;
                item科 = new String[]{"音樂"};
                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            case 205:
                item班 = new String[]{"1年2班","1年3班"} ;
                item科 = new String[]{"體育"};
                //班級選擇: 建ArrayAdapter
                adapter班=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item班);
                spnCls.setAdapter(adapter班);
                /**选项选择监听*/
                spnCls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item班[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選班:"+ item班[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnCls.setSelection(0);
                    }
                });
                //科目選擇: 建ArrayAdapter
                adapter科=new ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_single_choice,item科);
                spnSub.setAdapter(adapter科);
                /**选项选择监听*/
                spnSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ActivityTea_Info_post.this, "選擇了 " + item科[position], Toast.LENGTH_SHORT).show();
                        Log.d("LetNoBook_TIP", "選科:"+ item科[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spnSub.setSelection(0);
                    }
                });
                break;
            default:
                break;
        }
        

    }




}
