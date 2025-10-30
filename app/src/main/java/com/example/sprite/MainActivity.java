package com.example.sprite;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sprite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the toolbar
        setSupportActionBar(binding.appBarMain.toolbar);

        // Example floating action button
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        // Drawer and navigation view
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // ✅ Load menu based on role
        String userRole = getUserRole(); // e.g., "user", "organizer", "admin"
        navigationView.getMenu().clear(); // clear any existing menu first

        switch (userRole.toLowerCase()) {
            case "admin":
                navigationView.inflateMenu(R.menu.app_bar_admin);
                break;
            case "organizer":
                navigationView.inflateMenu(R.menu.app_bar_organizer);
                break;
            default:
                navigationView.inflateMenu(R.menu.app_bar_entrant);
                break;
        }

        // Define top-level destinations
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        // Setup navigation controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // Example role fetcher — replace with real logic later
    private String getUserRole() {
        // TODO: Replace this with your actual user role retrieval logic
        // For example: from SharedPreferences, Firebase, or a local DB
        return "user"; // Change this to "organizer" or "admin" to test
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu if needed (optional for toolbar icons)
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
