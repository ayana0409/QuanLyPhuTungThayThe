package com.example.quanlyphutungthaythe.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.adapter.PartAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManagePartActivity extends AppCompatActivity {

    private ListView lvPart;
    private FloatingActionButton btnAdd;
    private PartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_part);

        addView();
    }

    private void addView(){
        lvPart = findViewById(R.id.lvPart);
        btnAdd = findViewById(R.id.actionButtonAdd);

        loadData();
    }

    private void loadData(){
        //var partList;
        adapter = new PartAdapter(this, R.layout.part_item_layout, null);
        lvPart.setAdapter(adapter);
    }
}