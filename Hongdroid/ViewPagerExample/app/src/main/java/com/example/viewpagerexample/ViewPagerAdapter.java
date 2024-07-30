package com.example.viewpagerexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter  extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // 프래그먼트 교체를 보여주는 처리를 구현한 곳
    @NonNull
    @Override
    public Fragment getItem(int position) { // 호출
        switch (position) {
            case 0 :
                return FragMonday.newInstance();
            case 1 :
                return FragTuesday.newInstance();
            case 2 :
                return FragWednesday.newInstance();
            default:
                return null;
        }
    }

    // 갯수를 명시해줘야 함
    @Override
    public int getCount() { // 월 화 수 세가지이기 때문에 3을 리턴
        return 3;
    }

    // 상단Tap 레이아웃 인디케이터 쪽에 텍스트를 선언해주는 곳
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            default:
                return null;
        }
    }
}
