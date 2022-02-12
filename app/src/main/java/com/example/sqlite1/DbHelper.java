package com.example.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper
{
    public DbHelper(@Nullable Context context)
    {
        super(context, DbContract.DATEBASE_NAME, null, DbContract.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DbContract.TabelaPolaznik.SQL_CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DbContract.TabelaPolaznik.SQL_DELETE_ITEMS_TABLE);
        onCreate(db);
    }

    public boolean unesiPodatke(String ime, String prezime, String god, String poeni)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.TabelaPolaznik.COLUMN_NAME_IME, ime);
        values.put(DbContract.TabelaPolaznik.COLUMN_NAME_PREZIME, prezime);
        values.put(DbContract.TabelaPolaznik.COLUMN_NAME_GODINA_UPISA, god);
        values.put(DbContract.TabelaPolaznik.COLUMN_NAME_BROJ_POENA, poeni);
        long newRowId = db.insert(DbContract.TabelaPolaznik.TABLE_NAME,null,values);
        if (newRowId == -1)
            return false;
        else
            return true;

    }

    public Cursor prikaziPodatke()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Polaznik ", null, null, null, null, null, null);
        return  cursor;
    }

    public Integer izbrisiPodatke(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DbContract.TabelaPolaznik.TABLE_NAME,"_ID = ?",new String[] {id}) ;
    }
}
