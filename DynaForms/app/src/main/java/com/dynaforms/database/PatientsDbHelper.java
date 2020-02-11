package com.dynaforms.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PatientsDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "patients.db";
    private static final String TABLE_CATEGORY = "stract";
    public static final String PATIENT_NAME = "PATIENT_NAME";
    public static final String PASSWORD = "PASSWORD";


    public PatientsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PatientsDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_CATEGORY +
                "(" + PATIENT_NAME + " TEXT ,"
                + PASSWORD + " TEXT)";
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);

    }

    public void addPatientsList(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CATEGORY, null, contentValues);
        db.close();
    }

    public List<String> getPatientName() {
        List<String> patient_name = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } finally {
            if (cursor.moveToFirst()) {
                do {
                    patient_name.add(cursor.getString(0));

                } while (cursor.moveToNext());
            }
        }
        return patient_name;
    }

    public List<String> getPassword() {
        List<String> password = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } finally {
            if (cursor.moveToFirst()) {
                do {
                    password.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
        return password;
    }



    public void deleteCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORY);
        db.close();
    }

    public void emptyDBBucket() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORY); //delete all rows in a table
        db.close();
    }

    public Cursor retrieve(String searchTerm) {
        String[] columns = {PATIENT_NAME};
        Cursor c = null;
        SQLiteDatabase db = this.getWritableDatabase();
        if (searchTerm != null && searchTerm.length() > 0) {
            String sql = "SELECT * FROM " + TABLE_CATEGORY + " WHERE " + PATIENT_NAME + " LIKE '%" + searchTerm + "%'";
            c = db.rawQuery(sql, null);
            return c;
        }
        c = db.query(TABLE_CATEGORY, columns, null, null, null, null, null);
        return c;
    }

    public void openDB() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
