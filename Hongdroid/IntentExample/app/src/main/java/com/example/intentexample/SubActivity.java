package com.example.intentexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    private TextView tv_text;

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

        // subxml 에서 선언한 id값과 연결
        tv_text = findViewById(R.id.tv_text);

        // MainActivity 에서 문자를 입력하고 이동을 누르게 되면,
        Intent intent = getIntent();
        // 입력한 문자를 String 형태로 가져옴
        //intent.putExtra("str", str); 메인에서 설정한 별명과 동일해야 한다.
        String str = intent.getStringExtra("str");
        // 입력 받은 문자를 출력
        tv_text.setText(str);




    }
}