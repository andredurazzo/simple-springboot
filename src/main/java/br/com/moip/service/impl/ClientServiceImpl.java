package br.com.moip.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moip.domain.Client;
import br.com.moip.exception.BusinessException;
import br.com.moip.repository.ClientRepository;
import br.com.moip.response.ClientResponse;
import br.com.moip.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client addClient(String name) {
		return clientRepository.save(new Client(Math.abs(new Random().nextLong()), name));
	}

	@Override
	public List<ClientResponse> getClients() throws BusinessException {
		List<Client> targetCollection = new ArrayList<Client>();
		List<ClientResponse> response = new ArrayList<ClientResponse>();
		CollectionUtils.addAll(targetCollection, clientRepository.findAll().iterator());
		targetCollection.forEach((value) ->{
			response.add(new ClientResponse(value.getId().toString(), value.getName()));
		});
		return response;
	}

	@Override
	public ClientResponse findClient(Long id) throws BusinessException, Exception {
		Client findById = clientRepository.findClient(id);
		
		return new ClientResponse(findById.getId().toString(), findById.getName());
	}

}
