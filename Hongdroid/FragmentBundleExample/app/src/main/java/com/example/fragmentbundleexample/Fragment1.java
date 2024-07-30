package com.example.fragmentbundleexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment1 extends Fragment {

    private View view;
    private TextView tv_frag1;
    private Button btn_move;
    private String result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, container,false);

        tv_frag1 = view.findViewById(R.id.tv_frag1);
        btn_move = view.findViewById(R.id.btn_move);

        if(getArguments() != null) { // 빈값이 아니라면
            result = getArguments().getString("fromFrag2"); // bundle.putString("fromFrag2","추도비 프래그먼트 2"); 담아놨던 첫번째 인자 키값
            tv_frag1.setText(result);
        }

        btn_move.setOnClickListener(new View.OnClickListener() {  // 프래그먼트 2로 이동
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle(); // 무언가를 담을 준비를 하는 보따리
                bundle.putString("fromFrag1","추도비 프래그먼트 1");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); // transaction > 프래그먼트를 관리 Intent와 비슷
                Fragment2 fragment2 = new Fragment2(); // fragment2 생성
                fragment2.setArguments(bundle); // 꾸러미를 넣어주는 과정
                transaction.replace(R.id.frameLayout, fragment2); // activity 와 동일 transaction.replace(교체할 영역, 꾸러미에 넣은 것들)
                transaction.commit(); // 저장
            }
        });

        return view;
    }
}
