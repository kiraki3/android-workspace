package com.example.googlemapexample;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

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

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // 위도 경도 위치(오모테산도)
        LatLng location = new LatLng(35.67756239454694, 139.69947579356165);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("明治神宮");        // 위치 이름
        markerOptions.snippet("天皇の神社");   // 위치 설명
        markerOptions.position(location);   // 위치 설정
        googleMap.addMarker(markerOptions); // 위치 저장

        // 위치 보기 (설정 핀 주소, 줌)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));      // moveCamera
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));   // animationCamera
    }
}