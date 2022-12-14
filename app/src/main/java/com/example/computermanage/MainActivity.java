package com.example.computermanage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import com.example.computermanage.ui.FragmentHoaDon;
import com.example.computermanage.ui.FragmentThem;
import com.example.computermanage.ui.FragmentThongKe;
import com.example.computermanage.ui.FragmentTrangChu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActionBar toolbar;
    private long Pressed;
    Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new FragmentTrangChu()).commit();
        }
        addControl();
        addEvent();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.trangchu:
                            selectedFragment = new FragmentTrangChu();
                            break;
                        case R.id.hoadon:
                            selectedFragment = new FragmentHoaDon();
                            break;
                        case R.id.thongke:
                            selectedFragment = new FragmentThongKe();
                            break;
                        case R.id.them:
                            selectedFragment = new FragmentThem();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            selectedFragment).commit();

                    return true;
                }
            };



    private void addEvent() {
    }

    private void addControl() {
        toolbar = getSupportActionBar();
    }
    @Override
    public void onBackPressed() {
        if (Pressed + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            moveTaskToBack(true);
        } else {
            mToast = Toast.makeText(getApplicationContext(), "???n 2 l???n ????? tho??t ???ng d???ng", Toast.LENGTH_SHORT);
            mToast.show();
        }
        Pressed = System.currentTimeMillis();
    }
}
