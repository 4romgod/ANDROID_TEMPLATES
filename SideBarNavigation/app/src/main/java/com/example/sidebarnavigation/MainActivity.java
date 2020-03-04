package com.example.sidebarnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MAIN ACTIVITY SENDS U TO THE HOME FRAGMENT
        FragmentTransaction defaultFrag = getSupportFragmentManager().beginTransaction();
        defaultFrag.replace(R.id.layout_frames, new FragmentHome());
        defaultFrag.addToBackStack(null).commit();

        //DEALS WITH THE TOOLBAR
        toolbar = findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);       //removes the title

        //DEALS WITH THE ICON and DRAWER NAVIGATION
        mDrawerLayout = findViewById(R.id.layout_drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //prints the toggle menu icon

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

    }       //end onCreate()


    @Override       //makes the toggle menu icon clickeable
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);   //returns the selected menu item
    }

    @Override       //implemented by NavigationView (makes items selectable)
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;       //field that will hold fragments

        //choosing a fragment by clicking
        switch (menuItem.getItemId()){

            case R.id.Item1:
            {
                Toast.makeText(getApplicationContext(), "Item1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Item2:
            {
                Toast.makeText(getApplicationContext(), "Item2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Item3:
            {
                Toast.makeText(getApplicationContext(), "Item3", Toast.LENGTH_SHORT).show();
                break;
            }

        }       //end switch

        //change fragment to selected
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.layout_frames, fragment);       //replaces whatever in the frame layout with fragment field

            ft.addToBackStack(null).commit();
        }       //end if()

        mDrawerLayout.closeDrawer(GravityCompat.START);     //close sidebar menu after item selection
        return true;

    }       //end onNavigationItemSelected()


}       //end class
