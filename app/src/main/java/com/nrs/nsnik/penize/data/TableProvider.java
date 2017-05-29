package com.nrs.nsnik.penize.data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TableProvider extends ContentProvider{

    private static final int uExpensesAll = 568;
    private static final int uExpensesSingle = 569;

    private static final int uSavingAll = 668;
    private static final int uSavingSingle = 669;

    private static final int uIncomeAll = 768;
    private static final int uIncomeSingle = 769;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mExpenditureTable,uExpensesAll);
        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mExpenditureTable+"/*",uExpensesSingle);

        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mSavingsTable,uSavingAll);
        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mSavingsTable+"/*",uSavingSingle);

        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mIncomeTable,uIncomeAll);
        sUriMatcher.addURI(TableNames.mAuthority,TableNames.mIncomeTable+"/*",uIncomeSingle);
    }

    private TableHelper mTableHelper;

    @Override
    public boolean onCreate() {
        mTableHelper = new TableHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor c;
        SQLiteDatabase sdb = mTableHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case uExpensesAll:
                c = sdb.query(TableNames.mExpenditureTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case uExpensesSingle:
                selection = TableNames.expenditureTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                c = sdb.query(TableNames.mExpenditureTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case uSavingAll:
                c = sdb.query(TableNames.mSavingsTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case uSavingSingle:
                selection = TableNames.savingsTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                c = sdb.query(TableNames.mSavingsTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case uIncomeAll:
                c = sdb.query(TableNames.mIncomeTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case uIncomeSingle:
                selection = TableNames.incomeTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                c = sdb.query(TableNames.mIncomeTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw  new IllegalArgumentException("Invalid uri : "+uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sUriMatcher.match(uri)){
            case uExpensesAll:
                return insertVal(uri,values,TableNames.mExpenditureTable);
            case uSavingAll:
                return insertVal(uri,values,TableNames.mSavingsTable);
            case uIncomeAll:
                return insertVal(uri,values,TableNames.mIncomeTable);
            default:
                throw  new IllegalArgumentException("Invalid uri : "+uri);
        }
    }

    private Uri insertVal(Uri u,ContentValues cv,String tableName){
        SQLiteDatabase sdb = mTableHelper.getWritableDatabase();
        long count = sdb.insert(tableName,null,cv);
        if(count>0){
            getContext().getContentResolver().notifyChange(u,null);
            return Uri.withAppendedPath(u,String.valueOf(count));
        }else {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)){
            case uExpensesAll:
                return deleteVal(uri,selection,selectionArgs,TableNames.mExpenditureTable);
            case uExpensesSingle:
                selection = TableNames.expenditureTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return deleteVal(uri,selection,selectionArgs,TableNames.mExpenditureTable);

            case uSavingAll:
                return deleteVal(uri,selection,selectionArgs,TableNames.mSavingsTable);
            case uSavingSingle:
                selection = TableNames.savingsTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return deleteVal(uri,selection,selectionArgs,TableNames.mSavingsTable);

            case uIncomeAll:
                return deleteVal(uri,selection,selectionArgs,TableNames.mIncomeTable);
            case uIncomeSingle:
                selection = TableNames.incomeTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return deleteVal(uri,selection,selectionArgs,TableNames.mIncomeTable);
            default:
                throw  new IllegalArgumentException("Invalid uri : "+uri);
        }
    }

    private int deleteVal(Uri u,String sel,String[] selArgs,String tableName){
        SQLiteDatabase sdb = mTableHelper.getWritableDatabase();
        int count = sdb.delete(tableName,sel,selArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(u,null);
            return count;
        }else {
            return 0;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)){
            case uExpensesAll:
                return updateVal(uri,values,selection,selectionArgs,TableNames.mExpenditureTable);
            case uExpensesSingle:
                selection = TableNames.expenditureTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVal(uri,values,selection,selectionArgs,TableNames.mExpenditureTable);

            case uSavingAll:
                return updateVal(uri,values,selection,selectionArgs,TableNames.mSavingsTable);
            case uSavingSingle:
                selection = TableNames.savingsTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVal(uri,values,selection,selectionArgs,TableNames.mSavingsTable);

            case uIncomeAll:
                return updateVal(uri,values,selection,selectionArgs,TableNames.mIncomeTable);
            case uIncomeSingle:
                selection = TableNames.incomeTable.mID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateVal(uri,values,selection,selectionArgs,TableNames.mIncomeTable);
            default:
                throw  new IllegalArgumentException("Invalid uri : "+uri);
        }
    }

    private int updateVal(Uri u,ContentValues cv,String sel,String[] selArgs,String tableName){
        SQLiteDatabase sdb = mTableHelper.getWritableDatabase();
        int count = sdb.update(tableName,cv,sel,selArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(u,null);
            return count;
        }else {
            return 0;
        }
    }
}
