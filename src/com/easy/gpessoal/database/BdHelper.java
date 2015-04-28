package com.easy.gpessoal.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdHelper extends SQLiteOpenHelper {
	private static final String NOME_BD = "projetopi";
	private static final int VERSAO_BD = 1;
	
	private Context mContext;

	public BdHelper(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
		mContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		
        createTables(bd);

	}

 
private void createTables(SQLiteDatabase db) {
		AssetManager manager = mContext.getAssets();
 
		InputStream inputStream = null;
		BufferedReader reader = null;
 
		try {
			inputStream = manager.open("projetopi.sql");
 
			reader = new BufferedReader(new InputStreamReader(inputStream));
 
			StringBuilder stringBuilder = new StringBuilder();
 
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
 
			String[] sqls = stringBuilder.toString().split(";");
 
			for (String sql : sqls) {
				db.execSQL(sql);
			}
 
		} catch (IOException e) {
			try {
				throw e;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
 
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				try {
					throw e;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
