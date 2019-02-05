package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MainActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    TextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏顯示，隱藏窗口所有裝飾
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //標題是屬於View的，所以窗口所有的修飾部分被隱藏後標題依然有效，需要去掉標題
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //繼承AppCompatActivity要用↓達成全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        circleMenu = findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#FFFFFF"), R.drawable.mainbutton, R.drawable.mainpressedbutton)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.icon_login)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.icon_info)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.icon_news)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.icon_contacts)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.icon_setting)

                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int i) {
                        Intent intent;
                        Uri uri;
                        switch (i){
                            case 0:
                                Toast.makeText(MainActivity.this, "頁面轉至登入頁", Toast.LENGTH_SHORT).show();
                                intent = new Intent(MainActivity.this, ActivityLogin.class);
                                startActivity(intent);
                                MainActivity.this.onResume();
                                break;
                            case 1:
//                                Toast.makeText(MainActivity.this, "簡介", Toast.LENGTH_SHORT).show();
//                                intent = new Intent(MainActivity.this, InfoAct.class);
                                uri = Uri.parse("http://fuxiao.ps.nutn.edu.tw/?page_id=17");
                                intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                MainActivity.this.onResume();
                                break;
                            case 2:
//                                Toast.makeText(MainActivity.this, "頁面轉至校園公告", Toast.LENGTH_SHORT).show();
//                                intent = new Intent(MainActivity.this, ActNews.class);
                                uri = Uri.parse("http://fuxiao.ps.nutn.edu.tw/");
                                intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                MainActivity.this.onResume();
                                break;
                            case 3:
//                                Toast.makeText(MainActivity.this, "頁面轉至聯絡資訊", Toast.LENGTH_SHORT).show();
//                                intent = new Intent(MainActivity.this, ActContacts.class);
                                uri = Uri.parse("http://fuxiao.ps.nutn.edu.tw/?page_id=21");
                                intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                MainActivity.this.onResume();
                                break;
                            case 4:
                                Toast.makeText(MainActivity.this, "頁面轉至設定", Toast.LENGTH_SHORT).show();
                                intent = new Intent(MainActivity.this, ActivitySetting.class);
                                startActivity(intent);
                                MainActivity.this.onResume();
                                break;
                        }

                    }


                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {}

        });

    }

}