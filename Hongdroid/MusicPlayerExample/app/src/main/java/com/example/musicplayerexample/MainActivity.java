package com.example.musicplayerexample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 버튼 생성
    private Button btn_play, btn_stop;
    // mediaPlayer
    MediaPlayer mediaPlayer;

    // activity 가 종료 될 때 이곳을 실행함
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.release(); // 만약에 실행 중이라면 다시 재개해서 멈춰줘야 한다.
            mediaPlayer = null;
        }
    }

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

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        // 재생 버튼 눌렀을 때
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 초기화
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.trrrr);
                // 재생
                mediaPlayer.start();
            }
        });

        // 정지 버튼 눌렀을 때
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {  // mediaplayer 가 실행중이 아니라면, nullobject 블라블라가 나와서 중지되기 때문에 isPlaying 이라는 메서드를 사용한다
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }

            }
        });



    }
}