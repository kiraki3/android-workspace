package com.example.intentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = findViewById(R.id.et_test);


        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                str = et_test.getText().toString();
                Intent intent = new Intent(MainActivity.this , SubActivity.class);
                intent.putExtra("str", str);
                startActivity(intent); // 액티비티 이동
                // 첫 액티비티의 값(스트링)을 서브액티비티에서 받아오는 것


            }
        });
    }
}
