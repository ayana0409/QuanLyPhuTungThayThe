package com.example.quanlyphutungthaythe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlyphutungthaythe.R;
import com.example.quanlyphutungthaythe.adapter.PartAdapter;
import com.example.quanlyphutungthaythe.model.Part;
import com.example.quanlyphutungthaythe.sqlite.PartDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagePartActivity extends AppCompatActivity {

    private ListView lvPart;
    private FloatingActionButton btnAdd;
    private PartAdapter adapter;
    private PartDAO _jobDaAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_part);

        addView();
        addEvent();
    }

    private void addView(){
        _jobDaAO = new PartDAO(this);
        lvPart = findViewById(R.id.lvPart);
        btnAdd = findViewById(R.id.actionButtonAdd);

        loadData();
    }

    private void loadData(){
        List<Part> partList = _jobDaAO.getAllPart();

        List<Part> part = new ArrayList<>();
        part.add(new Part(1, "SP1", "Loại 1", "Mô tả 1", 0));
        part.add(new Part(2, "SP2", "Loại 2", "Mô tả 2", 0));
        part.add(new Part(3, "SP3", "Loại 3", "Mô tả 3", 0));

        part.add(new Part(1, "SP1", "Loại 1", "Mô tả 1", 0));
        part.add(new Part(1, "SP1", "Loại 1", "Mô tả 1", 0));
        part.add(new Part(1, "SP1", "Loại 1", "Mô tả 1", 0));


        adapter = new PartAdapter(this, R.layout.part_item_layout, partList);
        lvPart.setAdapter(adapter);
    }

    private void addEvent(){
        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddPartActivity.class);
            startActivity(intent);
        });

        lvPart.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, UpdateDeletePartActivity.class);
            intent.putExtra("part", (Serializable) adapter.getItem(position));
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

}