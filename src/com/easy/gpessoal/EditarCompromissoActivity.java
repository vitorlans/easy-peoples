package com.easy.gpessoal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Compromissos;
import com.easy.gpessoal.models.Usuarios;
import com.easy.gpessoal.utils.DateTime;
import com.easy.gpessoal.utils.DateTimePicker;
import com.easy.gpessoal.utils.SimpleDateTimePicker;
import com.easy.gpessoal.views.ParticipantesCompletion;
import com.tokenautocomplete.FilteredArrayAdapter;
import com.tokenautocomplete.TokenCompleteTextView.TokenListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EditarCompromissoActivity extends AppCompatActivity implements
		DateTimePicker.OnDateTimeSetListener, TokenListener {

	private Integer df;
	private Date todayDate;

	ArrayAdapter<Usuarios> Au;
	List<Usuarios> u;
	List<String> addlu = new ArrayList<String>();

	private SimpleDateTimePicker simpleDateTimePicker;

	private int _id;
	private Compromissos comp;
	
	private TextView descricao;
	private TextView titulo;
	private ParticipantesCompletion participantes;
	private TextView dataInicio;
	private TextView dataFim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_compr);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		Intent intCompromisso = getIntent();
		_id = intCompromisso.getIntExtra("idComp", 0);
		
		DAOCompromissos dComp = new DAOCompromissos(EditarCompromissoActivity.this);
		comp = dComp.RecuperarCompromisso(_id);
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			d = sdf.parse(comp.getDataInicio());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		simpleDateTimePicker = SimpleDateTimePicker.make(
				"Selecione uma Data e Hora", d, this,
				getSupportFragmentManager());

		
		DAOUsuarios dU = new DAOUsuarios(this);
		u = dU.RecuperarSimplesTodos();

		titulo = (TextView) findViewById(R.id.novo_titulo_ed);
		descricao = (TextView) findViewById(R.id.novo_descric_et);

		dataInicio = (TextView) findViewById(R.id.novo_data_inicio_tv);
		dataFim = (TextView) findViewById(R.id.novo_data_fim_tv);

		Au = new FilteredArrayAdapter<Usuarios>(this, R.layout.partic_row, u) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				if (convertView == null) {

					LayoutInflater l = (LayoutInflater) getContext()
							.getSystemService(
									AppCompatActivity.LAYOUT_INFLATER_SERVICE);
					convertView = l.inflate(R.layout.partic_row, parent, false);
				}

				Usuarios u = getItem(position);
				((TextView) convertView.findViewById(R.id.name)).setText(u
						.getNome());
				((TextView) convertView.findViewById(R.id.email)).setText(u
						.getEmail());

				return convertView;
			}

			@Override
			protected boolean keepObject(Usuarios person, String mask) {
				mask = mask.toLowerCase();
				return person.getNome().toLowerCase().startsWith(mask)
						|| person.getEmail().toLowerCase().startsWith(mask);
			}

		};

		participantes = (ParticipantesCompletion) findViewById(R.id.novo_particip_ed);
		participantes.setAdapter(Au);
		char[] splitChar = { ',', ';', ' ' };
		participantes.setSplitChar(splitChar);
		participantes.setTokenListener(this);
		participantes.allowCollapse(false);
		participantes.allowDuplicates(false);

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onStart() {
		super.onStart();

		todayDate = Calendar.getInstance().getTime();
		String sInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				Locale.getDefault()).format(todayDate);

		todayDate.setHours(todayDate.getHours() + 1);
		String sFim = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				Locale.getDefault()).format(todayDate);

		dataInicio.setText(sInicio);
		dataInicio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				df = 1;
				simpleDateTimePicker.show();

			}
		});

		dataFim.setText(sFim);
		dataFim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				df = 2;
				simpleDateTimePicker.show();

			}
		});

		
		
		titulo.setText(comp.getTitulo().toString());
		descricao.setText(comp.getDescricao().toString());
		dataInicio.setText(comp.getDataInicio().toString());
		dataFim.setText(comp.getDataFim().toString());
		
		if(comp.getParticipantes() != null){
		String[] p = comp.getParticipantes().split("\n");
		for(int x = 0; x < p.length; x++){
			
			Usuarios ux = new Usuarios();
			ux.setEmail(p[x]);
			participantes.addObject(ux);
		}}
	}

	@Override
	public void DateTimeSet(Date date) {

		DateTime mDateTime = new DateTime(date);
		String d = mDateTime.getDateString("dd/MM/yyyy HH:mm");

		if (df.equals(1)) {

			Date dtf = null;
			String sFim = dataFim.getText().toString();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",
					Locale.getDefault());
			try {

				dtf = sdf.parse(sFim);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Date dti = date;

			if (dti.after(dtf)) {
				dataInicio.setText(d);
				Date d2 = date;
				d2.setHours(d2.getHours() + 1);
				String st = new SimpleDateFormat("dd/MM/yyyy HH:mm",
						Locale.getDefault()).format(d2);

				dataFim.setText(st);

			} else {

				dataInicio.setText(d);
			}

		} else {

			Date dti = null;
			String sIni = dataInicio.getText().toString();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",
					Locale.getDefault());
			try {

				dti = sdf.parse(sIni);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Date dtf = date;

			if (dti.after(dtf)) {
				Toast.makeText(EditarCompromissoActivity.this,
						"Data de Termino, maior que Data de Inicio. Verifique",
						Toast.LENGTH_SHORT).show();
			} else {

				dataFim.setText(d);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.novo_action_salvar) {

			if (TextUtils.isEmpty(titulo.getText().toString()) == true
					&& TextUtils.isEmpty(descricao.getText().toString()) == true
					&& addlu.size() == 0) {
				
				Toast.makeText(EditarCompromissoActivity.this, "Campos Vazios",
						Toast.LENGTH_SHORT).show();
				
			} else {
				
				if (TextUtils.isEmpty(titulo.getText().toString()) == true){
					Toast.makeText(EditarCompromissoActivity.this, "Titulo Vazio",
							Toast.LENGTH_SHORT).show();
				}
				if (TextUtils.isEmpty(descricao.getText().toString()) == true){
				Toast.makeText(EditarCompromissoActivity.this, "Descrição Vazia",
						Toast.LENGTH_SHORT).show();
				}
				
				if(TextUtils.isEmpty(descricao.getText().toString()) != true && TextUtils.isEmpty(titulo.getText().toString()) != true){
					{
				Compromissos c = new Compromissos();
				c.setTitulo(titulo.getText().toString());
				c.setDescricao(descricao.getText().toString());
				c.setDataInicio(dataInicio.getText().toString());
				c.setDataFim(dataFim.getText().toString());

				
				String c1 = "";
				for (String u : addlu) {
					c1 += u + "\n";
				}
				c.setParticipantes(c1);

				c.setId(comp.getId());
				c.setIdEmpr(comp.getIdEmpr());
				c.setIdUser(comp.getIdUser());
				c.setStatus(comp.getStatus());
				
				DAOCompromissos mdC = new DAOCompromissos(
						EditarCompromissoActivity.this);
				mdC.EditarCompromisso(c);
				}
				
				Toast.makeText(EditarCompromissoActivity.this, "Compromisso Atualizado",
						Toast.LENGTH_SHORT).show();
				finish();
			}}
		}else if(id == android.R.id.home){
	        finish();

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTokenAdded(Object token) {
		// TODO Auto-generated method stub
		Usuarios u = (Usuarios) token;

		addlu.add(u.getEmail());

	}

	@Override
	public void onTokenRemoved(Object token) {
		// TODO Auto-generated method stub
		Usuarios u = (Usuarios) token;

		addlu.remove(u.getEmail());

	}
}