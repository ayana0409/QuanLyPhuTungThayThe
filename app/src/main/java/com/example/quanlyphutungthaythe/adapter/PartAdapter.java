package com.example.quanlyphutungthaythe.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.model.Part;

import java.util.List;

public class PartAdapter extends ArrayAdapter<Part> {
    Activity context;
    int resource;

    public PartAdapter(@NonNull Activity context, int resource, @NonNull List<Part> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View viewPart;
        if (convertView == null){
            LayoutInflater in = this.context.getLayoutInflater();
            viewPart = in.inflate(this.resource, null);
        }
        else
            viewPart = convertView;

        Part part = getItem(position);

        TextView txtName = viewPart.findViewById(R.id.txtName);
        TextView txtDescription = viewPart.findViewById(R.id.txtDescription);
        TextView txtPrice = viewPart.findViewById(R.id.txtPrice);
        TextView txtCategory = viewPart.findViewById(R.id.txtCategory);

        txtName.setText(part.getName());
        txtDescription.setText(part.getDescription());
        txtPrice.setText(String.valueOf(part.getPrice()));
        txtCategory.setText(part.getCategory());

        return viewPart;
    }

    private void addEvent() {

    }
}
