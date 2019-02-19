package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTea_Diary_edit extends AppCompatActivity {
    private Intent intent;
    private String 日誌id,學生id,日期,日誌,師評;
    private TextView txtTop, txtDate, txtDId, txtSId;
    private EditText txtDiary, txtReply;
    private Button btnPP, btnCxl;
    private ProgressDialog pDialog;
    private String str日誌編號, str日誌, str批改, str日期, str學編, 學名, dataToJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        InitialComponent();

    }

    private void InitialComponent() {
        intent= getIntent();
        日誌id = intent.getStringExtra(CDictionary.List_viewDiaryId);
        學生id = intent.getStringExtra(CDictionary.List_viewDiaryById);
        日期 = intent.getStringExtra(CDictionary.List_viewDiaryDate);
        日誌 = intent.getStringExtra(CDictionary.List_viewDiaryStu);
        師評 = intent.getStringExtra(CDictionary.List_viewDiaryTea);
        學名 = intent.getStringExtra(CDictionary.List_viewDiaryByName);

        txtTop = findViewById(R.id.txtTop);
        txtTop.setText("正在回覆 " + 學名);
        txtDate = findViewById(R.id.txtDate);
        txtDate.setText(日期);
        txtSId = findViewById(R.id.txtStuId);
        txtSId.setText(學生id);
        txtDId = findViewById(R.id.txtDiaryId);
        txtDId.setText(日誌id);
        txtDiary = findViewById(R.id.txtDiary);
        txtDiary.getBackground().setAlpha(70);
        txtDiary.setText(日誌);
        txtDiary.setEnabled(true);
        txtReply = findViewById(R.id.txtReply);
        txtReply.getBackground().setAlpha(70);
        txtReply.setText(師評);
        btnPP = findViewById(R.id.btnPP);
        btnPP.setOnClickListener(btnPP_Click);
        btnCxl = findViewById(R.id.btnCxl);
        btnCxl.setOnClickListener(btnCxl_Click);


    }

    private View.OnClickListener btnPP_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str日誌編號 = txtDId.getText().toString();
            int d = Integer.parseInt(str日誌編號);
            str日誌 = txtDiary.getText().toString();
            str批改 = txtReply.getText().toString();
            str日期 = txtDate.getText().toString();
            str學編 = txtSId.getText().toString();
            if(!txtReply.getText().equals("") && !txtReply.getText().equals(null))
            {
                //(String f學生日誌文字, String f日誌批改, String f日期, int f學生編號)
                CDiary data = new CDiary(d,str日誌,null,str批改,str日期,Integer.parseInt(str學編));
                dataToJson = data.toString();
                new PutDiaryTask().execute();
            }else
                Toast.makeText(ActivityTea_Diary_edit.this,"老師, 內容空白喔^^",Toast.LENGTH_LONG);
            Log.d("LetNoBook_btn送出","diary類" + dataToJson);
        }
    };
    //start//新增日誌
    private class PutDiaryTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LetNoBook_DiaryTask","作業中");
            pDialog = new ProgressDialog(ActivityTea_Diary_edit.this);
            pDialog.setMessage("作業中, 請稍後...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            CHttpPost cp = new CHttpPost();

            String path = "http://13.67.105.225/api05/ctrA/DTUpdate/?id=" + 日誌id+
                    "&content=";
//            cp.doPost(path,dataToJson);
            String result = cp.doPost(path, str批改);
            Log.d("LetNoBook_DiaryTask","上傳中");
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(!result.equals("Cannot find table 0."))
                Toast.makeText(ActivityTea_Diary_edit.this,
                        "無法更新,請稍後再試喔!",
                        Toast.LENGTH_LONG).show();
            else{
                Toast.makeText(ActivityTea_Diary_edit.this,
                        "成功更新!",
                        Toast.LENGTH_LONG).show();
                intent = new Intent(ActivityTea_Diary_edit.this,ActivityTea.class);
                startActivity(intent);
            }


            Log.d("LetNoBook_DiaryTask", "新增:" +result);
        }
    }
    //end//新增日誌

    private View.OnClickListener btnCxl_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ActivityTea_Diary_edit.this,ActivityTea.class);
            startActivity(intent);
        }
    };
}
