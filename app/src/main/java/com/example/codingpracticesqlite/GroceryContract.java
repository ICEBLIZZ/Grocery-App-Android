package com.example.codingpracticesqlite;

import android.provider.BaseColumns;

public class GroceryContract {

    private GroceryContract(){}

    //We will make inner classes for each table in our database
    public static final class GroceryEntry implements BaseColumns {
        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timeStamp";

    }
}
