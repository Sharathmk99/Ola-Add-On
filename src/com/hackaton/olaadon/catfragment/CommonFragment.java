package com.hackaton.olaadon.catfragment;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hack.datainterfaces.CommonsInterface;
import com.hack.datainterfaces.GetPlaces;
import com.hack.datainterfaces.PlaceDetails;
import com.hack.locfromadd.LocationFromAddress;
import com.hack.olahack.R;
import com.hackaton.olaadon.adapter.HotelAdapter;
import com.hackaton.olaadon.utils.Utils;

public class CommonFragment extends Fragment implements CommonsInterface {

	ListView listView;
	Context context;
	String category;

	public CommonFragment(String category) {
		this.category = category;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.common_layout, container,
				false);
		return content;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		context = getActivity();
		listView = (ListView) view.findViewById(R.id.list);
		GetPlaces getPlaces = new GetPlaces(getActivity(), category,
				12.9779381, 77.5683899, this, Utils.API_KEY);
		getPlaces.execute();
		LocationFromAddress address = new LocationFromAddress(
				"Plot No. 44, Hosur Road, Electronics City, Phase 1, Bengaluru, Karnataka 560100".replace(" ", "%20"),
				getActivity());
		address.getLatLng();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onPlaceDetailsRetrieved(List<PlaceDetails> details, String place) {
		if (!details.isEmpty()) {
			if (details.size() > 30) {
				details = details.subList(0, 30);
			}
		}
		final List<PlaceDetails> temp = details;
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {

				listView.setAdapter(new HotelAdapter(context, temp));
			}

		});

	}
}