package com.asbozh.fifthhomework;


import android.provider.BaseColumns;

public final class SQLContract {


    private SQLContract() {}

    public static class ItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "item_table";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ICON = "icon";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
