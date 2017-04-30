package com.askmydoctors.askmydoctors.views;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.LoginAdapter;

public class LoginActivity extends AppCompatActivity {
    ViewPager pager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setElevation(0);

        pager = (ViewPager) findViewById(R.id.pagerLogin);
        tabLayout = (TabLayout) findViewById(R.id.tabLogin);

        FragmentManager manager=getSupportFragmentManager();
        LoginAdapter adapter= new com.askmydoctors.askmydoctors.adapters.LoginAdapter(manager);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }
}
