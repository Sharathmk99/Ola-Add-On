package com.hack.olahack.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	Context context;
	String ttfName;

	String TAG = getClass().getName();

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			/*
			 * Read value of custom attributes
			 */

			this.ttfName = attrs.getAttributeValue(
					"http://schemas.android.com/apk/res/com.hack.olahack",
					"ttf_name");
			// Log.i(TAG, "lastText "+ lastText);

			init();
		}

	}

	private void init() {
		Typeface font = Typeface.createFromAsset(context.getAssets(), ttfName);
		setTypeface(font);
	}

	@Override
	public void setTypeface(Typeface tf) {

		// TODO Auto-generated method stub
		super.setTypeface(tf);
	}

}