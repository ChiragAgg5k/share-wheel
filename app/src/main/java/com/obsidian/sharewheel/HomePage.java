package com.obsidian.sharewheel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    RelativeLayout container;
    BottomNavigationView bnView;
    ImageView menu;
    NavigationView sideNav;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bnView = findViewById(R.id.bnView);
        container = findViewById(R.id.container);
        menu = findViewById(R.id.menu_icon);
        sideNav = findViewById(R.id.side_nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        bnView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFrag(new home_fragment(), false);
            } else if (id == R.id.nav_my_booking) {
                loadFrag(new MyBooking_fragment(), false);
            } else {
                loadFrag(new MyProfile_fragment(), false);
            }

            return true;
        });
        bnView.setSelectedItemId(R.id.nav_home);

        menu.setOnClickListener(v -> {
            drawerLayout.openDrawer(sideNav);
        });

    }

    public void loadFrag(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }

}