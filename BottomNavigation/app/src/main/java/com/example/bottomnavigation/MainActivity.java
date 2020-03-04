package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableBottomNav();      //method to enable bottom navigation

        RelativeLayout relativeLayout = new RelativeLayout(this);

        TextView tv = new TextView(this);
        tv.setText("Testing TextViews");
        tv.setTextColor(Color.RED);

        Button button = new Button(this);
        button.setText("Testing");
        button.setBackgroundColor(Color.GREEN);

        relativeLayout.addView(tv);
        relativeLayout.addView(button);

        setContentView(relativeLayout);

        //setContentView(tv);


    }       //end onCreate()


    //enables the bottom navigation
    public void enableBottomNav(){

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;      //holds fragment to change into

                switch (menuItem.getItemId()){

                    case R.id.item1:
                    {
                        Toast.makeText(getApplicationContext(), "Item1", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case  R.id.item2:
                    {
                        Toast.makeText(getApplicationContext(), "Item2", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.item3:
                    {
                        Toast.makeText(getApplicationContext(), "Item3", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }       //end switch

                return true;
            }
        });

    }       //end enableBottomNav()


}       //end class
