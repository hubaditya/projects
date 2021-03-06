package com.example.infinia.udhaar;

import android.database.sqlite.*;
import android.content.*;
import android.database.*;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FinanceManager";
    private static final String TABLE_FINANCE = "Finance";
    private SQLiteDatabase db;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String LOCATION = "location";
    private static final String DEBIT = "debit";
    private static final String CREDIT="credit";

    public DatabaseHandler(Context context)
    { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FINANCE + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + DATE + " TEXT," + LOCATION + " TEXT," + DEBIT + " INTEGER," + CREDIT + " INTEGER " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINANCE);
        onCreate(db);
    }

    public long addDetails(Details detail)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, detail.getName());
        values.put(DATE, detail.getDate());
        values.put(LOCATION, detail.getLocation());
        values.put(DEBIT, detail.getDebit());
        values.put(CREDIT, detail.getCredit());
        long rowID=db.insert(TABLE_FINANCE, null, values);
        db.close();
        return rowID;
    }

    public Cursor getDetails(String name)
    {
        db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_FINANCE + " WHERE " + NAME + " = '" +name+"'";
        //Cursor c = db.query(TABLE_FINANCE,null,NAME + "='"+name+"'",null,null, null,name);
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }

    public Cursor getAllDetails()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_FINANCE;
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);//rawQuery() used when no structure has to be defined unlike query()
        return c;
    }

	/*public void updateDetail(String id, String amount)
	{
		db = this.getWritableDatabase();
		db.rawQuery("UPDATE "+ TABLE_FINANCE + " SET " + AMOUNT + " = " + amount + " WHERE " + NAME + " = " + name, null);
	}*/

    public boolean deleteDetails(String name)
    {
        int count=0;
        db = this.getWritableDatabase();
        count=db.delete(TABLE_FINANCE, NAME + "='"+name+"'",null);
        db.close();
        if(count>0)
            return true;
        else return false;
    }
}



