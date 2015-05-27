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

import org.xml.sax.DTDHandler;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import com.easy.gpessoal.DetalhesCompromissosActivity;
import com.easy.gpessoal.EmpresaActivity;
import com.easy.gpessoal.LoginActivity;
import com.easy.gpessoal.NovoCompromissoActivity;
import com.easy.gpessoal.R;
import com.easy.gpessoal.adapters.CompromissosAdapter;
import com.easy.gpessoal.database.DAOCompromissos;
import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Compromissos;
import com.melnykov.fab.FloatingActionButton;

public class CompromissosFragment extends Fragment {

	// /Compromisso
	List<String> groupList;
	ExpandableListView expListView;
	Map<String, List<Compromissos>> compromissosCollection;
	List<Compromissos> lc;
	CompromissosAdapter expListAdapter;
	String dta = null;

	public CompromissosFragment() {
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
		View rootView = inflater.inflate(R.layout.fragment_comp, container,
				false);

		Bundle bundle = this.getArguments();
		if (bundle != null) {
			dta = bundle.getString("data");
		}

		expListView = (ExpandableListView) rootView
				.findViewById(R.id.comp_list);
		setGroupIndicatorToRight();

		FloatingActionButton fab = (FloatingActionButton) rootView
				.findViewById(R.id.fab);
		// fab.attachToListView(expListView);
		fab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent n = new Intent(getActivity(),
						NovoCompromissoActivity.class);
				startActivity(n);

			}
		});

		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		createObjetos();
		createGroupList();
		createCollection();

		expListAdapter = new CompromissosAdapter(getActivity(), groupList,
				compromissosCollection);
		expListView.setAdapter(expListAdapter);
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final Compromissos selected = (Compromissos) expListAdapter
						.getChild(groupPosition, childPosition);

				Intent intCompromisso = new Intent(getActivity(),
						DetalhesCompromissosActivity.class);

				intCompromisso.putExtra("idComp", selected.getId());
				getActivity().startActivity(intCompromisso);

				return true;
			}
		});

		ExpandeRecents();
		if (dta == null) {
			IrHoje();
		} else {

			for (int i = 0; i < groupList.size(); i++) {

				if (groupList.get(i).equals(dta)) {
					expListView.expandGroup(i);
					expListView.setSelectedGroup(i);
				}
			}

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createGroupList() {
		groupList = new ArrayList<String>();

		if (lc != null) {
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
					;
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",
							Locale.getDefault());
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

	private void createObjetos() {

		lc = new ArrayList<Compromissos>();

		DAOCompromissos mdC = new DAOCompromissos(getActivity());
		lc = mdC.RecuperarTodos();

	}

	private void createCollection() {

		compromissosCollection = new LinkedHashMap<String, List<Compromissos>>();
		if (lc != null) {

			Boolean b = true;
			int y = 0;

			do {

				List<Compromissos> rlc = new ArrayList<Compromissos>();

				for (int i = 0; i < lc.size(); i++) {
					String comp = groupList.get(y);

					String ft = lc.get(i).getDataInicio();
					String c = ft.substring(0, 10);

					String ft2 = lc.get(i).getDataFim();
					String c2 = ft2.substring(0, 10);

					if (c.equals(comp)) {
						rlc.add(lc.get(i));
					}

					if (!c.equals(c2)) {
						if (c2.equals(comp)) {
							rlc.add(lc.get(i));
						}
					}
					;

					OrderByDate(rlc);
					compromissosCollection.put(groupList.get(y), rlc);
				}

				y++;

				if (y >= groupList.size()) {
					b = false;
				}

			} while (b);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void OrderByDate(List<Compromissos> rlc) {

		Collections.sort(rlc, new Comparator() {
			public int compare(Object o1, Object o2) {
				Compromissos c1 = (Compromissos) o1;
				Compromissos c2 = (Compromissos) o2;
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm",
						Locale.getDefault());
				Date formattedDate = null;
				try {
					formattedDate = df.parse(c1.getDataInicio());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date formattedDate2 = null;
				try {
					formattedDate2 = df.parse(c2.getDataInicio());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return formattedDate.compareTo(formattedDate2);
			}
		});
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

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu, menuInflater);
		menuInflater.inflate(R.menu.compromissos, menu);

		SearchManager searchManager = (SearchManager) getActivity()
				.getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(
				R.id.menu_comp_pesquisar).getActionView();

        searchView.setSubmitButtonEnabled(true); 
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getActivity().getComponentName()));
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String newText) {
				// this is your adapter that will be filtered


					expListAdapter.getFilter().filter(newText);
					for (int y = 0; y < groupList.size(); y++) {
						expListView.expandGroup(y);
					}
				
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				if (TextUtils.isEmpty(newText)) {
					expListAdapter.clearFilter();
					ExpandeRecents();
					IrHoje();}
				
				return false;
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_empresa) {

			Intent n = new Intent(getActivity(), EmpresaActivity.class);
			getActivity().startActivity(n);

			return true;
		} else if (id == R.id.action_login) {
			Intent n = new Intent(getActivity(), LoginActivity.class);
			getActivity().startActivity(n);

			return true;

		} else if (id == R.id.menu_comp_hoje) {

			IrHoje();
			return true;

		} else {
			return true;
		}

	}

	private void IrHoje() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String formattedDate = df.format(c.getTime());

		Date hoje = null;
		try {
			hoje = df.parse(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Boolean encontro = false;
		long menor = hoje.getTime();
		int pos = 0;
		
		
		for (int i = 0; i < groupList.size(); i++) {

			Date data = null;
			try {
				data = df.parse(groupList.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			if(data.compareTo(hoje) == 0){
				pos = i;
				encontro = true;
			}else{
			
			if(encontro != true){
			long sub = hoje.getTime() - data.getTime();
				if(sub < menor ){
					menor = sub;
					pos = i;
				}
			}
			}
		}
		
		expListView.setSelectedGroup(pos);
		expListView.expandGroup(pos);
		
	}

	private void ExpandeRecents() {

		Calendar c = Calendar.getInstance();
		Date dfim = null;

		for (int x = 0; x < groupList.size(); x++) {
			String sfim = groupList.get(x);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
					Locale.getDefault());
			try {

				dfim = sdf.parse(sfim);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date dnow = c.getTime();
			dnow.setDate(dnow.getDate() - 14);

			if (dfim.before(dnow)) {

			} else {
				expListView.expandGroup(x);
			}
		}
	}

}
