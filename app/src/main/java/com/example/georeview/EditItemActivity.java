package com.example.georeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    ArrayList<ItemModal> itemModalArrayList;
    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        data = getIntent().getExtras();
        itemModalArrayList = (ArrayList<ItemModal>) data.getParcelable("items");
    }
}