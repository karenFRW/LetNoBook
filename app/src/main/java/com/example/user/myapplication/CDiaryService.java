package com.example.user.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CDiaryService {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "http://13.67.105.225/api/institute/tDiaries";
    private IDiaryService diaryService;

    public CDiaryService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        diaryService = retrofit.create(IDiaryService.class);
    }

    public IDiaryService getService(){
        return diaryService;
    }
}
