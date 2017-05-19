package com.orafaaraujo.depuis.repository.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.FactModel;
import com.orafaaraujo.depuis.repository.contract.FactContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Database using SQLite.
 *
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
    public long saveFact(FactModel factModel) {
        final ContentValues values = new ContentValues();
        values.put(FactContract.FactEntry.COLUMN_NAME_START_TIME, factModel.startTime());
        values.put(FactContract.FactEntry.COLUMN_NAME_TITLE, factModel.title());
        values.put(FactContract.FactEntry.COLUMN_NAME_COMMENT, factModel.comment());
        values.put(FactContract.FactEntry.COLUMN_NAME_END_TIME, factModel.endTime());

        return getWritableDatabase().insert(FactContract.FactEntry.TABLE_NAME, null, values);
    }

    @Override
    public void updateFact(FactModel factModel) {

        final ContentValues values = new ContentValues();
        values.put(FactContract.FactEntry.COLUMN_NAME_START_TIME, factModel.startTime());
        values.put(FactContract.FactEntry.COLUMN_NAME_TITLE, factModel.title());
        values.put(FactContract.FactEntry.COLUMN_NAME_COMMENT, factModel.comment());
        values.put(FactContract.FactEntry.COLUMN_NAME_END_TIME, factModel.endTime());

        final String where = FactContract.FactEntry._ID + " = ?";
        final String[] whereArgs = {String.valueOf(factModel.id())};

        getWritableDatabase().update(FactContract.FactEntry.TABLE_NAME, values, where, whereArgs);

    }

    @Nullable
    @Override
    public FactModel findFact(long factId) {

        FactModel returnFactModel = null;

        final String where = FactContract.FactEntry._ID + " = ?";
        final String[] whereArgs = {String.valueOf(factId)};
        final Cursor cursor = getReadableDatabase()
                .query(FactContract.FactEntry.TABLE_NAME, null, where, whereArgs, null, null, null);
        while (cursor.moveToNext()) {


            returnFactModel = FactModel.builder()
                    .setId(cursor.getInt(0))
                    .setStartTime(cursor.getLong(1))
                    .setTitle(cursor.getString(2))
                    .setComment(cursor.getString(3))
                    .setEndTime(cursor.getLong(4))
                    .build();
        }
        cursor.close();
        return returnFactModel;
    }

    @NonNull
    @Override
    public List<FactModel> fetchAll() {
        final Cursor cursor = getReadableDatabase()
                .rawQuery(FactContract.FactEntry.FIND_ALL, null);

        final List<FactModel> factModels = new ArrayList<>(cursor.getCount());

        while (cursor.moveToNext()) {
            factModels.add(
                    FactModel.builder()
                            .setId(cursor.getInt(0))
                            .setStartTime(cursor.getLong(1))
                            .setTitle(cursor.getString(2))
                            .setComment(cursor.getString(3))
                            .setEndTime(cursor.getLong(4))
                            .build());
        }
        cursor.close();
        return factModels;
    }

    @Override
    public void deleteFact(FactModel factModel) {
        final String selection = FactContract.FactEntry._ID + " = ?";
        final String[] selectionArgs = {String.valueOf(factModel.id())};
        getWritableDatabase().delete(FactContract.FactEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void deleteTable() {
        getReadableDatabase().delete(FactContract.FactEntry.TABLE_NAME, null, null);
    }
}
