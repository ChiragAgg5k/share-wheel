package com.obsidian.sharewheel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {

    RelativeLayout container;
    BottomNavigationView bnView;
    EditText pickup, whereTo;
    Button ridePostButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bnView = findViewById(R.id.bnView);
        container = findViewById(R.id.container);
        pickup = findViewById(R.id.ridePostPickupInput);
        whereTo = findViewById(R.id.ridePostWhereToInput);

        databaseReference = FirebaseDatabase.getInstance().getReference("ride-posts");

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