package com.easy.gpessoal.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import com.easy.gpessoal.NovoCompromissoActivity;
import com.easy.gpessoal.R;
import com.easy.gpessoal.adapters.CompromissosAdapter;
import com.easy.gpessoal.models.Compromissos;
import com.melnykov.fab.FloatingActionButton;

public class CompromissosFragment extends Fragment {

	///Compromisso
	List<String> groupList;
	ExpandableListView expListView;
	Map<String, List<Compromissos>> compromissosCollection;
	List<Compromissos> lc;
    public CompromissosFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comp, container, false);
        
        createObjetos();
		createGroupList();
		createCollection();
		
		expListView = (ExpandableListView)rootView.findViewById(R.id.laptop_list);
		final CompromissosAdapter expListAdapter = new CompromissosAdapter(getActivity(), groupList, compromissosCollection);
		expListView.setAdapter(expListAdapter);
		
		setGroupIndicatorToRight();
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final Compromissos selected = (Compromissos) expListAdapter.getChild(
						groupPosition, childPosition);
				
				Toast.makeText(getActivity(), selected.getTitulo(), Toast.LENGTH_LONG)
						.show();

				return true;
			}
		});

		for (int y = 0; y < groupList.size(); y++) {
			expListView.expandGroup(y);
		}
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String formattedDate = df.format(c.getTime()); 
		
		for (int i = 0; i < groupList.size(); i++) {
		
			if(groupList.get(i).equals(formattedDate)){
				expListView.setSelectedGroup(i);
			}
		}
		
		
		FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
		//fab.attachToListView(expListView); 
		fab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent n = new Intent(getActivity(), NovoCompromissoActivity.class);
				startActivity(n);
			
				
			}
		});	
        
        return rootView;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createGroupList() {
		groupList = new ArrayList<String>();

		if(lc != null){
		for (int i = 0; i < lc.size(); i++) {
			
			String ft = lc.get(i).getDataInicio();
			String c = ft.substring(0, 10);
			
			String ft2 = lc.get(i).getDataFim();
			String c2 = ft2.substring(0, 10);
			
			groupList.add(c);
			groupList.add(c2);

			
		}
		
		Object[] st = groupList.toArray();
	      for (Object s : st) {
	        if (groupList.indexOf(s) != groupList.lastIndexOf(s)) {
	            groupList.remove(groupList.lastIndexOf(s));
	         }
	      }
		
	      Collections.sort(groupList, new Comparator() {
				public int compare(Object o1, Object o2) {
					String c1 = (String) o1;
					String c2 = (String) o2;
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					Date formattedDate = null;
					try {
						formattedDate = df.parse(c1);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					Date formattedDate2 = null;
					try {
						formattedDate2 = df.parse(c2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					return formattedDate.compareTo(formattedDate2);
				}
			});
	      
		}
	}

	private void createObjetos(){
		
		lc = new ArrayList<Compromissos>();
		
		for(int x = 0; x < 10 ; x++){
			
			Compromissos c = new Compromissos();
			c.setTitulo("Compromisso "+x);
			c.setDataInicio("2"+x+"/05/2015 00:00");
			c.setDataFim("2"+x+"/05/2015 23:59");
			c.setStatus("P");
			lc.add(c);
		}
		
		for(int x = 0; x < 10 ; x++){
			
			Compromissos c = new Compromissos();
			c.setTitulo("Compromisso XY "+x);
			c.setDataInicio("2"+x+"/04/2015 00:00");
			c.setDataFim("2"+x+"/04/2015 23:59");
			c.setStatus("A");
			lc.add(c);
		}

		Compromissos c = new Compromissos();
		c.setTitulo("Compromisso Teste ");
		c.setDataInicio("12/04/2014 00:00");
		c.setDataFim("13/04/2014 23:59");
		c.setStatus("T");
		lc.add(c);
		
		Compromissos c2 = new Compromissos();
		c2.setTitulo("Compromisso 223");
		c2.setDataInicio("13/04/2015 00:00");
		c2.setDataFim("14/04/2015 23:59");
		c2.setStatus("T");
		lc.add(c2);
		
		Compromissos c3 = new Compromissos();
		c3.setTitulo("Compromisso Swift");
		c3.setDataInicio("10/04/2012 00:00");
		c3.setDataFim("11/04/2012 23:59");
		c3.setStatus("T");
		lc.add(c3);
		
		Compromissos c4 = new Compromissos();
		c4.setTitulo("Aniversário Vitor Santos");
		c4.setDataInicio("26/04/2015 00:00");
		c4.setDataFim("26/04/2015 23:59");
		c4.setStatus("A");
		lc.add(c4);
	}
	
	private void createCollection() {
		
		compromissosCollection = new LinkedHashMap<String, List<Compromissos>>();
		if(lc != null){
	
		Boolean b = true;
		int y = 0;
		
		do{
			
			List<Compromissos> rlc =  new ArrayList<Compromissos>();

			for (int i = 0; i < lc.size(); i++) {
				String comp = groupList.get(y);
				
				String ft = lc.get(i).getDataInicio();
				String c = ft.substring(0, 10);
				
				String ft2 = lc.get(i).getDataFim();
				String c2 = ft2.substring(0, 10);
				
				if (c.equals(comp)) {
					rlc.add(lc.get(i));
				}
				
				if(!c.equals(c2)){
					if (c2.equals(comp)) {
						rlc.add(lc.get(i));
					}
				};
				
				compromissosCollection.put(groupList.get(y), rlc);
			}
			
			y++;
			
			if(y >= groupList.size()){
				b = false;
			}
			
		}while(b);
		
		}
	}
    
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
private void setGroupIndicatorToRight() {
	/* Get the screen width */
	int width = getResources().getDisplayMetrics().widthPixels;

	if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
				- getDipsFromPixel(5));
	} else {
		expListView.setIndicatorBoundsRelative(
				width - getDipsFromPixel(35), width - getDipsFromPixel(5));
	}

}

// Convert pixel to dip
public int getDipsFromPixel(float pixels) {
	// Get the screen's density scale
	final float scale = getResources().getDisplayMetrics().density;
	// Convert the dps to pixels, based on density scale
	return (int) (pixels * scale + 0.5f);
}
}
