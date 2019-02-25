package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class ActivityPar_Contact_post extends AppCompatActivity {
    private Intent intent;
    private TextView txtCId, txtStuId, txtClsId, txtTop, txtDate, txtTea, txtPar;
    private Button btnPP, btnCxl, btnSign;
    private String str日期, str師言, str家言;
    private int int學編, int班編;
    private String dataToJson;
    boolean is簽章;
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

        intent = getIntent();
        stuId = intent.getStringExtra(CDictionary.List_viewCommById);
        stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
        stuClsId = intent.getStringExtra(CDictionary.List_viewCommByClassId);

        txtClsId.setText(stuClsId);
        txtStuId.setText(stuId);


    }

    private void InitialComponent() {
        txtCId = findViewById(R.id.txtCId);
        txtStuId = findViewById(R.id.txtStuId);
        txtClsId = findViewById(R.id.txtClsId);
        txtTop = findViewById(R.id.txtTop);
        txtDate = findViewById(R.id.txtDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        txtDate.setText(simpleDateFormat.format(today));

        txtTea = findViewById(R.id.txtTea);
        txtTea.getBackground().setAlpha(70);
        txtTea.setClickable(false);

        txtPar = findViewById(R.id.txtPar);
        txtPar.getBackground().setAlpha(70);

        btnPP = findViewById(R.id.btnPP);
        btnPP.setOnClickListener(btnPP_Click);
        btnCxl = findViewById(R.id.btnCxl);
        btnCxl.setOnClickListener(btnCxl_Click);
        btnSign = findViewById(R.id.btnSign);
        btnSign.setBackgroundResource(R.drawable.ripple_green);
        btnSign.setText("已簽名");

    }
    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityPar_Contact_post.this, ActivityPar_Contact.class);
            startActivity(intent);
            Log.d("LetNoBook_PCP","取消");
        }
    };
    private View.OnClickListener btnPP_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{

                intent = getIntent();
                stuId = intent.getStringExtra(CDictionary.List_viewCommById);
                stuName = intent.getStringExtra(CDictionary.List_viewCommByName);
                stuClsId = intent.getStringExtra(CDictionary.List_viewCommByClassId);
                str日期 = txtDate.getText().toString();
                str師言 = txtTea.getText().toString();
                str家言 = txtPar.getText().toString();
                String ss = txtStuId.getText().toString();
                int學編 = Integer.parseInt(stuId);
                String cc = txtClsId.getText().toString();
                int班編 = Integer.parseInt(stuClsId);
                //若是由家長新增留言===已簽名
                is簽章 = true;


                //(String f日期, String f老師交代事項,
                //String f家長交代事項, int f學生編號, boolean f家長簽名, int fClassId)
                CCommunication data = new CCommunication(str日期,str師言,str家言, int學編, is簽章,int班編);
                dataToJson = data.toString();
                new PostCommunicationTask().execute();


                Log.d("LetNoBook_PCP_btn送出","Comm類" + dataToJson);
            }catch (Exception e){
                e.printStackTrace();
                Log.d("LetNoBook_PCP", "btnPP_crash:"+e.toString());
            }

        }
    };
    //start//新增留言
    private class PostCommunicationTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_PCP","作業中");
            pDialog = new ProgressDialog(ActivityPar_Contact_post.this);
            pDialog.setMessage("新增中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();
            String path = "http://52.246.164.133/api01/ctra/insertCommunication/?json=";

//            cp.doPost(path,dataToJson);
            String result = cp.doPost(path, dataToJson);
            Log.d("LetNoBook_PCP","上傳中");
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("新增成功"))
                Toast.makeText(ActivityPar_Contact_post.this,
                        "無法上傳,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else{
                Toast.makeText(ActivityPar_Contact_post.this,
                        "成功新增!",
                        Toast.LENGTH_LONG).show();

                //ActivityPar_Contact 需要這些參數取得相關資料, 所以要記得傳回去
                intent = new Intent(ActivityPar_Contact_post.this, ActivityPar_Contact.class);
                intent.putExtra(CDictionary.List_viewCommById, stuId);
                intent.putExtra(CDictionary.List_viewCommByName, stuName);
                intent.putExtra(CDictionary.List_viewCommByClassId, stuClsId);
                startActivity(intent);
                ActivityPar_Contact_post.this.finish();
                Log.d("LetNoBook_PCP","上傳成功 跳至 ActivityPar_Contact");
            }

            Log.d("LetNoBook_PCP", "新增/失敗: " +result);
        }
    }
    //end//新增留言
}
