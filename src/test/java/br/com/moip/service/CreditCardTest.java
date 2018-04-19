package br.com.moip.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.moip.repository.CardRepository;
import br.com.moip.service.impl.CardServiceImpl;
import br.com.moip.utils.CreditCardUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardTest {

	@MockBean
	CardRepository cardRepository;

	@InjectMocks
	private CardServiceImpl cardService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validCreditCard() throws Exception {

		String numberCreditCard = "5502 0948 3494 8411";

		boolean validCC = CreditCardUtils.validCC(numberCreditCard);

		assertTrue(validCC);
	}

	@Test
	public void invalidCreditCard() throws Exception {

		String numberCreditCard = "1111 0948 3494 8411";

		boolean validCC = CreditCardUtils.validCC(numberCreditCard);

		assertFalse(validCC);
	}

	@Test
	public void invalidCharCreditCard() throws Exception {

		String numberCreditCard = "asdasdasdasdsadas";

		boolean validCC = CreditCardUtils.validCC(numberCreditCard);

		assertFalse(validCC);
	}

}
