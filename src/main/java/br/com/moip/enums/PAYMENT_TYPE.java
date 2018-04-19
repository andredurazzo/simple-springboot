package br.com.moip.enums;

public enum PAYMENT_TYPE {

	CREDIT_CARD(0), BOLETO(1);

	private final int id;

	PAYMENT_TYPE(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}

}
