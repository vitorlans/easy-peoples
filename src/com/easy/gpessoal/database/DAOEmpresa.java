package com.easy.gpessoal.database;

import java.util.ArrayList;
import java.util.List;

import com.easy.gpessoal.models.Empresas;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOEmpresa {

	private SQLiteDatabase bd;
	private List<Empresas> le;

	private static final String NOME_TABELA = "tbempresas";
	private static final String[] NOME_COLUNAS = new String[] { "_id",
			"empr_cnpj", "nome", "status" };

	public DAOEmpresa(Context context) {

		BdHelper auxBd = new BdHelper(context);
		bd = auxBd.getWritableDatabase();

	}

	public List<Empresas> RecuperarTodos() {

		le = new ArrayList<>();

		Cursor cursor = bd.query(NOME_TABELA, NOME_COLUNAS, null, null, null,
				null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				Empresas e = new Empresas();
				e.setId(cursor.getInt(0));
				e.setEmprCnpj(cursor.getString(1));
				e.setNome(cursor.getString(2));
				e.setStatus(cursor.getString(3));

				le.add(e);

			} while (cursor.moveToNext());
		}
		cursor.close();

		return le;

	}

	public Empresas RecuperarEmpresa(Integer _id) {

		Cursor cursor = bd.query(NOME_TABELA, NOME_COLUNAS, null, null, null,
				null, "titulo ASC");

		Empresas e = new Empresas();

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				e.setId(cursor.getInt(0));
				e.setEmprCnpj(cursor.getString(1));
				e.setNome(cursor.getString(2));
				e.setStatus(cursor.getString(3));
			} while (cursor.moveToNext());

		}

		cursor.close();

		return e;

	}
	

}
