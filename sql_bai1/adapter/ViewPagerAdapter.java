package com.example.sql_bai1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sql_bai1.fragment.FragmentInformation;
import com.example.sql_bai1.fragment.FragmentList;
import com.example.sql_bai1.fragment.FragmentSearch;

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

    @Override
    public int getCount() {
        return 3;// so luong fragment
    }
}
