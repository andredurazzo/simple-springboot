package br.com.moip.service;

import br.com.moip.domain.Buyer;
import br.com.moip.exception.BusinessException;

public interface IBuyerService {

	Buyer addBuyer(Buyer buyer) throws BusinessException;

	Buyer findBuyer(Buyer buyer) throws BusinessException;
	
	
}
