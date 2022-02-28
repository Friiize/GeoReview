package com.example.georeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    ItemModal itemModal;
    Bundle data;
    Button btnEdit, btnGeoloc, btnImage, btnDelete;
    EditText editName, editNote;
    MapView geoMap;
    ImageView itemImg;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        data = getIntent().getExtras();
        itemModal = data.getParcelable("items");

        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnGeoloc = findViewById(R.id.btnRegeoloc);
        btnImage = findViewById(R.id.btnChangeImg);
        editName = findViewById(R.id.editName);
        editNote = findViewById(R.id.editNote);
        geoMap = findViewById(R.id.mapView);
        itemImg = findViewById(R.id.imageView);

        editName.setText(itemModal.getName());
        editNote.setText(itemModal.getNote());
        //itemImg.setImageDrawable(R.drawable); Implémenter les images
        //geoMap

        onClickHandler();
    }

    public void onClickHandler() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGeoloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}