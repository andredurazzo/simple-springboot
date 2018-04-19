package br.com.moip.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.moip.enums.PAYMENT_TYPE;
import br.com.moip.enums.PAYMENT_STATUS;

@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 5733677128531053123L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double amount;

	private PAYMENT_TYPE type;

	@ManyToOne(optional = true)
	@JoinColumn(referencedColumnName  = "id")
	private Card card;

	private String numberBoleto;

	private PAYMENT_STATUS status;

	public Payment(Long id, Double amount, PAYMENT_TYPE type, Card card, String numberBoleto, PAYMENT_STATUS status) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.card = card;
		this.numberBoleto = numberBoleto;
		this.status = status;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PAYMENT_TYPE getType() {
		return type;
	}

	public void setType(PAYMENT_TYPE type) {
		this.type = type;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getNumberBoleto() {
		return numberBoleto;
	}

	public void setNumberBoleto(String numberBoleto) {
		this.numberBoleto = numberBoleto;
	}

	public PAYMENT_STATUS getStatus() {
		return status;
	}

	public void setStatus(PAYMENT_STATUS status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numberBoleto == null) ? 0 : numberBoleto.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numberBoleto == null) {
			if (other.numberBoleto != null)
				return false;
		} else if (!numberBoleto.equals(other.numberBoleto))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
