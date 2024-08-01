package com.example.lifecycleexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // Activity 또는 Fragment 가 생성이 되었을 때 이 곳을 먼저 실행
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.e("onCreate", "ENTER");

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // main > sub 로 화면 이동
                startActivity(new Intent(MainActivity.this, SubActivity.class));
            }
        });

    }

    // 중지 되어있던 Activity 가 재개 되는 시점에서 이곳 내부 구문들을 실행
    // 실제로 사용자에게 화면이 보여지고 있을 때 실행됨
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "ENTER");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart", "ENTER");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart", "ENTER");

    }

    // 중지 상태일 때 내부 구문들을 실행
    // 1. 홈 버튼을 눌러서 바깥으로 잠깐 빠져나갔을 때
    // 2. 다른 액티비티가 활성화 되어있을 때
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause", "ENTER");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop", "ENTER");

    }

    // 화면이 파괴되어서 소멸했을 때 실행
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "ENTER");

    }
}