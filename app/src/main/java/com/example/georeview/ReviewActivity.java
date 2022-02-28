package com.example.georeview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.OnItemListener {
    UserLogModal userLogModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        //CustomRecyclerViewAdapter custom = new CustomRecyclerViewAdapter();
        userLogModal = new UserLogModal(getIntent().getExtras().getString("username"));
        CreateRecycler();
    }

    private void CreateRecycler() {
        CustomRecyclerViewAdapter recyclerViewAdapter;
        RecyclerView menuRecycler;
        RecyclerView.LayoutManager menuManager;
        ArrayList<ItemModal> itemModalArrayList = new ArrayList<>();

        DBHandler dbHandler = new DBHandler();
        dbHandler.setMETHOD_NAME("get_objs_files");
        dbHandler.setUserLogModal(userLogModal);
        dbHandler.setItemModalArrayList(itemModalArrayList);
        dbHandler.execute(userLogModal.username); //Problème pour récupérer les infos

        menuRecycler = (RecyclerView) findViewById(R.id.reviewRecycler);
        recyclerViewAdapter = new CustomRecyclerViewAdapter(this, dbHandler.itemModalArrayList, this);
        menuManager = new LinearLayoutManager(ReviewActivity.this, RecyclerView.VERTICAL, false);

        menuRecycler.setLayoutManager(menuManager);
        menuRecycler.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onItemClick(int position, ArrayList<ItemModal> itemModalArrayList) {
        Intent i = new Intent(this, EditItemActivity.class);
        i.putExtra("item", itemModalArrayList.get(position));
    }
}