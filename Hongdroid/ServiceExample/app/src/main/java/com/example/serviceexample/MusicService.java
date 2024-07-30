package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.security.Provider;

public class MusicService extends Service {

    // Player Create
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // service 초기화
    @Override
    public void onCreate() {
        super.onCreate();
        // raw 라는 폴더에 음원 넣고, 이름은 대문자, 공백이 있으면 안됨
        mediaPlayer = MediaPlayer.create(this, R.raw.ontherocks);
        // 반복재생 > true 한번재생 > false
        mediaPlayer.setLooping(false);
    }

    //service 시작
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    // service 종료
    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }
}
