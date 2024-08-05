package com.example.registerforactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_result;

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

        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                getSubActivityResult.launch(intent); // startActivityForResult 와 동일한 기능 수행
            }
        });
    }

    // 새로운 Activity를 시작하고 결과를 비동기적으로 처리할 수 있는 객체
    private final ActivityResultLauncher<Intent> getSubActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // 메인에서 서브엑티비티로 돌아올 때 결과 처리 : 서브Activity에서 결과를 받아오는 기능을 구현
                if (result.getResultCode() == RESULT_OK) {
                    // 서브 액티비티의 입력 값을 메인에서 받아서 텍스트뷰에 표시
                    tv_result.setText(result.getData().getStringExtra("value"));
                }
            }
    );

}