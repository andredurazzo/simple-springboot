package br.com.moip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moip.domain.Buyer;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {

	
	public Buyer findByCpf(Long cpf);
	
}
