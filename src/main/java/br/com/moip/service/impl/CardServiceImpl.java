package br.com.moip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.domain.Card;
import br.com.moip.exception.BusinessException;
import br.com.moip.exception.CreditCardException;
import br.com.moip.repository.CardRepository;
import br.com.moip.service.ICardService;
import br.com.moip.utils.CreditCardUtils;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	CardRepository cardRepository;

	@Override
	public Card addCreditCard(Card card) throws BusinessException, CreditCardException {

		try {
			if (CreditCardUtils.validCC(card.getCardNumber())) {
				return cardRepository.save(card);
			} else {
				throw new CreditCardException("Invalid Credit card!");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
