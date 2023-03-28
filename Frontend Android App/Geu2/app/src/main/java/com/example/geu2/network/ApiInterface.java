package com.example.geu2.network;

import com.example.geu2.Pmodel.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    String BASE_URL = "https://6e4c-2405-201-680b-3b64-cdb2-b5fe-da86-76b4.in.ngrok.io/";

    @GET("tt/check")
    Call<PostModel> getsuperHeroes();



    @POST("/tt/checkstudentlogin")
    Call<PostModel> postData(@Body PostModel postModel);

    @POST("/tt/checkPassword")
    Call<PostModel> checkPassword(@Body PostModel postModel);

    @POST("/tt/makeattendence")
    Call<PostModel> makeattendence(@Body PostModel postModel);
//    @POST("/tt/checkstudentlogin")
//    Call<PostModel>  postData(@Body PostModel postModel);


}
