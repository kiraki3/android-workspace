package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

// 유틸성 클래스
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) { // 상태값을 받아온다
        // 네트워크 상태값 받아오기
        if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            NetworkInfo info = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            NetworkInfo.DetailedState state = info.getDetailedState();
            if(state == NetworkInfo.DetailedState.CONNECTED) { // 네트워크 연결 상태
                MainActivity.tv_state.setText("Network Connection Success");
            } else if (state == NetworkInfo.DetailedState.DISCONNECTED) { // 네트워크 연결되지 않은 상태
                MainActivity.tv_state.setText("Network Connection Fail");

            }
        }
    }
}
