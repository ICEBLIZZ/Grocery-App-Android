package com.example.codingpracticesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.codingpracticesqlite.GroceryContract.*;

public class GroceryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //this is called a first time a database is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE "+ GroceryEntry.TABLE_NAME +
                " (" +GroceryEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryEntry.COLUMN_NAME+ " TEXT NOT NULL, " + GroceryEntry.COLUMN_AMOUNT +
                " INTEGER NOT NULL, " + GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT" +
                " CURRENT_TIMESTAMP);";
        //GroceryEntry._ID is due to the BaseColumns interface
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +GroceryEntry.TABLE_NAME);
        onCreate(db);
    }

}
