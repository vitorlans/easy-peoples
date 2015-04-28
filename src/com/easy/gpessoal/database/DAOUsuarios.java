package com.easy.gpessoal.database;

import java.util.ArrayList;
import java.util.List;

import com.easy.gpessoal.models.Empresas;
import com.easy.gpessoal.models.Usuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOUsuarios {

	private SQLiteDatabase bd;
	private List<Usuarios> lu;

	public DAOUsuarios(Context context) {

		BdHelper auxBd = new BdHelper(context);
		bd = auxBd.getWritableDatabase();

	}
	
	public List<Usuarios> RecuperarTodos(){
		
		
		lu = new ArrayList<>();
		
		String[] colunas = new String[]{"_id", "nome", "sobrenome", "apelido", "email", "senha", "dtnascimento", "endereco", "bairro", "cidade", "cep", "telefone", "status", "dtcriacao", "imagem" };
		
		Cursor cursor = bd.query("tbusuarios", colunas, null, null, null, null, "nome ASC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{
				
				Usuarios u = new Usuarios();
				u.setId(cursor.getInt(0));
				u.setNome(cursor.getString(1));
				u.setSobrenome(cursor.getString(2));
				u.setApelido(cursor.getString(3));
				u.setEmail(cursor.getString(4));
				u.setSenha(cursor.getString(5));
				u.setDtNascimento(cursor.getString(6));
				u.setEndereco(cursor.getString(7));
				u.setBairro(cursor.getString(8));
				u.setCidade(cursor.getString(9));
				u.setCep(cursor.getString(10));
				u.setTelefone(cursor.getString(11));
				u.setStatus(cursor.getString(12));
				u.setDtCriacao(cursor.getString(13));
				u.setImagem(cursor.getString(14));


				lu.add(u);
				
			}while(cursor.moveToNext());
		}
		
		
		return lu;
		
	}
	
	public List<Empresas> RecEmpresasVinc(){
		
		return null;
		
	}
}
