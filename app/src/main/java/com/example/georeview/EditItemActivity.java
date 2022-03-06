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
    UserLogModal userLogModal;
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
        itemModal = data.getParcelable("item");
        userLogModal = new UserLogModal(data.getString("username"));

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
                if (itemModal.getId().equals("-2")) {
                    dbHandler.setMETHOD_NAME("set_obj_file");
                    dbHandler.execute(userLogModal.getUsername(), itemModal.getName(), itemModal.getImage(), itemModal.getGeoloc(), itemModal.getNote());
                } else {
                    dbHandler.setMETHOD_NAME("edit_obj_file");
                    dbHandler.execute(itemModal.getId(), itemModal.getName(), itemModal.getImage(), itemModal.getGeoloc(), itemModal.getNote());
                }

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