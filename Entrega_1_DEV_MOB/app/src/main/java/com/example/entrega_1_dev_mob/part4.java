package com.example.entrega_1_dev_mob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class part4 extends AppCompatActivity {
    private TabLayout tb;
    private TabItem a,b,c;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4);
        getSupportActionBar().hide();
        IniciarComponents();
        PagerAdapter pagerAdapter = new
                PagerAdapter(getSupportFragmentManager(),
                tb.getTabCount());
        vp.setAdapter(pagerAdapter);

        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void IniciarComponents(){
        tb = findViewById(R.id.tabLayout);
        a = findViewById(R.id.aba1);
        b = findViewById(R.id.aba2);
        c = findViewById(R.id.aba3);
        vp = findViewById(R.id.vpager);
    }
}