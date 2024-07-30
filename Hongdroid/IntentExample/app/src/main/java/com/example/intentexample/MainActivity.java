package com.example.intentexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // 텍스트 적는 곳 생성
        et_test = findViewById(R.id.et_test);


        // 버튼
        btn_move = findViewById(R.id.btn_move);
        // 버튼을 누르면 activity sub 로 이동하고 싶을때
        btn_move.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 버튼을 클릭하고나면, 사용자가 작성한 텍스트를 가져온다(String 형태로)
                str = et_test.getText().toString();
                Intent intent = new Intent(MainActivity.this , SubActivity.class);
                // 첫번째 파라미터는 별명을 짓고, 두번째 파라미터는 실제로 값이 들어가는 파라미터
                intent.putExtra("str", str);
                // startActivity 메소드를 사용하여 인수를 호출할 대상을 지정하는 Intent 객체를 전달
                startActivity(intent); // 액티비티 이동
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
}