package br.com.moip.response;

import java.io.Serializable;

import br.com.moip.domain.Buyer;
import br.com.moip.domain.Client;
import br.com.moip.domain.Payment;
import br.com.moip.domain.Purchase;

public class PurchaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3963301646015428324L;

	private Client client;
	private Buyer buyer;
	private Payment payment;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public PurchaseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseResponse(Purchase purchase) {
		this.client = purchase.getClient();
		this.buyer = purchase.getBuyer();
		this.payment = purchase.getPayment();

	}

	public PurchaseResponse(Client client, Buyer buyer, Payment payment) {
		super();
		this.client = client;
		this.buyer = buyer;
		this.payment = payment;
	}

}
