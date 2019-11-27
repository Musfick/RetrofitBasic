package com.foxhole.retrofitbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_TAG";

    public TextView mResultText;
    private MyAPI myAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultText = findViewById(R.id.tv_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        //Creating new Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myAPI = retrofit.create(MyAPI.class);

        //getAllPost();
        //getSinglePost();
        //createPost();
        putPost();


    }

    private void putPost() {

        Post post = new Post(12,null,"New Text");
        Call<Post> call = myAPI.patchPost(1,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post post = response.body();

                    String content = "";
                    content += "Code: "+response.code()+"\n";
                    content += "ID: "+post.getUserId()+"\n";
                    content += "UID: "+post.getUID()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getText()+"\n";

                    mResultText.append(content);
                }

                else {
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void createPost() {

        Post post = new Post(2,"New Title","New Text");

        Map<String, String> map = new HashMap<>();
        map.put("userId", "23");
        map.put("title" , "New Title");
        map.put("body", "New Body");

        Call<Post> call = myAPI.createPost(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post post = response.body();

                    String content = "";
                    content += "Code: "+response.code()+"\n";
                    content += "ID: "+post.getUserId()+"\n";
                    content += "UID: "+post.getUID()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getText()+"\n";

                    mResultText.append(content);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void getSinglePost() {
        Call<Post> call = myAPI.getSinglePost(3);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful())
                {
                    Post post = response.body();

                    String content = "";
                    content += "ID: "+post.getUserId()+"\n";
                    content += "UID: "+post.getUID()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getText()+"\n";

                    mResultText.append(content);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void getAllPost() {

        //Get API Response
        Call<List<Post>> call = myAPI.getAllPost(1,"id","asc");

        //Perform API Call in Background Thread
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //Check response is successful or not
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.code());
                }

                //If response is successful
                List<Post> postList = response.body();
                for (Post post: postList)
                {
                    String content = "";
                    content += "ID: "+post.getUserId()+"\n";
                    content += "UID: "+post.getUID()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getText()+"\n";

                    mResultText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
