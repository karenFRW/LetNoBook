package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityTea_Contact_edit extends AppCompatActivity {
    //虛擬Bar-返回鍵 : 至首頁
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        intent = new Intent(ActivityTea_Contact_edit.this, ActivityTea_Contact.class);
        intent.putExtra(CDictionary.List_viewCommById,str學編);
        intent.putExtra(CDictionary.List_viewCommByName, str學名);
        intent.putExtra(CDictionary.List_viewCommByClassId, str班編);
        ActivityTea_Contact_edit.this.finish();
        Log.d("LetNoBook_TCP","虛擬Bar-返回");
    }
    private SharedPreferences table;
    private Intent intent;
    private TextView txtCId, txtStuId, txtClsId, txtTop, txtDate, txtTea, txtPar;
    private Button btnPP, btnCxl, btnSign;
    private String str留言id, str日期, str師言, str家言, str學編, str班編, str學名;
    private String str日期new, str師言new, str家言new;
    private int int留言idnew, int學編new, int班編new;
    private String dataToJson;
    boolean is簽章, is簽章new;
    private ProgressDialog pDialog;
    private String stuId, stuName, stuClsId;
    private Date today = new Date();
    private String userId;
    private int intUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        InitialComponent();

        table = getSharedPreferences(CDictionary.LoginAct_userInfo,MODE_PRIVATE);
        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_editCommStuId);
        stuName = intent.getStringExtra(CDictionary.List_editCommStuName);
        stuClsId = intent.getStringExtra(CDictionary.List_editCommClsId);
        userId = table.getString(CDictionary.LoginAct_userId,null);

        showDetails();

    }


    void showDetails(){
        intent = getIntent();
        str留言id = intent.getStringExtra(CDictionary.List_editCommById);
        str日期 = intent.getStringExtra(CDictionary.List_editCommByDate);
        str師言 = intent.getStringExtra(CDictionary.List_editCommTeaMsg);
        str家言 = intent.getStringExtra(CDictionary.List_editCommParMsg);
        str學編 = intent.getStringExtra(CDictionary.List_editCommStuId);
        str學名 = intent.getStringExtra(CDictionary.List_editCommStuName);
        is簽章 = intent.getBooleanExtra(CDictionary.List_editCommSign,false);
        str班編 = intent.getStringExtra(CDictionary.List_editCommClsId);
        txtCId.setText(str留言id);
        txtStuId.setText(str學編);
        txtClsId.setText(str班編);
        txtDate.setText(str日期);
        txtTea.setText(str師言);
        txtPar.setText(str家言);
        if(is簽章==true){
            //已簽名
            btnSign.setBackgroundResource(R.drawable.ripple_green);
            btnSign.setText("已簽名");
        }
        else{
            //未簽名
            btnSign.setBackgroundResource(R.drawable.ripple_red);
            btnSign.setText("未簽名");
        }

    }
    private void InitialComponent() {
        txtCId = findViewById(R.id.txtCId);
        txtStuId = findViewById(R.id.txtStuId);
        txtClsId = findViewById(R.id.txtClsId);
        txtTop = findViewById(R.id.txtTop);
        txtDate = findViewById(R.id.txtDate);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
//        txtDate.setText(simpleDateFormat.format(today));

        txtTea = findViewById(R.id.txtTea);
        txtTea.getBackground().setAlpha(70);

        txtPar = findViewById(R.id.txtPar);
        txtPar.setFocusableInTouchMode(false);
        txtPar.getBackground().setAlpha(70);

        btnPP = findViewById(R.id.btnPP);
        btnPP.setOnClickListener(btnPP_Click);
        btnCxl = findViewById(R.id.btnCxl);
        btnCxl.setOnClickListener(btnCxl_Click);
        btnSign = findViewById(R.id.btnSign);

    }


    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityTea_Contact_edit.this, ActivityTea_Contact.class);
            intent.putExtra(CDictionary.List_viewCommById,str學編);
            intent.putExtra(CDictionary.List_viewCommByName, str學名);
            intent.putExtra(CDictionary.List_viewCommByClassId, str班編);
            startActivity(intent);
            Log.d("LetNoBook_TCP","取消更改");
        }
    };
    private View.OnClickListener btnPP_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String cid = txtCId.getText().toString();
            int留言idnew = Integer.parseInt(cid);
            str日期new = txtDate.getText().toString();
            str師言new = txtTea.getText().toString();
            str家言new = txtPar.getText().toString();
            String ss = txtStuId.getText().toString();
            int學編new = Integer.parseInt(ss);
            String cc = txtClsId.getText().toString();
            int班編new = Integer.parseInt(cc);
            String strSign = btnSign.getText().toString();
            if (strSign.equals("未簽名")){
                is簽章 = false;
            }else
                is簽章 = true;

            //(int f交流編號, String f日期, String f老師交代事項,
            //String f家長交代事項, int f學生編號, boolean f家長簽名, int fClassId)

            new PutCommunicationTask().execute();

            Log.d("LetNoBook_TCP_btn送出","Comm類:" + dataToJson);

        }
    };

    //start//新增留言
    private class PutCommunicationTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_TCP","作業中");
            pDialog = new ProgressDialog(ActivityTea_Contact_edit.this);
            pDialog.setMessage("作業中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();

            //老師update自己的交代事項,  ID是f交流編號, content=後面是更新的內容
            //http://13.67.105.225/api06/ctra/CTUpdate/?id=1&content=Helloiamteacher
            String path = "http://52.246.164.133/api01/ctra/CTUpdate/?id=" + int留言idnew +"&content=";
            dataToJson = str師言new;
//            cp.doPost(path,dataToJson);
            String result = cp.doPost(path, dataToJson);
            Log.d("LetNoBook_TCP","api==" + path + dataToJson);
            Log.d("LetNoBook_TCP","上傳中");
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("Cannot find table 0."))
                Toast.makeText(ActivityTea_Contact_edit.this,
                        "無法上傳,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else{
                Toast.makeText(ActivityTea_Contact_edit.this,
                        "成功!",
                        Toast.LENGTH_LONG).show();

                //ActivityTea_Contact 需要這些參數取得相關資料
                //當畫面轉回去時, 要記得傳回去 @學id  @學名  @學班級
                intent = new Intent(ActivityTea_Contact_edit.this, ActivityTea_Contact.class);
                intent.putExtra(CDictionary.List_viewCommById, stuId);
                intent.putExtra(CDictionary.List_viewCommByName, stuName);
                intent.putExtra(CDictionary.List_viewCommByClassId, stuClsId);
                startActivity(intent);
                Log.d("LetNoBook_TCP","上傳成功 跳至 ActivityTea_Contact");
            }

            Log.d("LetNoBook_TCP", "新增/失敗: " +result);
        }
    }
    //end//新增留言
}
