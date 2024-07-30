package com.example.broadcastreceiver;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static TextView tv_state;
    private NetworkReceiver receiver;

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
        tv_state = findViewById(R.id.tv_state);

        // BroadCastReceiver 등록 Intent 와 IntentFilter 차이점 알아두기
        IntentFilter filter = new IntentFilter();
        receiver = new NetworkReceiver(); // 객체 생성
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION); // 네트워크 상태값을 filter 에 추가  // 네트워크 상태 변경 액션 추가
        registerReceiver(receiver, filter);  // Broadcast Receiver 등록

    }

    // Broadcast Receiver 등록 해제
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver); // 종료해주지 않으면 스마트폰에 과부화가 올 수 있다.
    }
}