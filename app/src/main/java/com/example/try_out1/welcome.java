package com.example.try_out1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.try_out1.Event.chelsea;
import com.example.try_out1.Team.arsenal;
import com.google.android.material.navigation.NavigationView;

public class welcome extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showHomePage();

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment selectedFragment = null;

                if (itemId == R.id.Arsenal) {
                    selectedFragment = new arsenal();
                } else if (itemId == R.id.Chelsea) {
                    selectedFragment = new chelsea();
                } else if (itemId == R.id.akun) {
                    // Ganti dengan memulai aktivitas HomeAct
                    Intent intent = new Intent(welcome.this, HomeAct.class);
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                    return true;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, selectedFragment)
                            .commit();
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        ImageView imageView = navigationView.getHeaderView(0).findViewById(R.id.imageViewProfile);
        String imageUrl = "https://i.pinimg.com/564x/fe/fd/26/fefd2613841f0f0392666d0e6e068a46.jpg";
        Glide.with(this).load(imageUrl).into(imageView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void showHomePage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new arsenal())
                .commit();

        getSupportActionBar().setTitle("welcome!!");
    }
}
