package com.easy.gpessoal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.easy.gpessoal.adapters.DrawerListAdapter;
import com.easy.gpessoal.fragments.CompromissosFragment;
import com.easy.gpessoal.fragments.ContatosFragment;
import com.easy.gpessoal.models.Drawer;

public class HomeActivity extends AppCompatActivity {

	// Drawer
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private List<Drawer> mDrawerItens;
	int[] mImagem;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar mToolbar;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mLabel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mToolbar =(Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(mToolbar);
		
		getSupportActionBar().setTitle(R.string.app_name);
		navigateDrawer(savedInstanceState);

	}

	private void navigateDrawer(Bundle savedInstanceState) {
		mDrawerTitle = getSupportActionBar().getTitle();
		mLabel = getResources().getStringArray(R.array.options_menu);
		mImagem = new int[]{R.drawable.ic_calendar_text, R.drawable.ic_account_multiple };
		mDrawerItens = new ArrayList<Drawer>();
		
		for(int x = 0; x < mLabel.length; x++){
			
			Drawer d = new Drawer();
			d.setImagem(mImagem[x]);
			d.setLabel(mLabel[x]);
			mDrawerItens.add(d);
			
		}
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
	    TextView v = new TextView(HomeActivity.this);
        v.setBackgroundResource(R.color.material_blue_500);
        v.setTextColor(0xFFffffff);
        v.setTextSize(20);
        v.setHeight(dpToPx(100));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd - MMMM - yyyy", Locale.getDefault());
		String formattedDate = df.format(c.getTime()); 
        v.setText("HOJE\n "+formattedDate);
        v.setGravity(Gravity.CENTER);


        // Adding button to listview at footer
        mDrawerList.addHeaderView(v);

		DrawerListAdapter dla = new DrawerListAdapter(this, mDrawerItens);
		mDrawerList.setAdapter(dla);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		mToolbar, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	// DRAWER
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			int x = position-1;
			if(x == -1){}else{
			selectItem(x);
			}
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments

		switch (position) {
		case 0:
			Fragment fragment = new CompromissosFragment();
			Bundle args = new Bundle();
			fragment.setArguments(args);

			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

			break;

		case 1:
			Fragment fragment1 = new ContatosFragment();
			Bundle args1 = new Bundle();
			fragment1.setArguments(args1);

			FragmentManager fragmentManager1 = getSupportFragmentManager();
			fragmentManager1.beginTransaction().replace(R.id.container, fragment1).commit();


			break;
		}

		mDrawerList.setItemChecked(position, true);
		changeTitle(mLabel[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	
	public void changeTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	
	public static int dpToPx(int dp)
	{
	    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}

	public static int pxToDp(int px)
	{
	    return (int) (px / Resources.getSystem().getDisplayMetrics().density);
	}
}
