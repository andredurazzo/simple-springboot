package br.com.moip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moip.domain.Purchase;
import br.com.moip.exception.BusinessException;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	
	
	Iterable<Purchase> findByClientId(Long id) throws BusinessException;

}
