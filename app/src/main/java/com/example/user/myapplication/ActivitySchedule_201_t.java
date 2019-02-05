package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivitySchedule_201_t extends AppCompatActivity {
    Date today = new Date();
    TextView txtDate;
    LinearLayout Monday, Tuesday, Wednesday, Thursday, Friday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_201_t);

        Monday = findViewById(R.id.Monday);
        Tuesday = findViewById(R.id.Tuesday);
        Wednesday = findViewById(R.id.Wednesday);
        Thursday = findViewById(R.id.Thursday);
        Friday = findViewById(R.id.Friday);
        txtDate = findViewById(R.id.txtDate);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 E", Locale.TAIWAN);
        txtDate.setText(simpleDateFormat.format(today));

        CheckWeekdays();
    }
    @SuppressLint("ResourceAsColor")
    public void CheckWeekdays() {
        String w = today.toString().substring(0, 3);
        switch (w){
            case "Mon":
                Monday.setBackgroundColor(Color.rgb(235,214,214));
                break;
            case "Tue":
                Tuesday.setBackgroundColor(Color.rgb(235,214,214));
                break;
            case "Wed":
                Wednesday.setBackgroundColor(Color.rgb(235,214,214));
                break;
            case "Thu":
                Thursday.setBackgroundColor(Color.rgb(235,214,214));
                break;
            case "Fri":
                Friday.setBackgroundColor(Color.rgb(235,214,214));
                break;
            default:
                break;
        }
        Log.d("LetNoBook", "今天日期" + today + "星期" + w);
    }
}
