package com.example.sharedexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //EditText 아이디 선언
    private EditText et_save;
    // String 함수 선언
    private String shared = "file";

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
        // acitivity_main.xml 파일과 연결
        et_save = (EditText)findViewById(R.id.et_save);
        // SharedPreferences 선언
        // getSharedPreferences(위에 선언한 String 값="파일이름",'모드')
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        // 아래 onDestroy 를 실행하고, commit 이후
        String value = sharedPreferences.getString("USERID", "");
        // value 에 담겨있는 String 값을 불러온다.
        et_save.setText(value);


    }

    // ctrl+알파벳o
    // onDestroy 라는 생명주기 생성
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 앞서 작성했던 텍스트를 가져옴
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        // sharedPreference 안에 editor 를 연결
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // editText 에 입력한 문자를 가져올 때
        String value = et_save.getText().toString();
        // 받아온 값을 String 형태로 value에 저장
        editor.putString("USERID", value);
        // 항상 commit 과 apply 를 해줘야 저장됨
        editor.commit();


    }
}