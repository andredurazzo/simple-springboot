package br.com.moip.service;

import java.util.List;

import br.com.moip.exception.BusinessException;
import br.com.moip.request.PurchaseRequest;
import br.com.moip.response.PurchaseResponse;

public interface IPurchaseService {

	public PurchaseResponse finalizePurchase(PurchaseRequest purchase) throws BusinessException;

	List<PurchaseResponse> getAllPurchase() throws BusinessException;

	List<PurchaseResponse> getAllPurchase(Long idClient) throws BusinessException;

}
