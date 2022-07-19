package com.example.baseproject;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;

public class CustomContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.baseproject";

    public static final String PATH_ACCOUNT = "ACCOUNT";

    public static final Uri CONTENT_URI_ACCOUNT = Uri.parse(
            "content://" + AUTHORITY + "/" + PATH_ACCOUNT
    );

    //get account
    public static final int MANAGE_ACCOUNT = 1;

    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        URI_MATCHER.addURI(AUTHORITY, PATH_ACCOUNT, MANAGE_ACCOUNT);
    }

    public static final String CONTENT_TYPE_ACCOUNT = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + PATH_ACCOUNT;

    String a = "nong dep trai";

    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("ptit", "query: ");
        switch (URI_MATCHER.match(uri)) {
            case MANAGE_ACCOUNT: {
                String[] columns = new String[]{"userName"};
                MatrixCursor matrixCursor = new MatrixCursor(columns);
                matrixCursor.addRow(new Object[]{a});
                return matrixCursor;
            }
        }
        return null;
    }

    //Uri = Uri.parse("content://com.example.baseproject/ACCOUNT)")

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
//        switch (URI_MATCHER.match(uri)) {
//            case MANAGE_ACCOUNT: return CONTENT_TYPE_ACCOUNT;
//        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
