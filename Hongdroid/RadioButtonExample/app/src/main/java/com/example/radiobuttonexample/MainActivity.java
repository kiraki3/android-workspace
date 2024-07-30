package com.example.radiobuttonexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rd_gender;
    private RadioButton rd_woman, rd_man;
    private Button btn_result;
    // result 값을 받아올 변수
    private String str_result;


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

        // 연결
        rd_gender = findViewById(R.id.rd_gender);
        rd_woman = findViewById(R.id.rd_woman);
        rd_man = findViewById(R.id.rd_man);

        btn_result = findViewById(R.id.btn_result);

        // 라디오 버튼들의 상태 값의 변경됨을 감지
        rd_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) { // 결과는 int i로 받는다
                if(i == R.id.rd_woman) {
                    Toast.makeText(MainActivity.this, "여자를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    str_result = rd_woman.getText().toString();
                } else if(i == R.id.rd_man) {
                    Toast.makeText(MainActivity.this, "남자를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    str_result = rd_man.getText().toString();
                }
            }
        });

        //  결과 버튼을 눌렀을때
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str_result != null) {
                    Toast.makeText(MainActivity.this, str_result, Toast.LENGTH_SHORT).show();
                } else if (str_result == null) {
                    Toast.makeText(MainActivity.this, "둘 중 하나를 선택해 주세요", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}