package com.example.geut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class qugenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qugenerator);

        Intent intent = getIntent();
        String pass = intent.getStringExtra("SectionPassword");
        Toast.makeText(this, pass, Toast.LENGTH_SHORT).show();
//        ArrayList<String> arr = intent.getStringArrayListExtra("TeacherData")
    }
}