package br.com.moip.service;

import java.util.List;

import br.com.moip.domain.Client;
import br.com.moip.exception.BusinessException;
import br.com.moip.response.ClientResponse;

public interface IClientService  {
	
	Client addClient(String name);
		
	List<ClientResponse> getClients() throws BusinessException;

	ClientResponse findClient(Long id) throws BusinessException, Exception;
}
