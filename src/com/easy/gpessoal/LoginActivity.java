package com.easy.gpessoal;

import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
	
	private EditText email;
	private EditText senha;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email = (EditText)findViewById(R.id.email);
		senha = (EditText)findViewById(R.id.password);
		final Button botaoLogar = (Button)findViewById(R.id.entrar_bt);
		botaoLogar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email2 = email.getText().toString();
				String senha2 = senha.getText().toString();
				DAOUsuarios dUser = new DAOUsuarios(LoginActivity.this);
				if(dUser.RealizarLogin(email2, senha2) == true){
					Intent intent = new Intent(LoginActivity.this,EmpresaActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(LoginActivity.this, "Login ou senha inválida", Toast.LENGTH_SHORT).show();
				}
					
			}
		});
		
	}
}