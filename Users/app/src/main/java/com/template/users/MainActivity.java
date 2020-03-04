package com.template.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerViewUsers adapterRecyclerView;

    public static ViewModelUsers viewModelUsers;


    FloatingActionButton btnFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapterRecyclerView = new AdapterRecyclerViewUsers(this);
        recyclerView.setAdapter(adapterRecyclerView);

        //ask android system for viewModel if exist, else build one
        viewModelUsers = ViewModelProviders.of(this).get(ViewModelUsers.class);
        viewModelUsers.getListUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapterRecyclerView.setUsers(users);
            }
        });


        btnFloat = findViewById(R.id.floatingActionButton);
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelUsers.insert(new User("Carbon", "Dioxide", "I polute the air", R.drawable.ic_launcher_foreground));
            }
        });


    }       //end onCreate()



}       //end class