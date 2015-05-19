package com.easy.gpessoal;


import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.models.Compromissos;
import com.easy.gpessoal.utils.DateTime;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalhesCompromissosActivity extends AppCompatActivity {

	private int id_Compromisso;
	private TextView txTitulo, txDataInicio, txDataTermino, txDescricao, txParticipantes;
	private Compromissos comp;
	private DAOCompromissos dComp;
	private DateFormat dtf = DateFormat.getDateInstance(DateFormat.LONG, new Locale("pt", "BR"));
	private Date dtInicio, dtTermino;
	
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
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		if(comp != null)
		{	
			SimpleDateFormat dtParse = new SimpleDateFormat();
			
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
				txDataInicio.setText(dtf.format(dtInicio).toString()+" - "+dtf.format(dtTermino).toString() );
			else
				txDataInicio.setText(dtf.format(dtInicio).toString());
			
			txDescricao.setText(comp.getDescricao());
			txParticipantes.setText(comp.getParticipantes());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhes_compromissos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
