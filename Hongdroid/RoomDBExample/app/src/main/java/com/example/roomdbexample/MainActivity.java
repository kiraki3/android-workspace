package com.example.roomdbexample;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDao mUSerDao; // UserDao를 참조하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // 엣지 투 엣지 모드 활성화 (전체 화면 사용)
        setContentView(R.layout.activity_main); // 레이아웃 설정

        // WindowInsets 설정: 상태 바와 네비게이션 바에 맞게 패딩 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Room 데이터베이스 빌더를 사용하여 데이터베이스 인스턴스를 생성
        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "chudroid_db")
                .fallbackToDestructiveMigration()       // 마이그레이션이 없을 때 기존 데이터를 파괴하고 새로 생성
                .allowMainThreadQueries()               // 메인 스레드에서 데이터베이스 쿼리 실행 허용 (비권장)
                .build();

        mUSerDao = database.userDao(); // UserDao 인터페이스를 통해 데이터베이스 작업 접근

        // 데이터 삽입 (주석 처리됨)
        // User user = new User();
        // user.setName("추도비");
        // user.setAge(30);
        // user.setPhonenumber("08000000000");
        // mUSerDao.setInsertUser(user);

        // 데이터 조회
        List<User> userList = mUSerDao.getUserAll(); // 모든 사용자 정보를 조회
        for (int i = 0; i < userList.size(); i++) {
            Log.d("TEST", userList.get(i).getName() + "\n"
                    + userList.get(i).getAge() + "\n"
                    + userList.get(i).getPhonenumber() + "\n");
        }

        // 데이터 수정 (주석 처리됨)
        // User user2 = new User();
        // user2.setId(2);
        // user2.setName("추도비_동생");
        // user2.setAge(20);
        // user2.setPhonenumber("08012345678");
        // mUSerDao.setUpdateUSer(user2);

        // 데이터 삭제
        User user3 = new User();
        user3.setId(2);
        mUSerDao.setDeleteUser(user3); // id가 2인 사용자 삭제
    }
}
