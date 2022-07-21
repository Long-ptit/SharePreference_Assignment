package com.example.baseproject;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SharePreManager {
    public static SharedPreferences getEncryptedSharedPreferences(Context context, String fileName)
            throws GeneralSecurityException, IOException {
        MasterKey masterKeyAlias = new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
        SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                context,
                fileName,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        return sharedPreferences;
    }
}
