package br.com.moip.service;

import br.com.moip.domain.Card;
import br.com.moip.exception.BusinessException;

public interface ICardService {

	
	Card addCreditCard(Card card) throws BusinessException;
	
}
