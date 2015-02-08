package com.hack.olahack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.hackaton.olaadon.adapter.DrawerAdapter;
import com.hackaton.olaadon.catfragment.ATMFragment;
import com.hackaton.olaadon.catfragment.BarFragment;
import com.hackaton.olaadon.catfragment.CommonFragment;
import com.hackaton.olaadon.fragment.NavigationDrawerFragment;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout,
				toolbar);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, new CommonFragment("lodging"))
				.commit();
		drawerLayout.openDrawer(Gravity.LEFT);
		startService(new Intent(this, AutoBookService.class));
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mNavigationDrawerFragment.getDrawerToggle().syncState();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		String title = DrawerAdapter.mDataSet.get(position).getTitle();
		if (title.equals("atm")) {
			fragmentManager.beginTransaction()
					.replace(R.id.container, new ATMFragment()).commit();
		} else if (title.equals("bar")) {
			fragmentManager.beginTransaction()
					.replace(R.id.container, new BarFragment()).commit();
		} else {
			fragmentManager.beginTransaction()
					.replace(R.id.container, new CommonFragment(title))
					.commit();
		}

	}
}
