package com.example.checkboxexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox chk_red, chk_blue, chk_green;
    private TextView tv_result;
    private Button btn_color;

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

        // id 연결
        chk_red = findViewById(R.id.chk_red);
        chk_blue = findViewById(R.id.chk_blue);
        chk_green = findViewById(R.id.chk_green);
        tv_result = findViewById(R.id.tv_result);
        btn_color = findViewById(R.id.btn_color);

        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_result = " "; // 공백의 의미, 클릭할때마다 String 값 초기화
                if(chk_red.isChecked()) { // 체크박스에 체크가 되어있다면 실행
                    str_result += chk_red.getText().toString();
                } if(chk_blue.isChecked()) {
                    str_result += chk_blue.getText().toString();
                } if(chk_green.isChecked()) {
                    str_result += chk_green.getText().toString();
                }
                tv_result.setText(str_result); // 체크박스에 체크되어있던 값을 String 으로 텍스트뷰에 출력
            }
        });
    }
}