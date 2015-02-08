package com.hackaton.olaadon.catfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hack.datainterfaces.CommonsInterface;
import com.hack.datainterfaces.GetPlaces;
import com.hack.datainterfaces.PlaceDetails;
import com.hack.olahack.R;
import com.hackaton.olaadon.utils.Utils;

public class BarFragment extends Fragment implements CommonsInterface {

	GoogleMap googleMap;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public BarFragment() {
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

		View content = inflater.inflate(R.layout.bar_layout, container, false);

		return content;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initialMap();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	private void initialMap() {
		if (googleMap == null) {
			SupportMapFragment fragment = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map));
			googleMap = fragment.getMap();
			if (googleMap == null) {
				Toast.makeText(getActivity(),
						"Google Play service is not supported in this device",
						Toast.LENGTH_LONG).show();
			}
			googleMap.setMyLocationEnabled(true);
			googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
		}
	}

	/** The my location change listener. */
	boolean isFirst = true;
	Marker marker = null;
	boolean isReferesh = true;
	private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
		@Override
		public void onMyLocationChange(Location location) {
			LatLng loc = new LatLng(location.getLatitude(),
					location.getLongitude());
			MarkerOptions markerOptions = new MarkerOptions()
					.position(loc)
					.title("Current Location")
					.snippet("and snippet")
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			if (marker != null) {
				marker.remove();
			}
			marker = googleMap.addMarker(markerOptions);
			if (isReferesh) {
				GetPlaces getPlaces = new GetPlaces(getActivity(), "bar",
						loc.latitude, loc.longitude, BarFragment.this,
						Utils.API_KEY);
				getPlaces.execute();
				isReferesh = false;
			}
			if (googleMap != null && isFirst) {
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,
						16.0f));
				isFirst = false;
			}

		}
	};

	List<Marker> list = new ArrayList<Marker>();

	@Override
	public void onPlaceDetailsRetrieved(List<PlaceDetails> details, String place) {
		// TODO Auto-generated method stub
		if (!list.isEmpty()) {
			for (Marker marker : list) {
				marker.remove();
			}
		}
		if (!details.isEmpty()) {
			if (details.size() > 30) {
				details = details.subList(0, 30);
			}
		}
		final List<PlaceDetails> temp = details;
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				LatLng loc;
				for (PlaceDetails placeDetails : temp) {
					loc = new LatLng(placeDetails.getLat(), placeDetails
							.getLng());
					MarkerOptions markerOptions = new MarkerOptions().position(
							loc).title(placeDetails.getName());
					list.add(googleMap.addMarker(markerOptions));
				}
			}
		});

	}
}