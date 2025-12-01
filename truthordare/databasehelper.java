package com.example.truthordare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "players.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_PLAYERS = "players";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PLAYERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void addPlayer(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        db.insert(TABLE_PLAYERS, null, cv);
    }

    public ArrayList<String> getAllPlayers() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PLAYERS, null);

        if (c.moveToFirst()) {
            do {
                list.add(c.getString(c.getColumnIndexOrThrow(COL_NAME)));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void deletePlayer(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("players", "name = ?", new String[]{name});
        db.close();
    }
}
