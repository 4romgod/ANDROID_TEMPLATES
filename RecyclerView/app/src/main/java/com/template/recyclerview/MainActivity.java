package com.template.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerView adapterRecyclerView;

    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        adapterRecyclerView = new AdapterRecyclerView(this, users);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapterRecyclerView);

        prepareData();
    }

    public void prepareData(){
        users.add(new User("Ebenezer", "Mathebula", "I love boxing too much", R.drawable.smile));
        users.add(new User("Nsuku", "Mathebula", "I training", R.drawable.serious));
        users.add(new User("Clarah", "Mathebula", "I love farming", R.drawable.pretty));
        users.add(new User("Bonny", "Mathebula", "I love the law", R.drawable.shorts));
        users.add(new User("Ebenezer", "Mathebula", "I love boxing too much", R.drawable.smile));
        users.add(new User("Nsuku", "Mathebula", "I training", R.drawable.serious));
        users.add(new User("Clarah", "Mathebula", "I love farming", R.drawable.pretty));
        users.add(new User("Bonny", "Mathebula", "I love the law", R.drawable.shorts));
    }

}
