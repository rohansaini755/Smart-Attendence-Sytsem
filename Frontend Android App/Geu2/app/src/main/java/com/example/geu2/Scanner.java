package com.example.geu2;

import java.io.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.geu2.Pmodel.PostModel;
import com.example.geu2.network.ApiInterface;
import com.example.geu2.network.RetrofitClient;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Scanner extends AppCompatActivity {

    private CodeScanner codeScanner;
    String section;
    int id;
    private TextView codeData;
    private Button button2;
    private CodeScannerView scannerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Intent intent = getIntent();
        ArrayList<String> arr;
        arr = intent.getStringArrayListExtra("student_info");
//        section = intent.getStringExtra("Section_name");
        section = arr.get(0);
        id = Integer.parseInt(arr.get(1));
        Toast.makeText(this, section + " " + Integer.toString(id), Toast.LENGTH_SHORT).show();

        codeData = findViewById(R.id.text_code);
        scannerView = findViewById(R.id.scanner_view);
        button2 = findViewById(R.id.button2);
        int PERMISSION_ALL = 1;
        String [] PERMISSIOS = {
                Manifest.permission.CAMERA
        };

        if(!hasPermissions(this,PERMISSIOS))
            ActivityCompat.requestPermissions(this,PERMISSIOS,PERMISSION_ALL);
        else
            runCodeScanner();


        progressDialog = new ProgressDialog(this);
    }

    public void runCodeScanner()
    {
        codeScanner = new CodeScanner(this,scannerView);

        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setScanMode(ScanMode.CONTINUOUS);
        codeScanner.setFlashEnabled(false);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data =result.getText().toString();
                        String [] arr = data.split("/");
                        String password = arr[0];
                        String col_name = arr[1];
//                        Toast.makeText(Scanner.this, section + " " + password, Toast.LENGTH_SHORT).show();
                        PostModel postModel = new PostModel(section,password);
                        Call<PostModel> call =RetrofitClient.getInstance().getMyApi().checkPassword(postModel);
                        call.enqueue(new Callback<PostModel>() {
                         @Override
                         public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                               PostModel mydata = response.body();
                               if(mydata.getStatus() == 1)
                                   makeAttendence(section,col_name);
                               else
                               Toast.makeText(Scanner.this,"proxy", Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void onFailure(Call<PostModel> call, Throwable t) {
                              Toast.makeText(Scanner.this, "Something went wrong in scanner", Toast.LENGTH_SHORT).show();
                         }
                         });
                    }
                });
            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Throwable thrown) {
                Toast.makeText(Scanner.this, "camera not responding", Toast.LENGTH_SHORT).show();
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });

    }


    public static boolean hasPermissions(Context context, String... permissions)
    {
        if(context!= null && permissions != null)
        {
            for(String permission : permissions)
            {
                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeAttendence(String section, String col_name)
    {
        PostModel postModel = new PostModel(section,col_name,id);
        Call<PostModel> call = RetrofitClient.getInstance().getMyApi().makeattendence(postModel);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                Toast.makeText(Scanner.this,"marked present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(Scanner.this, "please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}