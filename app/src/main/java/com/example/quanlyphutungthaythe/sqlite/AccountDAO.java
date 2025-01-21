package com.example.quanlyphutungthaythe.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountDAO {
    private SQLiteDatabase db;
    public AccountDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public Boolean insert(String account, String passWord){

        ContentValues values = new ContentValues();
        values.put("Username", account);
        values.put("Password", passWord);
        long result = db.insert("Account", null, values);
        if (result == -1)
            return false;
        return true;
    }
    //=============================================
    public Boolean isAccount(String username){
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE Username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    //===========================================
    public Boolean isAccount_PassWord(String user, String password){
        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE Username = ? AND Password = ?", new String[]{user, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}


