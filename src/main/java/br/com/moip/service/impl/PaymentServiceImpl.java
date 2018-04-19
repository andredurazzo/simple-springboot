package br.com.moip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.domain.Card;
import br.com.moip.domain.Payment;
import br.com.moip.enums.PAYMENT_STATUS;
import br.com.moip.enums.PAYMENT_TYPE;
import br.com.moip.exception.BusinessException;
import br.com.moip.repository.PaymentRepository;
import br.com.moip.service.IPaymentService;
import br.com.moip.utils.BoletoGenerate;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment findPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> listPayments() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> listAllPayments() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment addLvlPayment(Payment payment) throws BusinessException {

		if (payment.getType().equals(PAYMENT_TYPE.BOLETO)) {
			payment.setNumberBoleto(BoletoGenerate.generateBoleto());

		} else {
			payment.setCard(new Card(true));

		}
		payment.setStatus(PAYMENT_STATUS.AWAITING_PAYMENT);
		return payment;

	}

	@Override
	public Payment addPayment(Payment payment) throws BusinessException {

		try {

			return paymentRepository.save(payment);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		// TODO Auto-generated method stub

	}

}
