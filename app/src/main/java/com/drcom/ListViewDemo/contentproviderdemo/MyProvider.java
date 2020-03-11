package com.drcom.ListViewDemo.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {
    private MyProviderHelper helper;
    public static UriMatcher uriMatcher;
    public static final int  STUDENT_DIR =1 ;
    public static final int  STUDENT_ITEM =2 ;
    public static final int  COURSE_DIR =3;
    public static final int  COURSE_ITEM =4 ;
    public static final String AUTHORITY = "com.drcom.ListViewDemo.contentproviderdemo.MyProvider";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"student",STUDENT_DIR);
        uriMatcher.addURI(AUTHORITY,"student/#",STUDENT_ITEM);
        uriMatcher.addURI(AUTHORITY,"course",COURSE_DIR);
        uriMatcher.addURI(AUTHORITY,"course/#",COURSE_ITEM);
    }
    @Override
    public boolean onCreate() {
        helper = new MyProviderHelper(getContext(),"student.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        String id;
        SQLiteDatabase db = helper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case STUDENT_DIR:
                cursor = db.query("student", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case STUDENT_ITEM:
                id = uri.getPathSegments().get(1);
                cursor = db.query("student", projection, selection, selectionArgs, null, null, sortOrder);

                break;
            case COURSE_DIR:
                cursor = db.query("course", projection, selection, selectionArgs, null, null, sortOrder);

                break;

            case COURSE_ITEM:
                id = uri.getPathSegments().get(1);

                cursor = db.query("course", projection, selection, selectionArgs, null, null, sortOrder);

                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
                return "vnd.android.cursor.dir/vnd.com.drcom.ListViewDemo.contentproviderdemo.MyProvider.student";
            case STUDENT_ITEM:
                return "vnd.android.cursor.item/vnd.com.drcom.ListViewDemo.contentproviderdemo.MyProvider.student";
            case COURSE_DIR:
                return "vnd.android.cursor.dir/vnd.com.drcom.ListViewDemo.contentproviderdemo.MyProvider.course";
            case COURSE_ITEM:
                return "vnd.android.cursor.item/vnd.com.drcom.ListViewDemo.contentproviderdemo.MyProvider.course";
            default:
            return null;

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Uri uri_return = null;
        long id;
        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
            case STUDENT_ITEM:
                id = db.insert("student",null,values);
                uri_return = Uri.parse("content://"+AUTHORITY+"/student/"+id);
                break;
            case COURSE_DIR:
            case COURSE_ITEM:
                id = db.insert("course",null,values);
                uri_return = Uri.parse("content://"+AUTHORITY+"/course/"+id);
                break;
            default:
                break;
        }
        return uri_return;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int row =0 ;
        String id ;

        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
                row = db.delete("student",selection,selectionArgs);
                break;

            case STUDENT_ITEM:
                id = uri.getPathSegments().get(1);
                row = db.delete("student","id=?",new String[]{id});
                break;
            case COURSE_DIR:
                row = db.delete("course",selection,selectionArgs);
                break;

            case COURSE_ITEM:
                id = uri.getPathSegments().get(1);
                row = db.delete("course","id=?",new String[]{id});
            default:
                break;
        }
        return row;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int row = 0 ;
        String id ;

        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
                row = db.update("student",values,selection,selectionArgs);
                break;

            case STUDENT_ITEM:
                id = uri.getPathSegments().get(1);
                row = db.update("student",values,"id=?",new String[]{id});
                break;
            case COURSE_DIR:
                row = db.update("course",values,selection,selectionArgs);
                break;

            case COURSE_ITEM:
                id = uri.getPathSegments().get(1);
                row = db.update("course",values,"id=?",new String[]{id});
            default:
                break;
        }
        return row;
    }
}
