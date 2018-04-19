package br.com.moip.enums;

public enum PAYMENT_STATUS {
	AWAITING_PAYMENT(0), PROCESSING(1), PAID(2), CANCELED(3);

	private final int id;

	PAYMENT_STATUS(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}

}
