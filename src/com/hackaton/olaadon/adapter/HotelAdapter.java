package com.hackaton.olaadon.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hack.datainterfaces.PlaceDetails;
import com.hack.olahack.R;
import com.hackaton.olaadon.images.MakeUrlForPhotoReference;
import com.hackaton.olaadon.utils.Utils;
import com.squareup.picasso.Picasso;

public class HotelAdapter extends BaseAdapter {

	Context context;
	List<PlaceDetails> details;

	public HotelAdapter(Context context, List<PlaceDetails> details) {
		this.context = context;
		this.details = details;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return details.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View recycledView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (recycledView == null) {

			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(context);
			recycledView = mInflater.inflate(R.layout.hotel_support, parent,
					false);
			holder.title = (TextView) recycledView.findViewById(R.id.name);
			holder.address = (TextView) recycledView.findViewById(R.id.address);
			holder.rating = (TextView) recycledView.findViewById(R.id.rating);
			holder.open = (TextView) recycledView.findViewById(R.id.open);
			holder.imageView = (ImageView) recycledView
					.findViewById(R.id.image);
			recycledView.setTag(holder);
		} else {
			holder = (ViewHolder) recycledView.getTag();
		}
		holder.address.setText(details.get(position).getAddress());
		holder.title.setText(details.get(position).getName());
		if (details.get(position).getRating() == null) {
			holder.rating.setVisibility(View.GONE);
		} else {
			holder.rating.setVisibility(View.VISIBLE);
			holder.rating.setText("" + details.get(position).getRating());
		}
		if (details.get(position).isOpen().contains("Not Available")) {
			holder.open.setVisibility(View.GONE);
		} else {
			holder.open.setVisibility(View.VISIBLE);
			holder.open.setText(details.get(position).isOpen());
		}
		if (details.get(position).getPhotoReference() != null) {
			Picasso.with(context)
					.load(new MakeUrlForPhotoReference(Utils.API_KEY, details
							.get(position).getPhotoReference()).getUrl())
					.error(R.drawable.not_available).into(holder.imageView);
		}

		return recycledView;
	}

	private static class ViewHolder {
		TextView title, rating, address, open;
		ImageView imageView;
	}

}
