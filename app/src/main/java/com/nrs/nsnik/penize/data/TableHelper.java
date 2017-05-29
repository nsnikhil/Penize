package com.nrs.nsnik.penize.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nrs.nsnik.penize.data.TableNames.expenditureTable;
import com.nrs.nsnik.penize.data.TableNames.savingsTable;
import com.nrs.nsnik.penize.data.TableNames.incomeTable;

public class TableHelper extends SQLiteOpenHelper{

    private static final String mCreateExpTable = "CREATE TABLE " + TableNames.mExpenditureTable + " ("
            + expenditureTable.mID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + expenditureTable.mExpAmount + " INTEGER DEFAULT 0 NOT NULL, "
            + expenditureTable.mExpDate + " TEXT NOT NULL "
            +");";

    private static final String mCreateSavTable = "CREATE TABLE " + TableNames.mSavingsTable + " ("
            + savingsTable.mID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + savingsTable.mSavAmount + " INTEGER DEFAULT 0 NOT NULL, "
            + savingsTable.mSavDate + " TEXT NOT NULL "
            +");";

    private static final String mCreateIncTable = "CREATE TABLE " + TableNames.mIncomeTable + " ("
            + incomeTable.mID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + incomeTable.mIncAmount + " INTEGER DEFAULT 0 NOT NULL, "
            + incomeTable.mIncDate + " TEXT NOT NULL "
            +");";

    private static final String mDropExpTable = "DROP TABLE IF EXISTS " + TableNames.mExpenditureTable;

    private static final String mDropSavTable = "DROP TABLE IF EXISTS " + TableNames.mSavingsTable;

    private static final String mDropIncTable = "DROP TABLE IF EXISTS " + TableNames.mIncomeTable;

    public TableHelper(Context context) {
        super(context, TableNames.mDatabaseName, null, TableNames.mDatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase sdb){
        sdb.execSQL(mCreateExpTable);
        sdb.execSQL(mCreateSavTable);
        sdb.execSQL(mCreateIncTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(mDropExpTable);
        db.execSQL(mDropSavTable);
        db.execSQL(mDropIncTable);
        createTables(db);
    }
}
