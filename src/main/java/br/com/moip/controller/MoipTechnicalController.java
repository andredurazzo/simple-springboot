package br.com.moip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.exception.BusinessException;
import br.com.moip.request.PurchaseRequest;
import br.com.moip.response.ClientResponse;
import br.com.moip.response.PurchaseResponse;
import br.com.moip.response.Response;
import br.com.moip.service.IClientService;
import br.com.moip.service.IPaymentService;
import br.com.moip.service.IPurchaseService;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin("*")
public class MoipTechnicalController {

	@Autowired
	IPurchaseService purchaseService;

	@Autowired
	IPaymentService paymentService;

	@Autowired
	IClientService clientService;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(path = "/purchase/lvApi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<PurchaseRequest>> finalizeMockAnswer(@RequestBody PurchaseRequest model)
			throws BusinessException {

		Response<PurchaseRequest> resp = new Response<>();
		model.setPayment(paymentService.addLvlPayment(model.getPayment()));
		resp.setData(model);
		resp.setSuccess(true);
		return new ResponseEntity<Response<PurchaseRequest>>(resp, HttpStatus.OK);

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/purchase/finalize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<PurchaseResponse>> finalizePurchase(@RequestBody PurchaseRequest model) {
		Response<PurchaseResponse> resp = new Response<>();
		try {
			PurchaseResponse finalizePurchase = purchaseService.finalizePurchase(model);
			resp.setData(finalizePurchase);
			resp.setSuccess(true);
			return new ResponseEntity<Response<PurchaseResponse>>(resp, HttpStatus.OK);
		} catch (BusinessException e) {
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
			return new ResponseEntity<Response<PurchaseResponse>>(resp, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/purchase/", method = RequestMethod.GET)
	public ResponseEntity<Response<List<PurchaseResponse>>> listPurchase() {
		Response<List<PurchaseResponse>> resp = new Response<>();
		try {
			List<PurchaseResponse> allPurchase = purchaseService.getAllPurchase();
			resp.setData(allPurchase);
			resp.setSuccess(true);
			return new ResponseEntity<Response<List<PurchaseResponse>>>(resp, HttpStatus.OK);
		} catch (BusinessException e) {
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
			return new ResponseEntity<Response<List<PurchaseResponse>>>(resp, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/purchase/:id", method = RequestMethod.GET)
	public ResponseEntity<Response<List<PurchaseResponse>>> listPurchase(@PathVariable(name = "id") Long id) {
		Response<List<PurchaseResponse>> resp = new Response<>();
		try {
			List<PurchaseResponse> allPurchase = purchaseService.getAllPurchase(id);
			resp.setData(allPurchase);
			resp.setSuccess(true);
			return new ResponseEntity<Response<List<PurchaseResponse>>>(resp, HttpStatus.OK);
		} catch (BusinessException e) {
			resp.setMessage(e.getMessage());
			resp.setSuccess(false);
			return new ResponseEntity<Response<List<PurchaseResponse>>>(resp, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/client", method = RequestMethod.GET)
	public ResponseEntity<Response<List<ClientResponse>>> getClients() throws BusinessException {
		Response<List<ClientResponse>> resp = new Response<>();
		resp.setData(clientService.getClients());
		resp.setSuccess(true);
		return new ResponseEntity<Response<List<ClientResponse>>>(resp, HttpStatus.OK);

	}

	@RequestMapping(path = "/client/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<ClientResponse>> getClientId(@PathVariable("id") Long id)
			throws BusinessException, Exception {
		Response<ClientResponse> resp = new Response<>();
		resp.setData(clientService.findClient(id));
		resp.setSuccess(true);
		return new ResponseEntity<Response<ClientResponse>>(resp, HttpStatus.OK);
	}

}
