package com.easy.gpessoal.database;

import java.util.ArrayList;
import java.util.List;

import com.easy.gpessoal.models.Compromissos;
import com.easy.gpessoal.models.Empresas;
import com.easy.gpessoal.models.Usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAOCompromissos {

	private SQLiteDatabase bd;
	private List<Compromissos> lc;
	
	private static final String NOME_TABELA = "tbcompromissos";
	private static final String[] NOME_COLUNAS = new String[] { "_id", "titulo", "descricao",
			"dtinicio", "dtfim", "participantes", "status", "iduser",
			"idempr" };
	

	public DAOCompromissos(Context context) {

		BdHelper auxBd = new BdHelper(context);
		bd = auxBd.getWritableDatabase();

	}

	public List<Compromissos> RecuperarTodos() {

		lc = new ArrayList<>();


		Cursor cursor = bd.query(NOME_TABELA, NOME_COLUNAS, null, null, null,
				null, "titulo ASC");

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			do {

				Compromissos c = new Compromissos();
				c.setId(cursor.getInt(0));
				c.setTitulo(cursor.getString(1));
				c.setDescricao(cursor.getString(2));
				c.setDataInicio(cursor.getString(3));
				c.setDataFim(cursor.getString(4));
				c.setParticipantes(cursor.getString(5));
				c.setStatus(cursor.getString(6));
				c.setIdUser(cursor.getInt(7));
				c.setIdEmpr(cursor.getInt(8));

				lc.add(c);

			} while (cursor.moveToNext());
		}
		cursor.close();

		return lc;

	}

	public Compromissos RecuperarCompromisso(Integer _id){
		

		Cursor cursor = bd.query(NOME_TABELA, NOME_COLUNAS, "_id="+_id, null, null,
				null, "titulo ASC");

		Compromissos c = new Compromissos();

		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{

				c.setId(cursor.getInt(0));
				c.setTitulo(cursor.getString(1));
				c.setDescricao(cursor.getString(2));
				c.setDataInicio(cursor.getString(3));
				c.setDataFim(cursor.getString(4));
				c.setParticipantes(cursor.getString(5));
				c.setStatus(cursor.getString(6));
				c.setIdUser(cursor.getInt(7));
				c.setIdEmpr(cursor.getInt(8));
			}while(cursor.moveToNext());
			
		}
		
		cursor.close();
		
		return c;
		
		
	}
	
	public Boolean CriarCompromisso(Compromissos c) {

		ContentValues insert = new ContentValues();
		insert.put("titulo", c.getTitulo());
		insert.put("descricao", c.getDescricao());
		insert.put("dtinicio", c.getDataInicio());
		insert.put("dtfim", c.getDataFim());
		insert.put("participantes", c.getParticipantes());
		insert.put("status", c.getStatus());
		insert.put("iduser", c.getIdUser());
		insert.put("idempr", c.getIdEmpr());

		try {
			bd.insert(NOME_TABELA, null, insert);
			return true;

		} catch (SQLException e) {
			return false;
		}

	}
}
