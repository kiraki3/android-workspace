package com.example.comebackexample;

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

public class SubActivity extends AppCompatActivity {

    private EditText edit_comeback;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 아이디 연결
        edit_comeback = findViewById(R.id.edit_comeback);
        btn_close = findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("comeback", edit_comeback.getText().toString()); // 입력한 텍스트를 삽입 (id값지정, 입력한 텍스트를 String 형태로 담는다)
                setResult(RESULT_OK, intent); // 결과 값 설정  RESULT_OK는 안드로이드에서 기본 제공하는 상수값
                                              // main 에서 받은 요청 잘 받았다고 보내주는 예의? 같은 개념
                finish(); // 현재 액티비티 종료
            }
        });
    }
}