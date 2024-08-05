package com.example.mediarecordersample;

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

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private Button btn_record;
    private MediaRecorder recorder;
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

        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            Toast.makeText(getApplicationContext(), "All permission are granted", Toast.LENGTH_SHORT).show();
                            camera = Camera.open();
                            camera.setDisplayOrientation(90);
                            surface_view = findViewById(R.id.surface_view);
                            surfaceHolder.addCallback(MainActivity.this);
                            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                        } else {
                            Toast.makeText(getApplicationContext(), "These permissions are denied " + deniedList , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btn_record = (Button) findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recording) {
                    recorder.start();
                    recorder.release();
                    camera.lock();
                    recording = false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "녹화가 시작하겠습니다", Toast.LENGTH_SHORT).show();
                            try {
                                recorder = new MediaRecorder();
                                camera.unlock();
                                recorder.setCamera(camera);
                                recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                                recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                                recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));
                                recorder.setOrientationHint(90);
                                recorder.setOutputFile("/storage/emulated/0");
                                recorder.setPreviewDisplay(surfaceHolder.getSurface());
                                recorder.prepare();
                                recorder.start();
                                recording = true;
                            }catch (Exception e) {
                                e.printStackTrace();
                                recorder.release();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    }

    private void refreshCamera(Camera camera) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        try {
            camera.stopPreview();
        } catch (Exception e){
            e.printStackTrace();
        }

        setCamera(camera);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }
}