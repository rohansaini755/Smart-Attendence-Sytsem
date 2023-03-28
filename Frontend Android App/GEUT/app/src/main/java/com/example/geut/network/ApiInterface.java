package com.example.geut.network;

import com.example.geut.Pmodel.PostModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    String BASE_URL = "https://6b3f-2409-4053-2d8c-6ad7-9d7f-e2b5-5100-65b9.in.ngrok.io/";

    @GET("tt/check")
    Call<PostModel> getchecking();

    @POST("tt/checkteacherlogin")
    Call<PostModel> getresponse(@Body PostModel postModel);

    @POST("tt/allsection")
    Call<List<PostModel>> getsection(@Body PostModel postmodel1);

    @POST("tt/generateqr")
    Call<PostModel> getsectionpassword(@Body PostModel postModel);
}
