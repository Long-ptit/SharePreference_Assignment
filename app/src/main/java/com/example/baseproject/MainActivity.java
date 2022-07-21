package com.example.baseproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.baseproject.databinding.ActivityMainBinding;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mBinding;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btnSave.setOnClickListener(v -> {
            handleSaveData();
        });

    }

    private void handleSaveData() {
        if (TextUtils.isEmpty(mBinding.edtWebsite.getText().toString()) ||
                TextUtils.isEmpty(mBinding.edtUsername.getText().toString()) ||
                TextUtils.isEmpty(mBinding.edtEmail.getText().toString()) ||
                TextUtils.isEmpty(mBinding.edtAddress.getText().toString())) {
            Toast.makeText(this, "You should fill full in form", Toast.LENGTH_SHORT).show();
        } else {
            try {
                SharedPreferences.Editor editor = SharePreManager
                        .getEncryptedSharedPreferences(this, Constants.FILE_NAME).edit();

                editor.putString(Constants.KEY_WEBSITE, mBinding.edtWebsite.getText().toString());
                editor.putString(Constants.KEY_USERNAME, mBinding.edtUsername.getText().toString());
                editor.putString(Constants.KEY_EMAIL, mBinding.edtEmail.getText().toString());
                editor.putString(Constants.KEY_ADDRESS, mBinding.edtAddress.getText().toString());
                editor.apply();
                Toast.makeText(this, "Saved data successfull", Toast.LENGTH_SHORT).show();
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something was wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}