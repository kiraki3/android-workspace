package com.example.comebackexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_comback;
    private Button btn_go;

    // startActivityForResult(intent); 안에 넣어줘여하는 상수. 보통 상수는 대문자
    private static final int REQUEST_CODE = 777;   // 상수 값 선언(변하지 않는 수)

    // 가장 먼저 보게되는 생명 주기
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

        // 아이디 연결
        tv_comback = findViewById(R.id.tv_comeback);
        btn_go = findViewById(R.id.btn_go);

        // 버튼 클릭
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class); // 이동할 액티비티
                startActivityForResult(intent, REQUEST_CODE); // 실제로 요청
            }
        });



    }

    // SubActivity > setResult(RESULT_OK, intent); 받아야지
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if (data != null) {
                // 데이터가 정상적으로 수신된 경우
                Toast.makeText(getApplicationContext(), "수신 성공", Toast.LENGTH_SHORT).show();
            } else {
                // 데이터가 null 인 경우, 수신 실패로 처리
                Toast.makeText(getApplicationContext(), "수신 실패", Toast.LENGTH_SHORT).show();
            }
        } else {
            //resultCode 가 RESULT_CANCELED 또는 다른 값인 경우
            Toast.makeText(getApplicationContext(), "수신 실패", Toast.LENGTH_SHORT).show();
        }

        // GO 라는 버튼을 눌렀을 때, REQUEST_CODE 를 담아서 SubActivity에 전달했는데, 그게 다시 잘 돌아왔는지 확인
        if (requestCode == REQUEST_CODE) {
            String resultTxt = data.getStringExtra("comeback"); //intent.putExtra("comeback", edit_comeback.getText().toString()); // 입력한 텍스트를 삽입 (id값지정, 입력한 텍스트를 String 형태로 담는다)
                                                                      // String 값을 보낸걸 받아오는걸 해야한다.
            tv_comback.setText(resultTxt);


        }
    }
}