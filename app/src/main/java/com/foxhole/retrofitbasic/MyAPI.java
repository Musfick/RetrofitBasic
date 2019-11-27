package com.foxhole.retrofitbasic;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPI {

    //GET METHODS
    @GET("posts")
    Call<List<Post>> getAllPost(@Query("userId") int userId,
                                @Query("_sort") String sort,
                                @Query("_order") String order);

    @GET("posts/{UID}")
    Call<Post> getSinglePost(@Path("UID") int uid);

    //POST METHODS
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //POST METHODS USING FromUrlEncoded
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userID,
            @Field("title") String title,
            @Field("body") String body
    );

    //POST METHODS USING FromUrlEncoded
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @FieldMap Map<String, String> map
    );

//    //PUT METHOD
//    @PUT("posts/{id}")
//    Call<Post> putPost(@Path("id") int id, @Body Post post);

    //PUT METHOD
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
