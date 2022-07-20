package com.example.baseproject;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.baseproject.model.Account;

import java.io.IOException;
import java.security.GeneralSecurityException;
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

    private Account mAccount;
    private SharedPreferences mSharePre;

    @Override
    public boolean onCreate() {
        try {
            mSharePre = getEncryptedSharedPreferences();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (URI_MATCHER.match(uri)) {
            case MANAGE_ACCOUNT: {
                return handleGetData();
            }
        }
        return null;
    }

    private Cursor handleGetData() {
        getData();
        String[] columns = new String[]{
                Constants.KEY_WEBSITE,
                Constants.KEY_USERNAME,
                Constants.KEY_EMAIL,
                Constants.KEY_ADDRESS,
        };
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        matrixCursor.addRow(new Object[]{
                mAccount.getWebsite(),
                mAccount.getUserName(),
                mAccount.getEmail(),
                mAccount.getAddress()
        });
        return matrixCursor;
    }

    private void getData() {
        mAccount = new Account(
                mSharePre.getString(Constants.KEY_WEBSITE, Constants.DEFAULT_VALUE_STRING),
                mSharePre.getString(Constants.KEY_USERNAME, Constants.DEFAULT_VALUE_STRING),
                mSharePre.getString(Constants.KEY_EMAIL, Constants.DEFAULT_VALUE_STRING),
                mSharePre.getString(Constants.KEY_ADDRESS, Constants.DEFAULT_VALUE_STRING)
        );

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
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

    public SharedPreferences getEncryptedSharedPreferences() throws GeneralSecurityException, IOException {
        MasterKey masterKeyAlias = new MasterKey.Builder(getContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
        SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                getContext(),
                Constants.FILE_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        return sharedPreferences;
    }
}
