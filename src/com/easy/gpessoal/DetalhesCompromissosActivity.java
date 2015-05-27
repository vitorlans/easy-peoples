package com.easy.gpessoal;


import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.models.Compromissos;
import com.easy.gpessoal.utils.DateTime;
import com.melnykov.fab.FloatingActionButton;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DetalhesCompromissosActivity extends AppCompatActivity {

	private int id_Compromisso;
	private TextView txTitulo, txDataInicio, txDataTermino, txDescricao, txParticipantes;
	private Compromissos comp;
	private DAOCompromissos dComp;
	private Date dtInicio, dtTermino;
	private FloatingActionButton btEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes_compromissos);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		Intent intCompromisso = getIntent();
		id_Compromisso = intCompromisso.getIntExtra("idComp", 0);
		
		dComp = new DAOCompromissos(DetalhesCompromissosActivity.this);
		comp = dComp.RecuperarCompromisso(id_Compromisso);
		
	    txTitulo = (TextView)findViewById(R.id.dcomp_titulo_tv);
	    txDataInicio = (TextView)findViewById(R.id.dt_inicio);
	    txDescricao = (TextView)findViewById(R.id.descricao_comp);
	    txParticipantes = (TextView)findViewById(R.id.relac_comp);
	    btEdit = (FloatingActionButton)findViewById(R.id.detalhes_contatos_edit_fab);
	    
	    btEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent n = new Intent(DetalhesCompromissosActivity.this, EditarCompromissoActivity.class);
				n.putExtra("idComp", comp.getId());
				startActivity(n);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Intent intCompromisso = getIntent();
		id_Compromisso = intCompromisso.getIntExtra("idComp", 0);
		
		dComp = new DAOCompromissos(DetalhesCompromissosActivity.this);
		comp = dComp.RecuperarCompromisso(id_Compromisso);
		
		if(comp != null)
		{	
			SimpleDateFormat dtParse = new SimpleDateFormat();
		    SimpleDateFormat teste = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy HH:mm", Locale.getDefault());
			
		    try 
		    {
		    	dtInicio = dtParse.parse(comp.getDataInicio());
		    	dtTermino = dtParse.parse(comp.getDataFim());
			} 
		    catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			
			txTitulo.setText(comp.getTitulo());
			
			if(comp.getDataInicio() != comp.getDataFim())
				txDataInicio.setText(teste.format(dtInicio).toString()+"\n"+teste.format(dtTermino).toString() );
			else
				txDataInicio.setText(teste.format(dtInicio).toString());
			
			txDescricao.setText(comp.getDescricao());
			txParticipantes.setText(comp.getParticipantes());
		}
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		
		
		if(comp != null)
		{	
			SimpleDateFormat dtParse = new SimpleDateFormat();
		    SimpleDateFormat teste = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy HH:mm", Locale.getDefault());
			
		    try 
		    {
		    	dtInicio = dtParse.parse(comp.getDataInicio());
		    	dtTermino = dtParse.parse(comp.getDataFim());
			} 
		    catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			
			txTitulo.setText(comp.getTitulo());
			
			if(comp.getDataInicio() != comp.getDataFim())
				txDataInicio.setText(teste.format(dtInicio).toString()+"\n"+teste.format(dtTermino).toString() );
			else
				txDataInicio.setText(teste.format(dtInicio).toString());
			
			txDescricao.setText(comp.getDescricao());
			txParticipantes.setText(comp.getParticipantes());
		}
		
	}
}
