package com.hack.olahack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class CalenderListExtractor {

	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<Long> startDates = new ArrayList<Long>();
	public static ArrayList<Long> endDates = new ArrayList<Long>();
	public static ArrayList<String> descriptions = new ArrayList<String>();
	public static ArrayList<String> eventLocation = new ArrayList<String>();

	public static ArrayList<String> readCalendarEvent(Context context) {
		Cursor cursor = context.getContentResolver()
				.query(Uri.parse("content://com.android.calendar/events"),
						new String[] { "calendar_id", "title", "description",
								"dtstart", "dtend", "eventLocation" }, null,
						null, null);
		cursor.moveToFirst();
		// fetching calendars name
		String CNames[] = new String[cursor.getCount()];

		// fetching calendars id
		nameOfEvent.clear();
		startDates.clear();
		endDates.clear();
		descriptions.clear();
		// String[] columnNames = cursor.getColumnNames() ;
		//
		// for (String dd : columnNames) {
		// Log.e("cursor column names",dd) ;
		// }
		//

		for (int i = 0; i < CNames.length; i++) {
//			System.out.println(cursor.getString(1) + "   "
//					+ getDate(Long.parseLong(cursor.getString(3))));
			nameOfEvent.add(cursor.getString(1));
			startDates.add((Long.parseLong(cursor.getString(3))));
			endDates.add((Long.parseLong(cursor.getString(4))));
			descriptions.add(cursor.getString(2));
			eventLocation.add(cursor.getString(5));

			CNames[i] = cursor.getString(1);
			cursor.moveToNext();

		}
		return descriptions;
	}

	public static String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss a");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

}