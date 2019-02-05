package com.example.user.myapplication;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.*;

public interface IDiaryService {
    //Retrofit turns our institute WEB API into a Java interface.
    //So these are the list available in our WEB API and the methods look straight forward
    //i.e. http://ipPath/api/institute/tDiaries

    @GET("tDiaries")
    public void getDiary(Callback<List<CtDiary>> callback);

    //i.e. http://localhost/api/institute/tDiaries/1
    //Get student record base on ID
    @GET("tDiaries/{id}")
    public void getDiaryById(@Path("id") Integer id,Callback<CtDiary> callback);

    //i.e. http://localhost/api/institute/tDiaries/1
    //Delete student record base on ID
    @DELETE("tDiaries/{id}")
    public void deleteDiaryById(@Path("id") Integer id,Callback<CtDiary> callback);

    //i.e. http://localhost/api/institute/tDiaries/1
    //PUT student record and post content in HTTP request BODY
    @PUT("tDiaries/{id}")
    public void updateDiaryById(@Path("id") Integer id,@Body CtDiary student,Callback<CtDiary> callback);

    //i.e. http://localhost/api/institute/tDiaries
    //Add student record and post content in HTTP request BODY
    @POST("tDiaries")
    public void addDiary(@Body CtDiary student,Callback<CtDiary> callback);
}
