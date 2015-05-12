package com.easy.gpessoal;

import java.util.ArrayList;
import java.util.List;

import com.easy.gpessoal.database.DAOEmpresa;
import com.easy.gpessoal.models.Empresas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EmpresaActivity extends AppCompatActivity{

	private Spinner spEmpresa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empresa);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		spEmpresa = (Spinner) findViewById(R.id.SpinnerEmpresa);
		DAOEmpresa dEmp = new DAOEmpresa(EmpresaActivity.this);
		List<Empresas> emp = dEmp.RecuperarTodos();
		List<String> sEmp= new ArrayList();
		
		for(int i =0;i<emp.size();i++)
		{
			sEmp.add(emp.get(i).getNome());
		}
		ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(EmpresaActivity.this, android.R.layout.simple_spinner_item, sEmp);
		spEmpresa.setAdapter(spAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.empresa, menu);
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
