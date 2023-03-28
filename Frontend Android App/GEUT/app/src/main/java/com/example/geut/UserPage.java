package com.example.geut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geut.Pmodel.PostModel;
import com.example.geut.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPage extends AppCompatActivity {
    TextView name,id;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        button = findViewById(R.id.button);

        Intent intent = getIntent();
        ArrayList<String>  arr = intent.getStringArrayListExtra("TeacherData");
//        Toast.makeText(this, arr.get(0), Toast.LENGTH_SHORT).show();
        name.setText(arr.get(0));
        id.setText(arr.get(1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsection(20021427);
            }
        });



    }

//    public void getsection(int id)
//    {
//        PostModel postmodel1 = new PostModel(id);
//        Call<List<PostModel>> call = RetrofitClient.getInstance().getMyApi().getsection(postmodel1);
//        call.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, retrofit2.Response<PostModel> response) {
//                PostModel data = response.body();
////                String s = data.getSection();
//                Toast.makeText(UserPage.this, "successfull", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//                Toast.makeText(UserPage.this, "Something Wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public void getsection(int id)
    {
        PostModel postModel = new PostModel(id);
        Call<List<PostModel>> call = RetrofitClient.getInstance().getMyApi().getsection(postModel);
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful())
                {
                    List<PostModel> data = response.body();
                    ArrayList<String> d = new ArrayList<>();
                    for(int i = 0 ; i < data.size();i++)
                    {
                        d.add(data.get(i).getSection());
                    }
                    Toast.makeText(UserPage.this, "successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserPage.this,SectionPage.class);
                    intent.putStringArrayListExtra("section",d);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(UserPage.this, "Server Problem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(UserPage.this, "No Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}