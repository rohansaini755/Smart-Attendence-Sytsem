package com.example.geu2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int UniversityRollNo;
    public static int UniversityId;
    public static String Section;
    public static String name;
    public static int ClassRollNo;
    public static TextView Name;
    public static TextView URN;
    public static TextView UID;
    public static TextView section;
    public static TextView CRN;
    public ImageButton login;
    public Button present;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "back to home page", Toast.LENGTH_SHORT).show();

        Name = findViewById(R.id.Name);
        URN = findViewById(R.id.URN);
        UID = findViewById(R.id.UID);
        section = findViewById(R.id.section);
        CRN = findViewById(R.id.CRN);
        login = findViewById(R.id.loginB);
        present = findViewById(R.id.present);
//        refreshLayout = findViewById(R.id.swiperefresh);
        sp = getApplication().getSharedPreferences("homePreferences",MODE_PRIVATE);

        UniversityRollNo = sp.getInt("URollNo",0);
        UniversityId = sp.getInt("UId",0);
        Section = sp.getString("Section","");
        name = sp.getString("name","");
        ClassRollNo = sp.getInt("CRollNo",0);

        Toast.makeText(this, Integer.toString(ClassRollNo), Toast.LENGTH_SHORT).show();
        Name.setText(name);
        URN.setText(Integer.toString(UniversityRollNo));
        UID.setText(Integer.toString(UniversityId));
        section.setText(Section);
        CRN.setText(Integer.toString(ClassRollNo));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "opening login page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,login_page.class);

                startActivity(intent);
//                UniversityRollNo = sp.getInt("URollNo",0);
//                UniversityId = sp.getInt("UId",0);
//                Section = sp.getString("Section","");
//                name = sp.getString("name","");
//                ClassRollNo = sp.getInt("CRollNo",0);
//                Name.setText(name);
//                URN.setText(Integer.toString(UniversityRollNo));
//                UID.setText(Integer.toString(UniversityId));
//                section.setText(Section);
//                CRN.setText(Integer.toString(ClassRollNo));
//                Toast.makeText(MainActivity.this, "back to home page", Toast.LENGTH_SHORT).show();
            }
        });


        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = Integer.toString(UniversityRollNo);
                String section = Section;
                ArrayList<String> arr = new ArrayList<>();
                arr.add(section);
                arr.add(id);
                Intent intent2 = new Intent(MainActivity.this,Scanner.class);
                intent2.putStringArrayListExtra("student_info",arr);
//                intent2.putExtra("Section_name",section);
                startActivity(intent2);
            }
        });
    }

    protected void setData(SharedPreferences sp)
    {
        UniversityRollNo = sp.getInt("URollNo",0);
        UniversityId = sp.getInt("UId",0);
        Section = sp.getString("Section","");
        name = sp.getString("name","");
        ClassRollNo = sp.getInt("CRollNo",0);
    }

}