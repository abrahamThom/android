package com.example.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase(); // Use readable database for reading
        return db.rawQuery("SELECT * FROM USERS", null);
    }

    public Boolean insertdata(String userVal, Integer passVal, String surnameVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", userVal);
        contentValues.put("SURNAME", surnameVal);
        contentValues.put("MARKS", passVal);
        long result = db.insert("USERS", null, contentValues);
        db.close(); // Close the database after the operation
        return result != -1; // Return true if insertion was successful
    }

    public int deletedata(String userVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("USERS", "NAME=?", new String[]{userVal});
    }

    public Boolean updateData(String userVal, Integer passVal, String surnameVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", userVal);
        contentValues.put("SURNAME", surnameVal);
        contentValues.put("MARKS", passVal);
        int result = db.update("USERS", contentValues, "NAME=?", new String[]{userVal});
        return result > 0; // Return true if any row was updated
    }
}
