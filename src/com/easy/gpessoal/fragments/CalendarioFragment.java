package com.easy.gpessoal.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Toast;

import com.easy.gpessoal.R;
import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.models.Compromissos;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayView;
import com.prolificinteractive.materialcalendarview.EventDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

public class CalendarioFragment extends Fragment implements OnDateChangedListener{
	
	MaterialCalendarView calendar;

	public CalendarioFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_calendar, container,
				false);

		Calendar c = Calendar.getInstance();
		calendar = (MaterialCalendarView)rootView.findViewById(R.id.calendarView);
		calendar.setSelectedDate(c.getTime());
		c.set(c.get(Calendar.YEAR), Calendar.JANUARY, 1);
		calendar.setMinimumDate(c.getTime());

		calendar.setOnDateChangedListener(this);
		c.set(c.get(Calendar.YEAR), Calendar.DECEMBER, 31);
		calendar.setMaximumDate(c.getTime());
        		
        new Eventos().executeOnExecutor(Executors.newSingleThreadExecutor());
       
        
        return rootView;
	}

	@Override
	
	public void onDateChanged(final MaterialCalendarView widget, final CalendarDay date, DayView dayView) {
		// TODO Auto-generated method stub
		
		dayView.setOnClickListener(new OnClickListener() {
			int i = 0;

		    @Override
		    public void onClick(View v) {
		        // TODO Auto-generated method stub
		        i++;
		        Handler handler = new Handler();
		        Runnable r = new Runnable() {

		            @Override
		            public void run() {
		                i = 0;
		            }
		        };

		        if (i == 1) {
		            //Single click
		        	widget.setSelectedDate(date);
		            handler.postDelayed(r, 250);
		        } else if (i == 2) {
		            //Double click
		            i = 0;
		            Toast.makeText(getActivity(), date.getDate().toString(), Toast.LENGTH_SHORT).show();
					Fragment fragment = new CompromissosFragment();
					Bundle args = new Bundle();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
					String now = sdf.format(date.getDate());
					args.putString("data", now);
					fragment.setArguments(args);
					
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
					
		        }


		    }
		});
			

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu, menuInflater);
		menuInflater.inflate(R.menu.calendario, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		int id = item.getItemId();
		if(id == R.id.menu_calen_hoje){
			Calendar c = Calendar.getInstance();

			calendar.setSelectedDate(c.getTime());
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class Eventos extends AsyncTask<Void, Void, List<CalendarDay>>{

		@Override
		protected List<CalendarDay> doInBackground(Void... voids) {
		

			List<CalendarDay> mCalendarList = new ArrayList<>();

			List<Compromissos>lc = new ArrayList<Compromissos>();
			DAOCompromissos mdC = new DAOCompromissos(getActivity());
			lc = mdC.RecuperarTodos();

			for(int x = 0; x < lc.size(); x++){
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date d = null;
						try {
							d = sdf.parse(lc.get(x).getDataInicio());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				CalendarDay cd = new CalendarDay(d);
				mCalendarList.add(cd);
			}
				
			for(int x = 0; x < lc.size(); x++){
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date d = null;
						try {
							d = sdf.parse(lc.get(x).getDataFim());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				CalendarDay cd = new CalendarDay(d);
				mCalendarList.add(cd);
			}
			
			return mCalendarList;
		}
		

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            calendar.addDecorator(new EventDecorator(Color.RED, calendarDays));
        }
		
	}
}

