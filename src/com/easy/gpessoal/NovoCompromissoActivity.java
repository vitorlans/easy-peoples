package com.easy.gpessoal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.easy.gpessoal.utils.DateTime;
import com.easy.gpessoal.utils.DateTimePicker;
import com.easy.gpessoal.utils.SimpleDateTimePicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NovoCompromissoActivity extends AppCompatActivity implements DateTimePicker.OnDateTimeSetListener {

	Integer df;
	Calendar calendar;
	Date todayDate;
	TextView textView;
	TextView textView1;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);
        Toolbar mToolbar =(Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        todayDate = Calendar.getInstance().getTime();
        String sInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(todayDate);
       
        todayDate.setHours(todayDate.getHours()+1);
        String sFim = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(todayDate);

        
        // Create a SimpleDateTimePicker and Show it
        final SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Set Date & Time Title",
                new Date(),
                this,
                getSupportFragmentManager()
        );

        /* Or we can chain it to simplify
        SimpleDateTimePicker.make(
                "Set Date & Time Title",
                new Date(),
                this,
                getSupportFragmentManager()
        ).show();*/
        
        textView = (TextView) findViewById(R.id.novo_data_inicio_tv);
        textView.setText(sInicio);
        textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        df = 1; 
		        simpleDateTimePicker.show();

			}
		});
        
        textView1 = (TextView) findViewById(R.id.novo_data_fim_tv);
        textView1.setText(sFim);
        textView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        df = 2; 
		        simpleDateTimePicker.show();

			}
		});
    }

    @Override
    public void DateTimeSet(Date date) {

        // This is the DateTime class we created earlier to handle the conversion
        // of Date to String Format of Date String Format to Date object
        DateTime mDateTime = new DateTime(date);
        // Show in the LOGCAT the selected Date and Time
        String d = mDateTime.getDateString("dd/MM/yyyy HH:mm");
        
        if(df.equals(1)){
		textView.setText(d);
        }else{
        	textView1.setText(d);
        }
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.novo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.novo_action_salvar) {

        	finish();
        }

        return super.onOptionsItemSelected(item);
    }
}