package com.example.edittext;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_id;
    Button btn_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
//        activity_main 파일의 id 값을 연결을 하여 왼쪽으로 숨을 불어넣어줌
        btn_text = findViewById(R.id.btn_text);
//        activity_main 파일의 button을 연결하여 왼쪽으로 숨을 불어넣어줌

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_id.setText("아키");
                /* 클릭 이벤트를 동작 시키면, 동적으로 id 값을 넣어준다 */
            }
        });

    }


}


