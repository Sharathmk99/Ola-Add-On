package com.hack.olahack;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AutoPopUp extends Activity {

	TextView header, starttime, eta, destplace;
	Button rideNow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		header = (TextView) findViewById(R.id.head);
		starttime = (TextView) findViewById(R.id.start);
		eta = (TextView) findViewById(R.id.eta);
		destplace = (TextView) findViewById(R.id.destina);
		rideNow = (Button) findViewById(R.id.booknow);
		rideNow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AutoPopUp.this, "Booking confermed",
						Toast.LENGTH_LONG).show();
			}
		});
		header.setText(AutoBookService.nameOfEvent);
		
		
		starttime.setText(new SimpleDateFormat("dd-MMM-yyyy hh:mm a").format(new Date(AutoBookService.nextTimeInMillis))) ;
		
		eta.setText(AutoBookService.etaTime11) ;
		destplace.setText(AutoBookService.locationDestination) ;
	}

}
