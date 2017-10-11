package br.com.lacomida.cart.model;

public class CheckoutStatus {

	String status;
	String message;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public CheckoutStatus setMessage(String message) {
		this.message = message;
		return this;
	}

	public CheckoutStatus setStatus(String status) {
		this.status = status;
		return this;
	}
}