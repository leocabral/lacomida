package br.com.lacomida.cart.model;

import org.parceler.Parcel;

@Parcel
public class PaymentMethod {

	Long id;
	String name;
	Boolean enabled;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getEnabled() {
		return enabled;
	}
}