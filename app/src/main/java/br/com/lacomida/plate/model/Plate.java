package br.com.lacomida.plate.model;

import org.parceler.Parcel;

@Parcel
public class Plate {

	Long id;
	String name;
	String imageUrl;
	String description;
	Float price;

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

	public Float getPrice() {
		return price;
	}

	public Plate setId(Long id) {
		this.id = id;
		return this;
	}

	public Plate setName(String name) {
		this.name = name;
		return this;
	}

	public Plate setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Plate setDescription(String description) {
		this.description = description;
		return this;
	}

	public Plate setPrice(Float price) {
		this.price = price;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Plate plate = (Plate) o;

		return id != null ? id.equals(plate.id) : plate.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}