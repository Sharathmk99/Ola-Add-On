package com.hack.locfromadd;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class LocationFromAddress {

	LatLng latLng;

	public LocationFromAddress(String address, final Context context) {
		final ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		new AsyncTask<String, Void, Void>() {

			@Override
			protected Void doInBackground(String... params) {
				// TODO Auto-generated method stub
				getLatLongFromGivenAddress(params[0]);
				return null;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				dialog.show();

			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				dialog.dismiss();
			}
		}.execute(address);
	}

	public void getLatLongFromGivenAddress(String youraddress) {
		try {

			String uri = "http://maps.google.com/maps/api/geocode/json?address="
					+ youraddress + "&sensor=false";
			HttpGet httpGet = new HttpGet(uri);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			StringBuilder stringBuilder = new StringBuilder();

			try {
				response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				InputStream stream = entity.getContent();
				int b;
				while ((b = stream.read()) != -1) {
					stringBuilder.append((char) b);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = new JSONObject(stringBuilder.toString());

				double lng = ((JSONArray) jsonObject.get("results"))
						.getJSONObject(0).getJSONObject("geometry")
						.getJSONObject("location").getDouble("lng");

				double lat = ((JSONArray) jsonObject.get("results"))
						.getJSONObject(0).getJSONObject("geometry")
						.getJSONObject("location").getDouble("lat");

				Log.d("latitude", "" + lat);
				Log.d("longitude", "" + lng);
				latLng = new LatLng(lat, lng);
			} catch (JSONException e) {
				latLng = null;
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			latLng = null;
		}

	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}
}
