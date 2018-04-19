package br.com.moip.repository;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.moip.configuration.H2ConfigurationTest;
import br.com.moip.domain.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H2ConfigurationTest.class })
@ActiveProfiles("test")
@Transactional
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	@Transactional
	public void instanceOf() {
		assertNotNull(this.clientRepository);
	}
	
	@Test
	public void testAddClient() {
		Client client = clientRepository.save(new Client(123456987L, "mock test"));
		assertNotNull(client);
	}
	
	@Test
	public void testFindClientById() {
		Client client = clientRepository.save(new Client(123456987L, "mock test"));
		Client clientTest = clientRepository.findClient(client.getId());
		assertNotNull(clientTest);
	}
}