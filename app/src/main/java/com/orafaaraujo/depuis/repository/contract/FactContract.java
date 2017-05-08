package com.orafaaraujo.depuis.repository.contract;

import android.provider.BaseColumns;

/**
 * Created by rafael on 02/05/17.
 */

public final class FactContract {

    private FactContract() {
    }

    public static class FactEntry implements BaseColumns {

        public static final String TABLE_NAME = "fact";
        public static final String COLUMN_NAME_START_TIME = "startTime";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_END_TIME = "endTime";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FactEntry.TABLE_NAME + " (" +
                        FactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        FactEntry.COLUMN_NAME_START_TIME + " INTEGER," +
                        FactEntry.COLUMN_NAME_TITLE + " TEXT," +
                        FactEntry.COLUMN_NAME_COMMENT + " TEXT," +
                        FactEntry.COLUMN_NAME_END_TIME + " INTEGER)";


        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FactEntry.TABLE_NAME;

        public static final String FIND_ALL = "SELECT  * FROM " + TABLE_NAME;
    }
}
