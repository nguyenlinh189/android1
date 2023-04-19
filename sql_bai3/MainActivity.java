package com.example.sql_bai3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.sql_bai3.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView=findViewById(R.id.bottom_nav);
        viewPager=findViewById(R.id.viewPager);
        fab=findViewById(R.id.floating);
        tab=findViewById(R.id.tab);
        // them --floating
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"them chi tieu",Toast.LENGTH_SHORT).show();
                Intent t=new Intent(getApplicationContext(),AddActivity.class);
                startActivity(t);
            }
        });

        //can tao behavior de khi quay lai thi no se lam noi du lieu
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //set adapter cho view pager
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        // neu chon chuyen trang trang o viewpager thi cung phai set cho bottom
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.minfor).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mlist).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.msearch).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // neu chon o bottom thi cung phai set cho viewpager
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.minfor:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mlist:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.msearch:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
}