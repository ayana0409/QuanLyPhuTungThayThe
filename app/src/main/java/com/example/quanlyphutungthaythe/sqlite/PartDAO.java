package com.example.quanlyphutungthaythe.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlyphutungthaythe.model.Part;

import java.util.ArrayList;
import java.util.List;


public class PartDAO {
    private SQLiteDatabase db;
    public PartDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);

        db = dbHelper.getWritableDatabase();
    }

    public List<Part> getAllPart(){
        List<Part> jobs = new ArrayList<>();
        String query = "SELECT * FROM Part";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);
            float price = cursor.getFloat(4);

            jobs.add(new Part(id, name, category, description, price));
        }
        return jobs;
    }

    public boolean addPart(Part part){
        String query = "INSERT INTO Part(Name, Category, Description, Price) VALUES(?, ?, ?, ?)";
        ContentValues values = new ContentValues();
        values.put("Name", part.getName());
        values.put("Category", part.getCategory());
        values.put("Description", part.getDescription());
        values.put("Price", part.getPrice());

        long result = db.insert("Part", null, values);
        return result > 0;
    }

    public void updatePart(Part part){
        String query = "UPDATE Part SET Name = ?, Category = ?, Description = ?, Price = ? WHERE Id = ?";
        db.execSQL(query, new String[]{part.getName(), part.getCategory(), part.getDescription(),
                String.valueOf(part.getPrice()), String.valueOf(part.getId())});
    }

    public void deletePart(int id){
        String query = "DELETE FROM Part WHERE Id = ?";
        db.execSQL(query, new String[]{String.valueOf(id)});
    }
}
