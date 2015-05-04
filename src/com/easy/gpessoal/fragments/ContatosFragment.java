package com.easy.gpessoal.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.SearchManager;
import android.content.Context;
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
import com.easy.gpessoal.R;
import com.easy.gpessoal.adapters.ContatosAdapter;
import com.easy.gpessoal.database.DAOUsuarios;
import com.easy.gpessoal.models.Usuarios;
import com.easy.gpessoal.views.PinnedHeaderListView;

public class ContatosFragment extends Fragment {

    private static final String FORMAT = "^[a-z,A-Z].*$";
	private PinnedHeaderListView mLVusuarios;
	private ContatosAdapter mAdapter;
	private List<String> mSections;
	private Map<String, List<String>> mMap;
	private List<Integer> mPositions;
	private Map<String, Integer> mIndexer;
	private List<Usuarios> mListUsuarios;

    public ContatosFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_contatos, container, false);

        //BD LIST
        DAOUsuarios mdU = new DAOUsuarios(getActivity());
		mListUsuarios = mdU.RecuperarSimplesTodos();

		mLVusuarios = (PinnedHeaderListView)rootView.findViewById(R.id.main_usuarios_lv);
		
		initData();
		mAdapter = new ContatosAdapter(getActivity(), mListUsuarios, mSections, mPositions);
		mLVusuarios.setAdapter(mAdapter);
		mLVusuarios.setOnScrollListener(mAdapter);
		mLVusuarios.setTextFilterEnabled(true);
		mLVusuarios.setPinnedHeaderView(LayoutInflater.from(getActivity()).inflate(
				R.layout.pinned_header_listview_side_header, mLVusuarios, false));
		
        return rootView;
    }

    private void initData() {
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<String>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();

		for (int i = 0; i < mListUsuarios.size(); i++) {
			String nome = mListUsuarios.get(i).getNome();
			String charNome = nome.substring(0, 1);
			
			if (charNome.matches(FORMAT)) {
				if (mSections.contains(charNome)) {
					mMap.get(charNome).add(nome);
				} else {
					mSections.add(charNome);
					List<String> list = new ArrayList<String>();
					list.add(nome);
					mMap.put(charNome, list);
				}
			} else {
				if (mSections.contains("#")) {
					mMap.get("#").add(nome);
				} else {
					mSections.add("#");
					List<String> list = new ArrayList<String>();
					list.add(nome);
					mMap.put("#", list);
				}
			}
		}

		Collections.sort(mSections);
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);
			mPositions.add(position);
			position += mMap.get(mSections.get(i)).size();
		}
	}
    
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
    	super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.contatos, menu);
        
		SearchManager searchManager = (SearchManager) getActivity().getSystemService( Context.SEARCH_SERVICE );
        SearchView searchView = (SearchView) menu.findItem(R.id.main_action_pesquisar).getActionView();
 
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String newText) {
				 // this is your adapter that will be filtered
			      
			         
			      return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				
				if (TextUtils.isEmpty(newText))
			      {
			    	  mAdapter.clearFilter();
			      }
			      else
			      {
			          mAdapter.getFilter().filter(newText);
			       }
				
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
		if (id == R.id.main_action_pesquisar) {
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
