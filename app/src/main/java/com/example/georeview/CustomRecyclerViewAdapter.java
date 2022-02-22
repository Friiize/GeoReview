package com.example.georeview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private final Activity activity;
    private ArrayList<ItemModal> itemModalArrayList;
    private OnItemListener onItemListener;

    public CustomRecyclerViewAdapter(@NonNull Activity activity, ArrayList<ItemModal> itemModalArrayList, OnItemListener onItemListener) {
        this.activity = activity;
        this.itemModalArrayList = itemModalArrayList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View converted = activity.getLayoutInflater().inflate(R.layout.item_view, null, true);
        return new ViewHolder(converted, onItemListener, itemModalArrayList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewH, int position) {

        ItemModal itemModal = itemModalArrayList.get(position);

        viewH.name.setText(itemModal.getName());
        viewH.image.setImageDrawable(LoadImageFromWebOperations(itemModal.getImage())); //Image resizer à discuter
        String currentNote = itemModal.getNote() + "/20";
        viewH.note.setText(currentNote);

    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return itemModalArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, note;
        ImageView image;
        OnItemListener itemListener;
        ArrayList<ItemModal> itemModalArrayList;

        ViewHolder(View v, OnItemListener onItemListener, ArrayList<ItemModal> itemModalArrayList) {
            super(v);
            name = (TextView) v.findViewById(R.id.itemName);
            image = (ImageView) v.findViewById(R.id.itemImage);
            note = (TextView) v.findViewById(R.id.itemNote);
            this.itemModalArrayList = itemModalArrayList;
            this.itemListener = onItemListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.onItemClick(getAdapterPosition(), itemModalArrayList);
        }
    }

    public interface OnItemListener {
        void onItemClick(int position, ArrayList<ItemModal> itemModalArrayList);
    }
}
