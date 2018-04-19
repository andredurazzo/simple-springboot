package br.com.moip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import br.com.moip.domain.Buyer;
import br.com.moip.domain.Card;
import br.com.moip.domain.Payment;
import br.com.moip.domain.Purchase;
import br.com.moip.enums.PAYMENT_STATUS;
import br.com.moip.enums.PAYMENT_TYPE;
import br.com.moip.exception.BusinessException;
import br.com.moip.exception.CreditCardException;
import br.com.moip.repository.PurchaseRepository;
import br.com.moip.request.PurchaseRequest;
import br.com.moip.response.ClientResponse;
import br.com.moip.response.PurchaseResponse;
import br.com.moip.service.IBuyerService;
import br.com.moip.service.ICardService;
import br.com.moip.service.IClientService;
import br.com.moip.service.IPaymentService;
import br.com.moip.service.IPurchaseService;
import br.com.moip.utils.BoletoGenerate;
import br.com.moip.utils.CreditCardUtils;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	IBuyerService buyerService;

	@Autowired
	IPaymentService paymentService;

	@Autowired
	ICardService cardService;

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	IClientService clientService;

	@Override
	public PurchaseResponse finalizePurchase(PurchaseRequest purchase) throws BusinessException {

		try {
			Future<Buyer> buyerF = this.asyncSaveBuyer(purchase.getBuyer());
			Future<Card> cardF = null;
			
			Future<ClientResponse> clientF = this.asyncFindClient(purchase.getClient().getId());
			if (null != purchase.getPayment() && purchase.getPayment().getType().equals(PAYMENT_TYPE.BOLETO)) {
				purchase.getPayment().setNumberBoleto(BoletoGenerate.generateBoleto());
			} else {
				if (CreditCardUtils.validCC(purchase.getCard().getCardNumber())) {
					cardF = this.asyncSaveCard(purchase.getCard());
					if (!cardF.isDone()) {
						Thread.sleep(2);
					}
					purchase.getPayment().setCard(cardF.get());
				} else {
					throw new CreditCardException("Invalid Credit card!");
				}
			}
			purchase.getPayment().setStatus(PAYMENT_STATUS.AWAITING_PAYMENT);
			Future<Payment> paymentF = this.asyncSavePayment(purchase.getPayment());
			while (!buyerF.isDone() && !paymentF.isDone() && !clientF.isDone()) {
				Thread.sleep(2);
			}
			
			//purchase.setClient(clientF.get());
			purchase.setBuyer(buyerF.get());
			purchase.setPayment(paymentF.get());
			
			
			Future<Purchase> purchaseF = this.asyncSavePurchase(this.toRepoPurchase(purchase));
			while(!purchaseF.isDone())
				Thread.sleep(2);
			return this.toPurchaseResponse(purchaseF.get());

		} catch (InterruptedException e) {
			throw new BusinessException(e);
		} catch (ExecutionException e) {
			throw new BusinessException(e);
		} catch (CreditCardException e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<PurchaseResponse> getAllPurchase() throws BusinessException {
		List<PurchaseResponse> resp = new ArrayList<PurchaseResponse>();
		purchaseRepository.findAll().forEach((purchase) -> {
			resp.add(toPurchaseResponse(purchase));

		});
		return resp;
	}

	@Override
	public List<PurchaseResponse> getAllPurchase(Long idClient) throws BusinessException {
		List<PurchaseResponse> resp = new ArrayList<PurchaseResponse>();
		try {
			purchaseRepository.findByClientId(idClient).forEach((purchase) -> {
				resp.add(toPurchaseResponse(purchase));

			});
			return resp;
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	private Purchase toRepoPurchase(PurchaseRequest purchaseModel) {
		return new Purchase(purchaseModel.getClient(), purchaseModel.getBuyer(), new Date(),
				purchaseModel.getPayment());
	}

	private PurchaseResponse toPurchaseResponse(Purchase purchase) {
		return new PurchaseResponse(purchase);
	}

	@Async
	private Future<Buyer> asyncSaveBuyer(Buyer buyer) {
		Buyer result = null;
		try {	
			result = buyerService.findBuyer(buyer);
			if(null == result){
				result = buyerService.addBuyer(buyer);
			}
			return new AsyncResult<Buyer>(result);
		} catch (BusinessException e) {
			return new AsyncResult<Buyer>(result);
		}
	}

	@Async
	private Future<Payment> asyncSavePayment(Payment payment) {
		Payment result = null;

		try {
			result = paymentService.addPayment(payment);
			return new AsyncResult<Payment>(result);
		} catch (BusinessException e) {
			return new AsyncResult<Payment>(result);
		}

	}

	@Async
	private Future<Card> asyncSaveCard(Card card) {
		Card result = null;

		try {
			return new AsyncResult<Card>(cardService.addCreditCard(card));
		} catch (BusinessException e) {
			return new AsyncResult<Card>(result);
		}
	}
	
	@Async
	private Future<ClientResponse> asyncFindClient(Long id){
		
		try {
			return new AsyncResult<ClientResponse>(clientService.findClient(id));
		} catch (BusinessException e) {
			return new AsyncResult<ClientResponse>(null);
		} catch(Exception e) {
			return new AsyncResult<ClientResponse>(null);
		}
	}
	@Async
	private Future<Purchase> asyncSavePurchase(Purchase purchase){
		try {
			return new AsyncResult<Purchase>(purchaseRepository.save(purchase));
		} catch (Exception e) {
			return new AsyncResult<Purchase>(null);
		}
		
	}

}
