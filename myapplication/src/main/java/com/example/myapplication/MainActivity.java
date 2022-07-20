package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Cursor cursor = getContentResolver().query(Uri.parse(Constants.URI_ACCOUNT),
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            String website = cursor.getString(0);
            String userName = cursor.getString(1);
            String email = cursor.getString(2);
            String address = cursor.getString(3);
            String result = " UserName: "
                    + userName
                    + "\n Website: "
                    + website
                    + "\n Email: "
                    + email + "\n Address: "
                    + address;
            mTextViewInfor.setText(result);
        }
    }

    private void initView() {
        mTextViewInfor = findViewById(R.id.tv_infor);
    }
}