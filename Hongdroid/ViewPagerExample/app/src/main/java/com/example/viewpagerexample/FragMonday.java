package com.example.viewpagerexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragMonday extends Fragment {
    private View view;

    public static FragMonday newInstance() {
        FragMonday fragMonday = new FragMonday(); // 객체 생성
        return fragMonday;  // adapter 에 맞춰 주기적으로 instance가 만들어진다
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mon, container, false); // frag_mon 와 연동

        return view;
    }
}
