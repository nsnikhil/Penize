package com.nrs.nsnik.penize.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class TableNames {

    public static final String mDatabaseName = "moneyDb";
    public static final int mDatabaseVersion  = 1;

    public static final String mExpenditureTable = "expenditureTable";
    public static final String mSavingsTable = "savingsTable";
    public static final String mIncomeTable = "incomeTable";

    public static final String mScheme = "content://";
    public static final String mAuthority = "com.nrs.nsnik.penize";

    public static final Uri mBaseUri = Uri.parse(mScheme+mAuthority);

    public static final Uri mExpenditureUri = Uri.withAppendedPath(mBaseUri,mExpenditureTable);
    public static final Uri mSavingsUri = Uri.withAppendedPath(mBaseUri,mSavingsTable);
    public static final Uri mIncomeUri = Uri.withAppendedPath(mBaseUri,mIncomeTable);

    class expenditureTable implements BaseColumns{
        public static final String mID  = BaseColumns._ID;
        public static final String mExpAmount = "expenditureAmount";
        public static final String mExpDate = "expenditureDate";
    }

    class savingsTable implements BaseColumns{
        public static final String mID  = BaseColumns._ID;
        public static final String mSavAmount = "savingAmount";
        public static final String mSavDate = "savingDate";
    }

    class incomeTable implements BaseColumns{
        public static final String mID  = BaseColumns._ID;
        public static final String mIncAmount = "incomeAmount";
        public static final String mIncDate = "incomeDate";
    }

}
