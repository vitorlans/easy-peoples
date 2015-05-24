package com.easy.gpessoal;

import java.util.UUID;

import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Usuarios;

import android.R.integer;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.IntToString;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesContatosActivity extends AppCompatActivity {

	private ImageView ivImagem;
	private TextView tvNome;
	private TextView tvEmail;
	private TextView tvTelefone;
	private TextView tvDNasc;
	private TextView tvEndereco;
	private TextView tvApelido;
	private Usuarios u;
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
		tvEndereco = (TextView) findViewById(R.id.detalhes_endereco_tv);
		tvApelido = (TextView) findViewById(R.id.detalhes_apelido_tv);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		DAOUsuarios dUs = new DAOUsuarios(DetalhesContatosActivity.this);
		u = dUs.RecuperarUsuario(_id);
		getSupportActionBar().setTitle(u.getNome());
		ivImagem.setBackgroundColor(Color.parseColor(u.getImagem()));
		tvNome.setText(u.getNome() + " " + u.getSobrenome());
		tvEmail.setText(u.getEmail());
		tvTelefone.setText(u.getTelefone());
		tvDNasc.setText(u.getDtNascimento());
		tvEndereco.setText(u.getEndereco()+ u.getBairro() + u.getCidade() + u.getCep());
		tvApelido.setText(u.getApelido());
		
		// Alterações realizadas para chamadas de Aplicativos externos;
		
		tvEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try
				{
					Intent iEmail = new Intent(Intent.ACTION_SENDTO);
					
					iEmail.setType("pain/text");
					iEmail.setData(Uri.parse("mailto:"+u.getEmail().toString().trim()+""));
					//iEmail.putExtra(Intent.EXTRA_SUBJECT, "Email Easy");
					
					startActivity(iEmail);
				}catch(Exception e)
				{
					int tempo =  Toast.LENGTH_SHORT;
					Toast tMsg = Toast.makeText(getApplicationContext(), "Não foi possível abrir o aplicativo de Email.", tempo);
					tMsg.show();
				}
			}
		});
		
		tvTelefone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String te = tvTelefone.getText().toString();
				
				Uri ur = Uri.parse("tel:"+te);
				Intent intTel = new Intent(Intent.ACTION_DIAL, ur);
				
				startActivity(intTel);
				
			}
		});
		
		tvEndereco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String endereco, cidade;
				
				endereco = u.getEndereco().toString();
				cidade = u.getCidade().toString();
				
				try
				{					
					Uri mapteste = Uri.parse("google.navigation:q="+endereco.replace(' ', '+')+",+"+cidade.replace(' ', '+')+",+Brasil");
					
					Intent intentEnd = new Intent(Intent.ACTION_VIEW, mapteste);
					intentEnd.setPackage("com.google.android.apps.maps");
					
					startActivity(intentEnd);
					
				}catch(Exception e)
				{
					Uri sMaps = Uri.parse("https://www.google.com.br/maps/place/"+endereco.replace(' ', '+')+",+"+cidade.replace(' ', '+')+",+Brasil");
					
					Intent intendMaps = new Intent(Intent.ACTION_VIEW, sMaps);
					
					startActivity(intendMaps);
				}
				
				
			}
		});
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
