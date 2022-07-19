package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.baseproject/ACCOUNT"),
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
            Log.d("ptit", "onCreate: " + userName);
        }
    }
}