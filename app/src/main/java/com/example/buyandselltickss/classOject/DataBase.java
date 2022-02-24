package com.example.buyandselltickss.classOject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // truy vân không trả kết quả
    public void QueryData(String sql){
        SQLiteDatabase Database = getWritableDatabase();
        Database.execSQL(sql);
    }
    // truy vấn trả kết quả
    public Cursor GetData(String sql){
        SQLiteDatabase Database = getReadableDatabase();
        return Database.rawQuery(sql, null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
