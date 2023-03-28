package com.example.geu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.geu2.Pmodel.PostModel;
import com.example.geu2.network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class login_page extends AppCompatActivity {

    private EditText universityId, pass;
    private Button button;
    private SharedPreferences sp;
    String id = "";
    String _password = "";
    public static int URollNo = 0;
    public static int UId = 0;
    public static String Section = "";
    public static String name = "";
    public static int CRollNo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        universityId = findViewById(R.id.universityId);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        sp = getSharedPreferences("homePreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = universityId.getText().toString();
                _password = pass.getText().toString();

                if (id.equals(""))
                    Toast.makeText(login_page.this, "please enter university Id", Toast.LENGTH_SHORT).show();
                else if (_password.equals(""))
                    Toast.makeText(login_page.this, "please enter password", Toast.LENGTH_SHORT).show();
                else {
//                    Toast.makeText(login_page.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                    jsonParse(editor);
                    check(id,_password,editor);
                    onBackPressed();
                }
            }
        });
    }

    private void check(String id , String password,SharedPreferences.Editor editor)
    {
        PostModel  postModel = new PostModel(Integer.parseInt(id),Integer.parseInt(password));
        Call<PostModel> call = RetrofitClient.getInstance().getMyApi().postData(postModel);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, retrofit2.Response<PostModel> response) {
                Toast.makeText(login_page.this, Integer.toString(response.body().getCRollNo()), Toast.LENGTH_SHORT).show();
                if(response.body().getStatus() == 2)
                {
                    URollNo = response.body().getURollNo();
                    UId = response.body().getUId();
                    Section = response.body().getSection();
                    name = response.body().getName();
                    CRollNo = response.body().getCRollNo();
                    editor.putInt("URollNo", URollNo);
                    editor.putInt("UId", UId);
                    editor.putString("Section", Section);
                    editor.putString("name", name);
                    editor.putInt("CRollNo", CRollNo);
                    editor.apply();
                    Toast.makeText(login_page.this, "Successfully login", Toast.LENGTH_SHORT).show();
                }
                else if(response.body().getStatus() == 1)
                    Toast.makeText(login_page.this, "invalid password", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(login_page.this, "invalid id", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(login_page.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void jsonParse(SharedPreferences.Editor editor){
        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);

        String url = "https://api.npoint.io/0f41361bb568f55ba63f";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            if(status == 2){
//                                Toast.makeText(login_page.this, "Login successfully",Toast.LENGTH_SHORT).show();
                                URollNo = response.getInt("URollNo");
                                UId = response.getInt("UId");
                                Section = response.getString("Section");
                                name = response.getString("name");
                                CRollNo = response.getInt("CRollNo");
                                editor.putInt("URollNo", URollNo);
                                editor.putInt("UId", UId);
                                editor.putString("Section", Section);
                                editor.putString("name", name);
                                editor.putInt("CRollNo", CRollNo);
                                editor.apply();
//                                Toast.makeText(login_page.this, sp.getString("name",""), Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(login.this,MainActivity.class);
//                                startActivities(new Intent[]{intent});
                            }
                            else if(status == 1)
                                Toast.makeText(login_page.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            else if(status == 0)
                                Toast.makeText(login_page.this, "Incorrect Id", Toast.LENGTH_SHORT).show();
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

        mQueue.add(request);
    }
}