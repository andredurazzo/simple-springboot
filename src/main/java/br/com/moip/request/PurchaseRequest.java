package br.com.moip.request;

import java.io.Serializable;

import br.com.moip.domain.Buyer;
import br.com.moip.domain.Card;
import br.com.moip.domain.Client;
import br.com.moip.domain.Payment;
import br.com.moip.domain.Purchase;

public class PurchaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5828345768542209075L;
	private Client client;
	private Buyer buyer;
	private Payment payment;
	private Card card;

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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public PurchaseRequest(Purchase purchase) {
		this.client = purchase.getClient();
		this.buyer = purchase.getBuyer();
		this.payment = purchase.getPayment();

	}

	public PurchaseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
