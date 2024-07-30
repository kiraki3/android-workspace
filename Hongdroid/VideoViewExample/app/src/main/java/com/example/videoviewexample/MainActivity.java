package com.example.videoviewexample;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;  // 비디오를 실행할 수 있게 도와주는 뷰
    private MediaController mediaController;  // 재생이나 정지와 같은 미디어 제어 버튼 뷰를 담당
    private String VideoURL= "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

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

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        Uri uri = Uri.parse(VideoURL);
        videoView.setMediaController(mediaController); // 미디어 제어 버튼 셋팅
        videoView.setVideoURI(uri); // 비디오 뷰의 주소를 설정
        videoView.start(); // 비디오 실행
    }
}