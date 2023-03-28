package com.example.geut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.geut.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class SectionPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_page);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();

        ArrayList<String> sections = new ArrayList<>();
        sections = bundle.getStringArrayList("section");
        recyclerViewAdapter = new RecyclerViewAdapter(SectionPage.this,sections);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}