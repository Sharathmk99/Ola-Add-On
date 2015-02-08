package com.hack.datainterfaces;

public class PlaceDetails implements Comparable<PlaceDetails>{
	
	private String isOpen ;
	private Integer photoWidth ;
	private String photoReference ;
	private Double rating ;
	private String address ;
	private Double lat ;
	private Double lng ;
	private String name;
	public String isOpen() {
		return isOpen;
	}
	public void setOpen(String string) {
		this.isOpen = string;
	}
	public Integer getPhotoWidth() {
		return photoWidth;
	}
	public void setPhotoWidth(Integer photoWidth) {
		this.photoWidth = photoWidth;
	}
	public String getPhotoReference() {
		return photoReference;
	}
	public void setPhotoReference(String photoReference) {
		this.photoReference = photoReference;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ListOfPlaces [isOpen=" + isOpen + ", photoWidth=" + photoWidth
				+ ", photoReference=" + photoReference + ", rating=" + rating
				+ ", address=" + address + ", lat=" + lat + ", lng=" + lng
				+ ", name=" + name + "]";
	}
	@Override
	public int compareTo(PlaceDetails another) {
		
		return 0;
	} 
	
	
	
	
	

}
