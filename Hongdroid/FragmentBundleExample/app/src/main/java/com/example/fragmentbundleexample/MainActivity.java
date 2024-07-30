package com.example.fragmentbundleexample;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

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


        // 첫번째 창에 fragment1 을 불러오는 방법
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); // transaction > 프래그먼트를 관리 Intent와 비슷
        Fragment1 fragment1 = new Fragment1(); // fragment1 생성
        transaction.replace(R.id.frameLayout, fragment1); // activity 와 동일 transaction.replace(교체할 영역, 꾸러미에 넣은 것들)
        transaction.commit(); // 저장
    }
}