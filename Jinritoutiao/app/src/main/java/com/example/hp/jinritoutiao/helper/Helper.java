package com.example.hp.jinritoutiao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/16 19 55
 */

public class Helper extends SQLiteOpenHelper {
    public Helper(Context context) {
        super(context, "news", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists xinwens(id integer primary key autoincrement,path text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
