package com.example.threadexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_stop;
    Thread thread;
    boolean isThread = false;

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

        // Thread 는 백그라운드에서 작업 가능하다
        // home 버튼이나 뒤로가기 버튼을 눌렀을때, Background 상태로 들어가게 되는데, 처리해 줄 수 있는 구문이나 일련의 프로세스 작업을 할 수 있다.

        // 스레드 시작 클릭 이벤트 구현
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isThread = true;
                thread = new Thread() {
                    public void run() {
                        while(isThread) {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            handler.sendEmptyMessage(0);  // try 문에서 5초 정도 쉬었다가 handler가 실행
                        }
                    }
                };
                // thread 시작
                thread.start();
            }
        });
        // isThread 가 false 가 되기 전까지는 계속 실행될거다. 종료 버튼을 누르면 끝이 난다
        // 스레드 종료 클릭 이벤트 구현
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = false;
            }
        });

    }

    // handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(), "홍드로이드 강의", Toast.LENGTH_SHORT).show();
        }
    };
}