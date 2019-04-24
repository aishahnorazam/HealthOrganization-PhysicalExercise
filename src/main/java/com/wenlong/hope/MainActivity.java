package com.wenlong.hope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.wenlong.hope.MainActivityFragment.BookingFragment;
import com.wenlong.hope.MainActivityFragment.ContactFragment;
import com.wenlong.hope.MainActivityFragment.GymFragment;
import com.wenlong.hope.MainActivityFragment.HighlightFragment;
import com.wenlong.hope.MainActivityFragment.HydroFragment;
import com.wenlong.hope.MainActivityFragment.StrongmanFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private DrawerLayout drawer;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        // direct the first menu item to homepage
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BookingFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_booking);

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BookingFragment()).commit();
                break;
            case R.id.nav_gym:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GymFragment()).commit();
                break;
            case R.id.nav_hydro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HydroFragment()).commit();
                break;
            case R.id.nav_strongman:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StrongmanFragment()).commit();
                break;
            case R.id.nav_highlight:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HighlightFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {


        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BookingFragment()).commit();
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_dialog_alert)
                    .setTitle("Closing Activity")
                    .setMessage("Do you sure you want to close this application?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();
        }



    }
}

