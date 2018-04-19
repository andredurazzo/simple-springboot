package br.com.moip.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moip.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	
	@Query("select c from Client c where c.id = ?1")
	Client findClient(Long id);

}
