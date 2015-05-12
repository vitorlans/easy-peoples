package com.easy.gpessoal;

import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Usuarios;

import android.R.integer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalhesContatosActivity extends AppCompatActivity {

	private ImageView ivImagem;
	private TextView tvNome;
	private TextView tvEmail;
	private TextView tvTelefone;
	private TextView tvDNasc;
	private TextView tvEndereço;
	private TextView tvApelido;
	Integer _id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes_contatos);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		Intent extras = getIntent();
		_id = extras.getIntExtra("_id", 0);		
		
		ivImagem = (ImageView) findViewById(R.id.detalhes_img_iv);
		tvNome = (TextView) findViewById(R.id.detalhes_nome_tv);
		tvEmail = (TextView) findViewById(R.id.detalhes_email_tv);
		tvTelefone = (TextView) findViewById(R.id.detalhes_telefone_tv);
		tvDNasc = (TextView) findViewById(R.id.detalhes_dtnasc_tv);
		tvEndereço = (TextView) findViewById(R.id.detalhes_endereco_tv);
		tvApelido = (TextView) findViewById(R.id.detalhes_apelido_tv);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		DAOUsuarios dUs = new DAOUsuarios(DetalhesContatosActivity.this);
		Usuarios u = dUs.RecuperarUsuario(_id);
		getSupportActionBar().setTitle(u.getNome());
		ivImagem.setBackgroundColor(Color.parseColor(u.getImagem()));
		tvNome.setText(u.getNome() + " " + u.getSobrenome());
		tvEmail.setText(u.getEmail());
		tvTelefone.setText(u.getTelefone());
		tvDNasc.setText(u.getDtNascimento());
		tvEndereço.setText(u.getEndereco()+ u.getBairro() + u.getCidade() + u.getCep());
		tvApelido.setText(u.getApelido());
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        finish();
	        return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
}
