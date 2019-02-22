package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTea_Info_put extends AppCompatActivity {
    private Intent intent;
    private String str日編, str班編, str科目, str作業, str用品, str其他, str日期, str師編;
    private String str日編new, str班編new, str科目new, str作業new, str用品new, str其他new, str日期new, str師編new;
    private TextView txtTitle,txtIId, txtCId, txtDate, txtTId, txtSub;
    private EditText txtHw, txtSf, txtOt;
    private Button btnPut, btnCxl;
    private ProgressDialog pDialog;
    private String dataToJson;
    private Spinner spnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea__info_put);

        // @參數@ 從 ActivityTea_Info 傳過來
        intent = getIntent();
        str日編 = intent.getStringExtra(CDictionary.List_editInfoByIId);
        str日期 = intent.getStringExtra(CDictionary.List_editInfoByDate);
        str班編 = intent.getStringExtra(CDictionary.List_editInfoByCId );
        str科目 = intent.getStringExtra(CDictionary.List_editInfoBySub);
        str作業 = intent.getStringExtra(CDictionary.List_editInfoByHw);
        str用品 = intent.getStringExtra(CDictionary.List_editInfoBySf);
        str其他 = intent.getStringExtra(CDictionary.List_editInfoByOt);
        str師編 = intent.getStringExtra(CDictionary.List_editInfoByTId);

        InitialComponent();
        GetDetails();
    }

    private void GetDetails() {
        txtIId.setText(str日編);
        txtCId.setText(str班編);
        txtDate.setText(str日期);
        txtSub.setText(str科目);
        txtHw.setText(str作業);
        txtSf.setText(str用品);
        txtOt.setText(str其他);
        txtTId.setText(str師編);

//        final String[] item科 = new String[]{"本土語言","國語","數學","綜合","生活","健康","閱讀",""};
//        //建ArrayAdapter
//        ArrayAdapter<String> adapter科=new ArrayAdapter<String>(
//                this,android.R.layout.simple_list_item_single_choice,item科);
//        spnSub.setAdapter(adapter科);
        
    }

    private void InitialComponent() {
        txtTitle = findViewById(R.id.txtTitle);
        txtIId = findViewById(R.id.txtClsId);
        txtCId = findViewById(R.id.txtCId);
        txtDate = findViewById(R.id.txtDate);
        txtSub = findViewById(R.id.txtSub);
        txtHw = findViewById(R.id.txtHw);
        txtSf = findViewById(R.id.txtSf);
        txtOt = findViewById(R.id.txtOt);
        txtTId = findViewById(R.id.txtTId);

        btnCxl = findViewById(R.id.btnCxl);
        btnPut = findViewById(R.id.btnPut);
        btnCxl.setOnClickListener(btnCxl_Click);
        btnPut.setOnClickListener(btnPut_Click);
    }

    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //取消新增,　回前頁
            intent = new Intent(ActivityTea_Info_put.this, ActivityTea_Info.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener btnPut_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str日編new = txtIId.getText().toString();
            str日期new = txtDate.getText().toString();
            str班編new = txtCId.getText().toString();
            str科目new = txtSub.getText().toString();
            str作業new = txtHw.getText().toString();
            str用品new = txtHw.getText().toString();
            str其他new = txtOt.getText().toString();
            str師編new = txtTId.getText().toString();
            new AlertDialog.Builder(ActivityTea_Info_put.this)
                    .setTitle("修改通知事項")
                    .setIcon(R.mipmap.ic_edit)
                    .setMessage("確定修改嗎?")
                    .setPositiveButton("確認修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //更改通知
                            new PutInfoTask().execute();
                            Log.d("LetNoBook_TI_Put","Info類:" + dataToJson);
                        }
                    })
                    .setNegativeButton("取消修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Log.d("LetNoBook_TI_Put","btn取消" );
                        }
                    })

                    .show();

        }
    };

    //start//修改通知
    private class PutInfoTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_TI_PutTask","作業中");
            pDialog = new ProgressDialog(ActivityTea_Info_put.this);
            pDialog.setMessage("作業中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();
            //api參數 日誌編號@id 作業@hw 用品@item 其他@memo
            //http://13.67.105.225/api09/ctra/ITUpdate/?id=2&hw=英文第一課習作&item=帶英文字典&memo=下禮拜三小考
            String path = "http://13.67.105.225/api09/ctra/ITUpdate/?id=";
            dataToJson = str日編new+"&hw="+str作業new+"&item="+str用品new+"&memo="+str其他new;
            String result = cp.doPost(path, dataToJson);
            Log.d("LetNoBook_TI_Put","上傳中"+dataToJson);
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("修改成功"))
                Toast.makeText(ActivityTea_Info_put.this,
                        "無法修改通知事項,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ActivityTea_Info_put.this,
                        "成功修改通知事項!",
                        Toast.LENGTH_LONG).show();
            Log.d("LetNoBook_TI_Put", "修改:" +result);
        }
    }
    //end//修改通知
}
