package com.example.geut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.geut.Pmodel.PostModel;
import com.example.geut.network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private EditText id,password;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.id);
        password =  findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Please enter id", Toast.LENGTH_SHORT).show();
                else if(password.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                else
                {
//                    jsonParse();
                    check1(id.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void check1(String id,String password)
    {
        PostModel postModel = new PostModel(Integer.parseInt(id),password);
        Call<PostModel> call = RetrofitClient.getInstance().getMyApi().getresponse(postModel);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, retrofit2.Response<PostModel> response) {
                PostModel mydata = response.body();
                int s = mydata.getStatus();
                String name = mydata.getName();
                int id = mydata.getTechId();
                if(s  == 2)
                {
//                    Intent intent = new Intent(MainActivity.this,UserPage.class);
//                    startActivity(intent);

                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(name);
                    arr.add(Integer.toString(id));
                    Intent intent = new Intent(getApplicationContext(),UserPage.class);
                    intent.putStringArrayListExtra("TeacherData",arr);
                    startActivities(new Intent[]{intent});
                }
                else
                Toast.makeText(MainActivity.this, Integer.toString(mydata.getStatus()) + " Invalid username or password", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrongjbsjdb", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void check()
    {
//        Toast.makeText(this, "entered", Toast.LENGTH_SHORT).show();
        RequestQueue  mqueue;
        mqueue = Volley.newRequestQueue(this);
        String url = "https://c182-2405-201-680b-3cc8-157a-866d-7f1d-9293.in.ngrok.io/tt/check";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String ans = response.getString("data");
                            Toast.makeText(MainActivity.this, ans, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Something went wrong1", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong2", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mqueue.add(request);
    }
    private void jsonParse()
    {
        RequestQueue mqueue;
        mqueue = Volley.newRequestQueue(this);
        String url = "https://api.npoint.io/563a250c1b703576af25";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            if(status == 2)
                            {
                                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                ArrayList<String> arr = new ArrayList<>();
                                arr.add(response.getString("Name"));
                                arr.add(Integer.toString(response.getInt("TID")));
                                Intent intent = new Intent(getApplicationContext(),UserPage.class);
                                intent.putStringArrayListExtra("TeacherData",arr);
                                startActivities(new Intent[]{intent});
                            }
                            else if(status == 1)
                                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            else if(status == 0)
                                Toast.makeText(MainActivity.this, "Incorrect Id", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mqueue.add(request);
    }
}