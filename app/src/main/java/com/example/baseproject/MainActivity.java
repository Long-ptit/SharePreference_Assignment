package com.example.baseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "ptit";

	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			getEncryptedSharedPreferences().edit().putString("a", "hiih");
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		findViewById(R.id.tv_helo).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, MainActivity2.class));
			}
		});

	}


	public SharedPreferences getEncryptedSharedPreferences() throws GeneralSecurityException, IOException {
		String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
		Context mContext = null;
		//MasterKey masterKeyAlias = new MasterKey.Builder(mContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();

		Log.d(TAG, "getEncryptedSharedPreferences: " + masterKeyAlias);
		SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
				"secret_shared_prefs_file",
				masterKeyAlias,
				this,
				EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
				EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
		);
		return sharedPreferences;
	}
}