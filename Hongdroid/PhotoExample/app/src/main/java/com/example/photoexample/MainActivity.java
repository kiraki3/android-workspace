package com.example.photoexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et_test;
    Button btn_test;
    ImageView test;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_test = (EditText)findViewById(R.id.et_test);

        btn_test = (Button)findViewById(R.id.btn_test);
        btn_test.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        });

        // test 변수에 ImageView 연결
        test = (ImageView)findViewById(R.id.test);
        // test이미지를 클릭하면,
        test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // (본인의 MainActivity, 팝업되는 텍스트, Toast가 나타나는 길이).show()'
                Toast.makeText(getApplicationContext(), "맞지요", Toast.LENGTH_LONG).show();
            }
        });


    }
}