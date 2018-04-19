package br.com.moip.service;

import java.util.List;

import br.com.moip.domain.Payment;
import br.com.moip.exception.BusinessException;

public interface IPaymentService {

	Payment addPayment(Payment payment) throws BusinessException;

	Payment findPayment() throws BusinessException;

	List<Payment> listPayments() throws BusinessException;

	List<Payment> listAllPayments() throws BusinessException;

	Payment addLvlPayment(Payment payment) throws BusinessException;
}
