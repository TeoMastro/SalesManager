package com.example.salesmanager;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    //create variables
    private AppBarConfiguration mAppBarConfiguration;
    public static FragmentManager fragmentManager;
    public static SalesManagerDatabase salesManagerDatabase;
    //static metavlhth gia na gnwrizw poion pelath exw epilegmeno kathe fora gia na leitourgei swsta to kalathi agorwn moy
    public static int selected_customer_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gia na kanw transitions meta3u twn fragments
        fragmentManager =getSupportFragmentManager();
        //gia na buildarw th vash
        salesManagerDatabase = Room.databaseBuilder(getApplicationContext(),SalesManagerDatabase.class,"eshopDB").allowMainThreadQueries().build();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Pernaw ta ids twn menu poy vriskontai sto side bar
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_analytikh_pelatwn ,R.id.nav_analytikh_proiontwn , R.id.nav_synolikes_pwlhseis,R.id.nav_ypoloipo_pwlhseis,R.id.nav_kalathi_agorwn)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
