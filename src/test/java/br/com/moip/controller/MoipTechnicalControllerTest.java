package br.com.moip.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.moip.exception.BusinessException;
import br.com.moip.response.ClientResponse;
import br.com.moip.service.IBuyerService;
import br.com.moip.service.IClientService;
import br.com.moip.service.IPaymentService;
import br.com.moip.service.IPurchaseService;

@RunWith(SpringRunner.class)
@WebMvcTest(MoipTechnicalController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MoipTechnicalControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IClientService clientService;

	@MockBean
	private IPurchaseService purchaseService;

	@MockBean
	private IPaymentService paymentService;

	@MockBean
	private IBuyerService buyerService;

	@Test
	public void verifyClientList() throws Exception {
		ClientResponse client = new ClientResponse("1111", "test mock");
		List<ClientResponse> allClients = Arrays.asList(client);

		given(clientService.getClients()).willReturn(allClients);

		mvc.perform(get("/api/client").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(1))).andExpect(jsonPath("$.data[0].name", is(client.getName())))
				.andDo(print());

	}

	@Test
	public void verifyClientListInvalid() throws Exception {
		given(clientService.getClients()).willThrow(new BusinessException("Unavailable Service!"));
		mvc.perform(get("/api/client").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());

	}

	@Test
	public void verifyClientById() throws Exception {
		ClientResponse client = new ClientResponse("1", "test mock");

		given(clientService.findClient(1L)).willReturn(client);

		mvc.perform(get("/api/client/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", not(equalTo(""))))
				.andExpect(jsonPath("$.data.name", equalTo(client.getName()))).andDo(print());

	}

	@Test
	public void verifyNullClient() throws Exception {

		given(clientService.findClient(1L)).willThrow(new Exception("Client not found!"));

		mvc.perform(get("/api/client/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.data", equalTo(null))).andExpect(jsonPath("$.success", equalTo(false))).andDo(print());

	}

}
