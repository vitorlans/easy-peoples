package com.easy.gpessoal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.models.Compromissos;
import com.easy.gpessoal.utils.DateTime;
import com.easy.gpessoal.utils.DateTimePicker;
import com.easy.gpessoal.utils.SimpleDateTimePicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NovoCompromissoActivity extends AppCompatActivity implements
		DateTimePicker.OnDateTimeSetListener {

	private Integer df;
	private Date todayDate;

	private SimpleDateTimePicker simpleDateTimePicker;
	
	private TextView Descricao;
	private TextView Titulo;
	private TextView Participantes;
	private TextView dataInicio;
	private TextView dataFim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		simpleDateTimePicker = SimpleDateTimePicker
				.make("Set Date & Time Title", new Date(), this,
						getSupportFragmentManager());

		Titulo = (TextView)findViewById(R.id.novo_titulo_ed);
		Descricao = (TextView)findViewById(R.id.novo_descric_et);
		Participantes = (TextView)findViewById(R.id.novo_particip_ed);

		dataInicio = (TextView) findViewById(R.id.novo_data_inicio_tv);
		dataFim = (TextView) findViewById(R.id.novo_data_fim_tv);

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
				d2.setHours(d2.getHours()+1);
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
				Toast.makeText(NovoCompromissoActivity.this,
						"Data Fim, maior que Data Inicio. Verifique",
						Toast.LENGTH_SHORT).show();
			} else {

				dataFim.setText(d);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.novo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();

		if (id == R.id.novo_action_salvar) {

			Compromissos c = new Compromissos();
			c.setTitulo(Titulo.getText().toString());
			c.setDescricao(Descricao.getText().toString());
			c.setDataInicio(dataInicio.getText().toString());
			c.setDataFim(dataFim.getText().toString());
			c.setParticipantes(Participantes.getText().toString());
			c.setStatus("A");

			DAOCompromissos mdC = new DAOCompromissos(NovoCompromissoActivity.this);
			mdC.CriarCompromisso(c);
			
			finish();
		}

		return super.onOptionsItemSelected(item);
	}
}