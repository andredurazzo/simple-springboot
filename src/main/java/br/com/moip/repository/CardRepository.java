package br.com.moip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moip.domain.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

}
