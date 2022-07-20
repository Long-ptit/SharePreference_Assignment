package com.example.baseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.model.Account;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ptit";
    private ActivityMainBinding mBinding;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "getEncryptedSharedPreferences122: ");
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
                SharedPreferences.Editor editor = getEncryptedSharedPreferences().edit();
                editor.putString("data", "dep trai");
                editor.putString(Constants.KEY_WEBSITE, mBinding.edtWebsite.getText().toString());
                editor.putString(Constants.KEY_USERNAME, mBinding.edtUsername.getText().toString());
                editor.putString(Constants.KEY_EMAIL, mBinding.edtEmail.getText().toString());
                editor.putString(Constants.KEY_ADDRESS, mBinding.edtAddress.getText().toString());
                editor.apply();
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    public SharedPreferences getEncryptedSharedPreferences() throws GeneralSecurityException, IOException {
        MasterKey masterKeyAlias = new MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();

        Log.d(TAG, "getEncryptedSharedPreferences: " + masterKeyAlias);
        SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                this,
                Constants.FILE_NAME,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
        return sharedPreferences;
    }
}