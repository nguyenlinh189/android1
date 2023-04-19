package com.example.sql_bai2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sql_bai2.fragment.FragmentInformation;
import com.example.sql_bai2.fragment.FragmentList;
import com.example.sql_bai2.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmentInformation();
            case 1:return new FragmentList();
            case 2:return new FragmentSearch();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "thong tin";
            case 1:
                return "danh sach";
            case 2:
                return "tim kiem va thong ke";
        }
        return "thong tin";
    }

    @Override
    public int getCount() {
        return 3;// so luong fragment
    }
}
