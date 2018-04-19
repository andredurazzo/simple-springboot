package br.com.moip.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.moip.domain.Client;
import br.com.moip.exception.BusinessException;
import br.com.moip.repository.ClientRepository;
import br.com.moip.response.ClientResponse;
import br.com.moip.service.impl.ClientServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(ClientServiceTest.class);
	
	@MockBean
	private ClientRepository clientRepository;
	
	@InjectMocks
	private ClientServiceImpl clientService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllClient() throws BusinessException{
		List<Client> clietList = new ArrayList<Client>();
		clietList.add(new ClientResponse("1","Todo Sample 1").toClient());
		clietList.add(new ClientResponse("2","Todo Sample 2").toClient());
		when(clientRepository.findAll()).thenReturn(clietList);
		
		List<ClientResponse> result = clientService.getClients();
		log.info("---------------");
		log.info(result.toArray().toString());
		assertEquals(2, result.size());
	}
	

}
