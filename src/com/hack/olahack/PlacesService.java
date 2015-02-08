package com.hack.olahack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.hack.atmpojo.AtmAtm;
import com.hack.atmpojo.Result;
import com.hack.olahackpojos.Place;

import android.util.Log;

public class PlacesService {

	 private String API_KEY;

	 public PlacesService(String apikey) {
	  this.API_KEY = apikey;
	 }

	 public void setApiKey(String apikey) {
	  this.API_KEY = apikey;
	 }

	 public ArrayList<Result> findPlaces(double latitude, double longitude,
	   String placeSpacification) {

	  String urlString = makeUrl(latitude, longitude, placeSpacification);

	  String json = getJSON(urlString);
	   
	   ArrayList<Result> arrayList = new ArrayList<Result>();
	   
	   Gson gson = new Gson() ;
	   
	   AtmAtm atmAtm = gson.fromJson(json, AtmAtm.class) ;
	   
	   for(int i = 0 ; i < atmAtm.getResults().size() ; i++)
	   {
		   arrayList.add(atmAtm.getResults().get(i)) ;
	   }

//	   System.out.println(json);
//	   JSONObject object = new JSONObject(json);
//	   JSONArray array = object.getJSONArray("results");
//
//	   ArrayList<Place> arrayList = new ArrayList<Place>();
//	   for (int i = 0; i < array.length(); i++) {
//	    try {
//	     Place place = Place
//	       .jsonToPontoReferencia((JSONObject) array.get(i));
//	     Log.v("Places Services ", "" + place);
//	     arrayList.add(place);
//	    } catch (Exception e) {
//	    }
//	   }
	   
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
	     new InputStreamReader(urlConnection.getInputStream()), 8);
	   String line;
	   while ((line = bufferedReader.readLine()) != null) {
	    content.append(line + "\n");
	   }
	   bufferedReader.close();
	  }catch (Exception e) {
	   e.printStackTrace();
	  }
	  return content.toString();
	 }
	}