package com.hack.olahack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class AutoBookService extends Service {

	String apiKey;
	static boolean isshowed = true;
	public static Double dstLatitude = 12.9250;
	public static Double dstLongitude = 77.5950;

	private static final double DAY_IN_MILLISECONDS = 86400000;

	public static String etaTime11;

	// public AutoBookService(String apiKey) {
	// this.apiKey = apiKey;
	// }

	ScheduledExecutorService executorService;
	LocationManager locationManager;
	String provider;
	Location loc;
	Context context;

	public static String nameOfEvent = "Family Function";
	public static String description = "Nice Marriage";

	public static String locationDestination = "Mandya";

	public static String startTime = "08/02/2014";

	// GPSTracker gpsTracker;

	static long nextTimeInMillis;

	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			Calendar calendar = Calendar.getInstance();

			long curTime = calendar.getTimeInMillis();

			SharedPreferences preferences = (SharedPreferences) getSharedPreferences(
					"lastupdate", Context.MODE_PRIVATE);
			long lastUpdate = preferences.getLong("last", -1);

			// if (lastUpdate != -1) {
			// double elapsedTime = (curTime - lastUpdate)
			// / (double) (1000 * 60 * 60);
			// if (elapsedTime > 24) {
			// updateDstLocation();
			// }
			// }

			// Log.e("location",
			// "" + gpsTracker.getLatitude() + "  "
			// + gpsTracker.getLongitude());

			// String urlString = makeUrl(gpsTracker.getLatitude(),
			// gpsTracker.getLongitude(), dstLatitude, dstLongitude);

			// Log.e("urlString",urlString) ;

			// String jsonString = getUrlContents(urlString);

			// Log.e("jsonString",jsonString) ;

			// Gson gson = new Gson();
			//
			// EtaEta etaEta = gson.fromJson(jsonString, EtaEta.class);

			try {

				// long etaTime = etaEta.getRows().get(0).getElements().get(0)
				// .getDuration().getValue();

				// long etaTime = 1000 * 60 * 60;

				// Log.e("eta","" +etaTime) ;

				// double hrsEta = etaTime / (double) (60 * 60);

				double hrsEta = 1;

				// Log.e("hrsEta","" +hrsEta) ;

				// Log.e("hrsEta","" +hrsEta) ;

				Log.e("diffCurPLanTime", "" + new Date(nextTimeInMillis));
				double diffCurPLanTime = (nextTimeInMillis - curTime)
						/ (double) (1000 * 60 * 60);
				Log.e("Date", "adsa" + nameOfEvent);
				// Log.e("diffCurPLanTime","" +nextTimeInMillis) ;

				if (diffCurPLanTime <= hrsEta) {

					// updateDstLocation();

					Log.e("Popup", "" + "pop");

					etaTime11 = Double.toString(hrsEta);
					if (isshowed) {
						Intent intent = new Intent(context, AutoPopUp.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						context.startActivity(intent);
						isshowed = false;
					}

					// context.stopService(new Intent(context,
					// AutoBookService.class));
					//
					// AutoBookService.this.stopSelf();
					// //////////////////////////////////////
					// //////////////// display popup here

				} else {
					// Log.e("Popup","" +"no   pop") ;

				}

			} catch (Exception d) {
				d.printStackTrace();
			}

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
	};

	private LocationListener listener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {
			// Log.e(TAG, "location update : " + location);
			Log.e("sdfsd", "I locationChanged");

			loc = location;
			locationManager.removeUpdates(listener);
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateDstLocation() {

		Calendar cur = Calendar.getInstance();
		Calendar tempCal = Calendar.getInstance();

		long diffTime = 1000 * 60 * 60 * 25;
		int latestIndex = -1;

		for (int i = 0; i < CalenderListExtractor.startDates.size(); i++) {
			tempCal.setTimeInMillis(CalenderListExtractor.startDates.get(i));

			if (isDateSame(cur, tempCal)) {
				if ((tempCal.getTimeInMillis() - cur.getTimeInMillis()) < diffTime
						&& (tempCal.getTimeInMillis() - cur.getTimeInMillis()) > 0) {
					// Log.e("Date", CalenderListExtractor.nameOfEvent.get(i));

					diffTime = (tempCal.getTimeInMillis() - cur
							.getTimeInMillis());
					latestIndex = i;
				}
			}
		}

		if (latestIndex != -1) {
			nextTimeInMillis = CalenderListExtractor.startDates
					.get(latestIndex);

			nameOfEvent = CalenderListExtractor.nameOfEvent.get(latestIndex);
			description = CalenderListExtractor.descriptions.get(latestIndex);

			locationDestination = CalenderListExtractor.eventLocation
					.get(latestIndex);
			Log.e("Date", nameOfEvent + "  " + new Date(nextTimeInMillis));
			Log.e("got first", nameOfEvent + "  " + description);

		}

	}

	private boolean isDateSame(Calendar c1, Calendar c2) {
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1
					.get(Calendar.DAY_OF_MONTH) == c2
				.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		SharedPreferences preferences = (SharedPreferences) getSharedPreferences(
				"lastupdate", Context.MODE_PRIVATE);
		preferences.edit()
				.putLong("last", Calendar.getInstance().getTimeInMillis())
				.commit();

		CalenderListExtractor calenderListExtractor = new CalenderListExtractor();

		calenderListExtractor.readCalendarEvent(this);

		updateDstLocation();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 14);

		// nextTimeInMillis = calendar.getTimeInMillis();

		// Log.e("sdfsd", "I service");

		super.onCreate();

		context = this;

		// gpsTracker = new GPSTracker(this);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		provider = locationManager.getBestProvider(new Criteria(), false);

		executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		// if (gpsTracker != null) {
		// gpsTracker.stopUsingGPS();
		// }
		super.onDestroy();
	}

	private String makeUrl(double srclatitude, double srclongitude,
			double dstlatitude, double dstlongitude) {
		StringBuilder urlString = new StringBuilder(
				"https://maps.googleapis.com/mlaps/api/distancematrix/json?");

		urlString.append("origins=");
		urlString.append(Double.toString(srclatitude));
		urlString.append(",");
		urlString.append(Double.toString(srclongitude));
		urlString.append("&destinations=");
		urlString.append(Double.toString(dstlatitude) + "," + dstlongitude);

		return urlString.toString();
	}

}
