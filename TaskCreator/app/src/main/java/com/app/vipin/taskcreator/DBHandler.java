package com.app.vipin.taskcreator;

/**
 * Created by vipin on 8/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Tasks.db";
    public static final String TABLE_NAME = "Task";
    public static final String TASK_TITLE = "title";
    public static final String TASK_DETAIL = "detail";
    public static final String TASK_ID = "id";
    public static final String TASK_STATUS = "status";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String query = "create table " +
                TABLE_NAME + "(" +
                TASK_ID + " integer primary key, "+
                TASK_TITLE + " text, "+
                TASK_DETAIL + " text, "+
                TASK_STATUS + " integer "+ ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TASK_TITLE,task.getTitle());
        contentValues.put(TASK_DETAIL,task.getDetail());
        contentValues.put(TASK_STATUS,task.getStatus());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public ArrayList<Task> getTasks()
    {
        ArrayList<Task> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from "+TABLE_NAME, null );
        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setTitle(cursor.getString(1));
                task.setDetail(cursor.getString(2));
                task.setStatus(cursor.getInt(3));

                arrayList.add(task);
            }
            while (cursor.moveToNext());
        }
        return arrayList;
    }
}
