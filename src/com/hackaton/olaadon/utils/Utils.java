package com.hackaton.olaadon.utils;

import com.hack.olahack.R;

public class Utils {
	public static int[] images = { R.drawable.atm, R.drawable.bar,
			R.drawable.hotel, R.drawable.shopping_mall, R.drawable.movie };
	public static String[] titlesOrginal = { "ATM", "Bar", "Hotels",
			"Shopping Mall", "Movies" };
	public static String[] titles = { "atm", "bar", "lodging", "shopping_mall",
			"movie_theater" };
	public static String API_KEY = "AIzaSyDRWHEwOfJy_V4y0dNahEvWWncfKG7Jtrw";

	public static ListSupport contains(String title) {
		for (int i = 0; i < titles.length; i++) {
			if (titles[i].equalsIgnoreCase(title)) {
				ListSupport listSupport = new ListSupport();
				listSupport.setImageName(images[i]);
				listSupport.setTitle(titles[i]);
				return listSupport;
			}
		}
		return null;
	}

	public static int getCount() {
		return images.length;
	}

}
