package com.hackaton.olaadon.images;

import java.io.InputStream;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.hack.olahack.R;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
	ImageView bmImage;
	ProgressDialog dialog;
	Context context;

	public ImageDownloader(ImageView bmImage, Context context) {
		this.context = context;
		this.bmImage = bmImage;
	}

	protected Bitmap doInBackground(String... urls) {
		Bitmap mIcon = null;
		try {
			String url = urls[0];
			InputStream in = new java.net.URL(url).openStream();
			mIcon = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			return null;
		}
		return mIcon;
	}

	protected void onPostExecute(Bitmap result) {
		if (result == null) {
			bmImage.setImageResource(R.drawable.not_available);
		} else {
			bmImage.setImageBitmap(result);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
}