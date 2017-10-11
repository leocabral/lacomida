package br.com.lacomida.home.model;

import org.parceler.Parcel;

@Parcel
public class Restaurant {

	Long id;
	String name;
	String imageUrl;
	String description;
	String address;
	Float rating;
	Float deliveryFee;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public Float getRating() {
		return rating;
	}

	public Float getDeliveryFee() {
		return deliveryFee;
	}

	public Restaurant setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}
}
