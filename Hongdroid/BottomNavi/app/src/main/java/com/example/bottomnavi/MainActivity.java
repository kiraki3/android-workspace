package com.example.bottomnavi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    // 하단 바
    private BottomNavigationView bottomNavigationView;
    private MenuItem mt;
    // 5개의 UI(생명주기)
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private Frag5 frag5;

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


        // 하단 바 아이디 연결
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavi);
        // 하단 바 아이콘 아이디 연결
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.action_lens_blur) {
                    setFrag(0);
                } else if (menuItem.getItemId() == R.id.action_nature_people) {
                    setFrag(1);
                } else if (menuItem.getItemId() == R.id.action_place) {
                    setFrag(2);
                } else if (menuItem.getItemId() == R.id.action_pool) {
                    setFrag(3);
                } else if (menuItem.getItemId() == R.id.action_brain) {
                    setFrag(4);
                }
                return true;
            }
        });
        // 객체 생성
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        frag5 = new Frag5();
        setFrag(0);  // 첫 프래그먼트 화면을 무엇으로 지정하는지 설정

    }
    // 함수 생성
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        // 실제로 fragment 교체가 이루어질때, 트랜젝션을 하게 한다.
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, frag5);
                ft.commit();
                break;
        }

    }
}