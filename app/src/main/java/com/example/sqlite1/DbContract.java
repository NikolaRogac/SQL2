package com.example.sqlite1;

import android.provider.BaseColumns;

public class DbContract
{
    public static final int DATEBASE_VERSION = 1;
    public static final String DATEBASE_NAME = "AKADEMIJA";

    private DbContract()
    {

    }

    public static abstract class TabelaPolaznik implements BaseColumns
    {
        public static final String TABLE_NAME = "Polaznik ";
        public static final String COLUMN_NAME_IME = "Ime";
        public static final String COLUMN_NAME_PREZIME = "Prezime";
        public static final String COLUMN_NAME_GODINA_UPISA = "Godina";
        public static final String COLUMN_NAME_BROJ_POENA = "Poeni";

        public static final String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_IME + " TEXT," +
                COLUMN_NAME_PREZIME + " TEXT," +
                COLUMN_NAME_GODINA_UPISA + " INTEGER," +
                COLUMN_NAME_BROJ_POENA + " INTEGER)";

        public static final String SQL_DELETE_ITEMS_TABLE = "DROP TABLE IF EXIST " + TABLE_NAME;

    }

}
