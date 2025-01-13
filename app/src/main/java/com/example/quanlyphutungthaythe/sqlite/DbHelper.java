package com.example.quanlyphutungthaythe.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "management_db.sqlite";
    private static final int DB_VERSION = 1;
    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPartTableQuery = "CREATE TABLE IF NOT EXISTS Part("+
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT NOT NULL," +
                "Category TEXT NOT NULL," +
                "Description TEXT NOT NULL," +
                "Price REAL NOT NULL)";
        String createAccountTableQuery = "CREATE TABLE IF NOT EXISTS Account("+
                "Username TEXT PRIMARY KEY, " +
                "Password TEXT NOT NULL)";
        db.execSQL(createPartTableQuery);
        db.execSQL(createAccountTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropPartTable = "DROP TABLE IF EXISTS Part";
        String dropAccountTable = "DROP TABLE IF EXISTS Account";
        db.execSQL(dropPartTable);
        db.execSQL(dropAccountTable);
        onCreate(db);
    }
}
