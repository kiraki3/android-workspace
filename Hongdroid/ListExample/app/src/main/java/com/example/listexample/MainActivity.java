package com.example.listexample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

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

        listView = (ListView)findViewById(R.id.listView);
        // 데이터를 저장하는 리스트 > String 형태로
        List<String> data = new ArrayList<>();
        // 리스트뷰와 리스트를 연결하는 역할 > adapter
        // new ArrayAdapter<>(현재 파일 this, 가지고 올 디자인, 위에 선언했던 데이터리스트 이름);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);

        //list 에 adapter 를 담아준다 (연결)
        listView.setAdapter(adapter);

        //아이템 추가
        data.add("업동이");
        data.add("갱이");
        data.add("귀여워");
        // 저장
        adapter.notifyDataSetChanged();
    }
}