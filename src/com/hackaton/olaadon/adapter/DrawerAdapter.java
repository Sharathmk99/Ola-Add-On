package com.hackaton.olaadon.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hack.olahack.R;
import com.hackaton.olaadon.utils.ListSupport;
import com.hackaton.olaadon.utils.Utils;

public class DrawerAdapter extends BaseAdapter {

	/**
	 * LayoutInflater instance for inflating the requested layout in the list
	 * view
	 */
	private LayoutInflater mInflater;

	public static ArrayList<ListSupport> mDataSet;

	/**
	 * Default constructor
	 */
	public DrawerAdapter(Context context, ArrayList<String> dataSet) {

		mInflater = LayoutInflater.from(context);
		mDataSet = new ArrayList<ListSupport>();
		ListSupport support;
		for (int i = 0; i < Utils.titles.length; i++) {
			support = new ListSupport();
			support.setTitle(Utils.titles[i]);
			support.setImageName(Utils.images[i]);
			mDataSet.add(support);
		}

	}

	public int getCount() {
		return mDataSet.size();
	}

	public Object getItem(int index) {
		return null;
	}

	public long getItemId(int index) {
		return 0;
	}

	public View getView(int position, View recycledView, ViewGroup parent) {
		ViewHolder holder;

		if (recycledView == null) {

			holder = new ViewHolder();
			recycledView = mInflater.inflate(R.layout.item_drawer, parent,
					false);
			holder.title = (TextView) recycledView.findViewById(R.id.title);
			holder.imageView = (ImageView) recycledView
					.findViewById(R.id.imageView1);
			recycledView.setTag(holder);

		} else {
			holder = (ViewHolder) recycledView.getTag();
		}
		ListSupport support = mDataSet.get(position);
		holder.title.setText(Utils.titlesOrginal[position]);
		holder.imageView.setImageResource(support.getImageName());
		return recycledView;
	}

	private static class ViewHolder {
		TextView title;
		ImageView imageView;
	}

}
