package com.hack.datainterfaces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.hack.atmpojo.AtmAtm;
import com.hack.atmpojo.Result;
import com.hack.barpojo.BarBar;
import com.hack.hotelpojo.HotelHotel;
import com.hack.shoppingmallpojo.MallMall;
import com.hack.theaterpojo.TheaterTheater;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetPlaces extends AsyncTask<Void, Void, Void> {

	private final String TAG = null;
	private ProgressDialog dialog;
	private Context context;
	private String places;
	private CommonsInterface commonsInterface;
	double lat, lng;
	String apiKey;

	public GetPlaces(Context context, String places, double lat, double lng,
			CommonsInterface commonsInterface, String apiKey) {
		this.context = context;
		this.places = places;
		this.lat = lat;
		this.lng = lng;
		this.commonsInterface = commonsInterface;
		this.apiKey = apiKey;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		dialog.dismiss();

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(context);
		dialog.setCancelable(false);
		dialog.setMessage("Loading..");
		dialog.show();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		PlacesService service = new PlacesService(apiKey);

		ArrayList<PlaceDetails> findPlaces = service.findPlaces(lat, lng,
				places);

		// for (PlaceDetails placeDetails : findPlaces) {
		// Log.e("YRESSSSSSSS", "" + placeDetails) ;
		// }
		//

		commonsInterface.onPlaceDetailsRetrieved(findPlaces, places);

		return null;

	}

	public class PlacesService {

		private String API_KEY;

		public PlacesService(String apikey) {
			this.API_KEY = apikey;
		}

		public void setApiKey(String apikey) {
			this.API_KEY = apikey;
		}

		public ArrayList<PlaceDetails> findPlaces(double latitude,
				double longitude, String placeSpacification) {

			String urlString = makeUrl(latitude, longitude, placeSpacification);

			String json = getJSON(urlString);

			ArrayList<PlaceDetails> arrayList = new ArrayList<PlaceDetails>();

			Gson gson = new Gson();

			switch (places) {
			case "atm": {
				AtmAtm atmAtm = gson.fromJson(json, AtmAtm.class);
				PlaceDetails placeDetails;
				List<Result> results = atmAtm.getResults();

				for (Result result : results) {

					placeDetails = new PlaceDetails();

					placeDetails.setAddress(result.getVicinity());

					if (result.getGeometry() != null) {
						placeDetails.setLat(result.getGeometry().getLocation()
								.getLat());
						placeDetails.setLng(result.getGeometry().getLocation()
								.getLng());
					}

					placeDetails.setName(result.getName());

					if (result.getOpeningHours() != null) {
						if (result.getOpeningHours().getOpenNow() == true) {
							placeDetails.setOpen("Open Now");
						} else if (result.getOpeningHours().getOpenNow() == false) {
							placeDetails.setOpen("Closed Now");
						}
					} else {
						placeDetails.setOpen("Data Not Available");

					}

					if (!result.getPhotos().isEmpty()) {
						placeDetails.setPhotoReference(result.getPhotos()
								.get(0).getPhotoReference());
						placeDetails.setPhotoWidth(result.getPhotos().get(0)
								.getWidth());
					}

					placeDetails.setRating(result.getRating());

					arrayList.add(placeDetails);

				}

				break;
			}

			case "movie_theater": {
				TheaterTheater theaterTheater = gson.fromJson(json,
						TheaterTheater.class);
				PlaceDetails placeDetails;
				List<com.hack.theaterpojo.Result> results = theaterTheater
						.getResults();

				for (com.hack.theaterpojo.Result result : results) {
					placeDetails = new PlaceDetails();

					placeDetails.setAddress(result.getVicinity());

					if (result.getGeometry() != null) {
						placeDetails.setLat(result.getGeometry().getLocation()
								.getLat());
						placeDetails.setLng(result.getGeometry().getLocation()
								.getLng());
					}

					placeDetails.setName(result.getName());

					if (result.getOpeningHours() != null) {
						if (result.getOpeningHours().getOpenNow() == true) {
							placeDetails.setOpen("Open Now");
						} else if (result.getOpeningHours().getOpenNow() == false) {
							placeDetails.setOpen("Closed Now");
						}
					} else {
						placeDetails.setOpen("Data Not Available");

					}

					if (!result.getPhotos().isEmpty()) {
						placeDetails.setPhotoReference(result.getPhotos()
								.get(0).getPhotoReference());
						placeDetails.setPhotoWidth(result.getPhotos().get(0)
								.getWidth());
					}

					placeDetails.setRating(result.getRating());

					arrayList.add(placeDetails);
				}
				break;
			}

			case "liquor_store":
			case "bar": {
				BarBar barBar = gson.fromJson(json, BarBar.class);
				PlaceDetails placeDetails;
				List<com.hack.barpojo.Result> results = barBar.getResults();

				for (com.hack.barpojo.Result result : results) {
					placeDetails = new PlaceDetails();

					placeDetails.setAddress(result.getVicinity());

					if (result.getGeometry() != null) {
						placeDetails.setLat(result.getGeometry().getLocation()
								.getLat());
						placeDetails.setLng(result.getGeometry().getLocation()
								.getLng());
					}

					placeDetails.setName(result.getName());

					if (result.getOpeningHours() != null) {
						if (result.getOpeningHours().getOpenNow() == true) {
							placeDetails.setOpen("Open Now");
						} else if (result.getOpeningHours().getOpenNow() == false) {
							placeDetails.setOpen("Closed Now");
						}
					} else {
						placeDetails.setOpen("Data Not Available");

					}

					if (!result.getPhotos().isEmpty()) {
						placeDetails.setPhotoReference(result.getPhotos()
								.get(0).getPhotoReference());
						placeDetails.setPhotoWidth(result.getPhotos().get(0)
								.getWidth());
					}

					placeDetails.setRating(result.getRating());

					arrayList.add(placeDetails);
				}
				break;
			}

			case "shopping_mall": {
				MallMall mallMall = gson.fromJson(json, MallMall.class);
				PlaceDetails placeDetails;
				List<com.hack.shoppingmallpojo.Result> results = mallMall
						.getResults();

				for (com.hack.shoppingmallpojo.Result result : results) {
					placeDetails = new PlaceDetails();

					placeDetails.setAddress(result.getVicinity());

					if (result.getGeometry() != null) {
						placeDetails.setLat(result.getGeometry().getLocation()
								.getLat());
						placeDetails.setLng(result.getGeometry().getLocation()
								.getLng());
					}

					placeDetails.setName(result.getName());

					if (result.getOpeningHours() != null) {
						if (result.getOpeningHours().getOpenNow() == true) {
							placeDetails.setOpen("Open Now");
						} else if (result.getOpeningHours().getOpenNow() == false) {
							placeDetails.setOpen("Closed Now");
						}
					} else {
						placeDetails.setOpen("Data Not Available");

					}

					if (!result.getPhotos().isEmpty()) {
						placeDetails.setPhotoReference(result.getPhotos()
								.get(0).getPhotoReference());
						placeDetails.setPhotoWidth(result.getPhotos().get(0)
								.getWidth());
					}

					placeDetails.setRating(result.getRating());

					arrayList.add(placeDetails);
				}
				break;
			}

			case "lodging":
			case "restaurant": {
				HotelHotel mallMall = gson.fromJson(json, HotelHotel.class);
				PlaceDetails placeDetails;
				List<com.hack.hotelpojo.Result> results = mallMall.getResults();

				for (com.hack.hotelpojo.Result result : results) {
					placeDetails = new PlaceDetails();

					placeDetails.setAddress(result.getVicinity());

					if (result.getGeometry() != null) {
						placeDetails.setLat(result.getGeometry().getLocation()
								.getLat());
						placeDetails.setLng(result.getGeometry().getLocation()
								.getLng());
					}

					placeDetails.setName(result.getName());

					if (result.getOpeningHours() != null) {
						if (result.getOpeningHours().getOpenNow() == true) {
							placeDetails.setOpen("Open Now");
						} else if (result.getOpeningHours().getOpenNow() == false) {
							placeDetails.setOpen("Closed Now");
						}
					} else {
						placeDetails.setOpen("Data Not Available");

					}

					if (!result.getPhotos().isEmpty()) {
						placeDetails.setPhotoReference(result.getPhotos()
								.get(0).getPhotoReference());
						placeDetails.setPhotoWidth(result.getPhotos().get(0)
								.getWidth());
					}

					placeDetails.setRating(result.getRating());

					arrayList.add(placeDetails);
				}
				break;
			}

			default:
				;

			}

			return arrayList;

		}

		// https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=apikey
		private String makeUrl(double latitude, double longitude, String place) {
			StringBuilder urlString = new StringBuilder(
					"https://maps.googleapis.com/maps/api/place/search/json?");

			if (place.equals("")) {
				urlString.append("&location=");
				urlString.append(Double.toString(latitude));
				urlString.append(",");
				urlString.append(Double.toString(longitude));
				urlString.append("&radius=1000");
				// urlString.append("&types="+place);
				urlString.append("&sensor=false&key=" + API_KEY);
			} else {
				urlString.append("&location=");
				urlString.append(Double.toString(latitude));
				urlString.append(",");
				urlString.append(Double.toString(longitude));
				urlString.append("&radius=1000");
				urlString.append("&types=" + place);
				urlString.append("&sensor=false&key=" + API_KEY);
			}
			return urlString.toString();
		}

		protected String getJSON(String url) {
			return getUrlContents(url);
		}

		private String getUrlContents(String theUrl) {
			StringBuilder content = new StringBuilder();
			try {
				URL url = new URL(theUrl);
				URLConnection urlConnection = url.openConnection();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()),
						8);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					content.append(line + "\n");
				}
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return content.toString();
		}
	}

}
