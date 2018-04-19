package br.com.moip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.domain.Buyer;
import br.com.moip.exception.BusinessException;
import br.com.moip.repository.BuyerRepository;
import br.com.moip.service.IBuyerService;

@Service
public class BuyerServiceImpl implements IBuyerService {

	@Autowired
	BuyerRepository buyerRepository;

	@Override
	public Buyer addBuyer(Buyer buyer) throws BusinessException {
		try {
			return buyerRepository.save(buyer);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Buyer findBuyer(Buyer buyer) throws BusinessException {
		try {
			return buyerRepository.findByCpf(buyer.getCpf());
		} catch (Exception e) {
			throw new BusinessException("Buyer not exists!");
		}

	}

}
