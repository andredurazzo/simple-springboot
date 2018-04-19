package br.com.moip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.moip.domain.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
