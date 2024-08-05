package com.example.videorecordexample;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private MediaRecorder mediaRecorder;
    private Button btn_record;
    private SurfaceView surface_view;
    private SurfaceHolder surfaceHolder;
    private boolean recording = false;

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



        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위해 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                .check();

        // 버튼 생성
        btn_record = findViewById(R.id.btn_record);
        surface_view = findViewById(R.id.surface_view);

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 실제로 녹화 버튼이 나오는 부분
                if(recording) {
                    mediaRecorder.stop();    // 시작
                    mediaRecorder.release();  // 끄다
                    camera.lock();            // 잠금
                    surface_view.setVisibility(View.GONE);
                    recording = false;
                    btn_record.setText("녹화시작");
                    Toast.makeText(MainActivity.this, "녹화가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 별도의 ui thread 에서 해주기 위해 (과부화, 오류 방지)
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                            try {
                                mediaRecorder = new MediaRecorder();
                                camera.unlock();
                                surface_view.setVisibility(View.VISIBLE);
                                mediaRecorder.setCamera(camera);
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);              // 영상 옴질
                                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);                 // 저장 공간
                                mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));  // 영상 화질
                                mediaRecorder.setOrientationHint(90);
                                String outputFile = getApplication().getFilesDir().getAbsolutePath() + File.separator + "test.mp4";
                                mediaRecorder.setOutputFile(outputFile);
                                mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                                recording = true;
                                btn_record.setText("녹화종료");
                            } catch (Exception e) {
                                e.printStackTrace();
                                mediaRecorder.release();
                            }
                        }
                    });
                }
            }
        });
    }

    //permission
    PermissionListener permission = new PermissionListener() {
        // 혀용의 경우
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            // 허용한 경우
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            surfaceHolder = surface_view.getHolder();
            surfaceHolder.addCallback(MainActivity.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        // 거부의 경우
        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한 거부", Toast.LENGTH_SHORT).show();
        }
    };
    // 처음 생성됐을때 생명주기
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    }
    // 카메라 초기화
    private void refreshCamera(Camera camera) {
        if(surfaceHolder.getSurface() == null) {
            return;
        }

        try{
            camera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCamera(camera);
    }

    private void setCamera(Camera cam) {

        camera = cam;
    }
    // surfaceView 에 변화를 감지
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera(camera);

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}