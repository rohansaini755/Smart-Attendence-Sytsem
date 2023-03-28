package com.example.geut;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrGen extends AppCompatActivity {

//    EditText etinput;
//    Button bt_generate;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_gen);
//        etinput = findViewById(R.id.et_input);
//        bt_generate = findViewById(R.id.bt_generate);
        iv = findViewById(R.id.iv);

        Intent intent = getIntent();
        String pass = intent.getStringExtra("SectionPassword");

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(pass, BarcodeFormat.QR_CODE
                    ,350,350);
            BarcodeEncoder en = new BarcodeEncoder();

            Bitmap bitmap = en.createBitmap(matrix);

            iv.setImageBitmap(bitmap);

            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );

//                    manager.hideSoftInputFromWindow(etinput.getApplicationWindowToken(),0);
        } catch (WriterException e) {
            e.printStackTrace();
        }

//        bt_generate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(QrGen.this, "done", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}