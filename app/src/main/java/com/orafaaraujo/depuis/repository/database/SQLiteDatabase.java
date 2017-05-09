package com.orafaaraujo.depuis.repository.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.repository.contract.FactContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 02/05/17.
 */
public class SQLiteDatabase extends SQLiteOpenHelper implements FactDatabase {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FactDatabase.db";

    public SQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL(FactContract.FactEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion,
            int newVersion) {
        db.execSQL(FactContract.FactEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public long saveFact(Fact fact) {
        final ContentValues values = new ContentValues();
        values.put(FactContract.FactEntry.COLUMN_NAME_START_TIME, fact.startTime());
        values.put(FactContract.FactEntry.COLUMN_NAME_TITLE, fact.title());
        values.put(FactContract.FactEntry.COLUMN_NAME_COMMENT, fact.comment());
        values.put(FactContract.FactEntry.COLUMN_NAME_END_TIME, fact.endTime());

        return getWritableDatabase().insert(FactContract.FactEntry.TABLE_NAME, null, values);
    }

    @Override
    public void updateFact(Fact fact) {

        final ContentValues values = new ContentValues();
        values.put(FactContract.FactEntry.COLUMN_NAME_START_TIME, fact.startTime());
        values.put(FactContract.FactEntry.COLUMN_NAME_TITLE, fact.title());
        values.put(FactContract.FactEntry.COLUMN_NAME_COMMENT, fact.comment());
        values.put(FactContract.FactEntry.COLUMN_NAME_END_TIME, fact.endTime());

        final String where = FactContract.FactEntry._ID + " = ?";
        final String[] whereArgs = {String.valueOf(fact.id())};

        getWritableDatabase().update(FactContract.FactEntry.TABLE_NAME, values, where, whereArgs);

    }

    @Nullable
    @Override
    public Fact findFact(long factId) {

        Fact returnFact = null;

        final String where = FactContract.FactEntry._ID + " = ?";
        final String[] whereArgs = {String.valueOf(factId)};
        final Cursor cursor = getReadableDatabase()
                .query(FactContract.FactEntry.TABLE_NAME, null, where, whereArgs, null, null, null);
        while (cursor.moveToNext()) {


            returnFact = Fact.builder()
                    .setId(cursor.getInt(0))
                    .setStartTime(cursor.getLong(1))
                    .setTitle(cursor.getString(2))
                    .setComment(cursor.getString(3))
                    .setEndTime(cursor.getLong(4))
                    .build();
        }
        cursor.close();
        return returnFact;
    }

    @NonNull
    @Override
    public List<Fact> fetchAll() {
        final Cursor cursor = getReadableDatabase()
                .rawQuery(FactContract.FactEntry.FIND_ALL, null);

        final List<Fact> facts = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()) {
            facts.add(
                    Fact.builder()
                            .setId(cursor.getInt(0))
                            .setStartTime(cursor.getLong(1))
                            .setTitle(cursor.getString(2))
                            .setComment(cursor.getString(3))
                            .setEndTime(cursor.getLong(4))
                            .build());
        }
        cursor.close();
        return facts;
    }

    @Override
    public void deleteFact(Fact fact) {
        final String selection = FactContract.FactEntry._ID + " = ?";
        final String[] selectionArgs = {String.valueOf(fact.id())};
        getWritableDatabase().delete(FactContract.FactEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void deleteTable() {
        getReadableDatabase().delete(FactContract.FactEntry.TABLE_NAME, null, null);
    }
}
