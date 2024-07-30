package com.example.viewbindingexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewbindingexample.databinding.FragTestBinding;

public class TestFragment extends Fragment {

    private FragTestBinding fBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fBinding = FragTestBinding.inflate(inflater, container,false);
        fBinding.btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fBinding.tvFragment.setText("Click Button!!");
            }
        });
        return fBinding.getRoot();
    }
}
