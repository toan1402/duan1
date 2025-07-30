package com.example.duan1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_favorite) {
                selectedFragment = new FavoriteFragment();
            } else if (id == R.id.nav_notification) {
                selectedFragment = new NotificationFragment();
            } else if (id == R.id.nav_account) {
                selectedFragment = new AccountFragment();
            }

            return loadFragment(selectedFragment);
        });

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment == null) return false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }
}
