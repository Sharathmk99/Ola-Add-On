package com.hackaton.olaadon.images;

public class MakeUrlForPhotoReference {
	String apiKey, photoReference;

	public MakeUrlForPhotoReference(String apiKey, String photoReference2) {
		this.apiKey = apiKey;
		this.photoReference = photoReference2;
	}

	public String getUrl() {
		StringBuilder urlString = new StringBuilder(
				"https://maps.googleapis.com/maps/api/place/photo?");
		urlString.append("maxwidth=10000");
		urlString.append("&photoreference=");
		urlString.append(photoReference);
		urlString.append("&key=" + apiKey);

		return urlString.toString();

	}

}
