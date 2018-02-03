package com.example.amrshosny.imobuts;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AccountContentActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_content);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        adapter.addFragment(new TicketsFragment(),null);
        adapter.addFragment(new ProfileFragment(), null);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ticket);
        tabLayout.getTabAt(1).setIcon(R.drawable.profile);

    }
}
