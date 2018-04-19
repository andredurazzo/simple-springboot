package br.com.moip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.moip.domain.Buyer;
import br.com.moip.service.IBuyerService;
import br.com.moip.service.IClientService;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner initTech(IClientService cService, IBuyerService bRepository) {
		return (args) -> {
			log.info("-------------------------------");
			log.info("Initiate mock");
			log.info("-------------------------------");
			log.info("");
			log.info("-------------------------------");
			log.info("Save Client");
			// save a couple of customers
			cService.addClient("Client Xpto");
			cService.addClient("Client Otpx");
			
			log.info("Save Buyer");
			bRepository.addBuyer(new Buyer( "buyer1", "buyer.1@bbb.com", 12345678901L));
			bRepository.addBuyer(new Buyer( "buyer2", "buyer.2@bbb.com", 12345678900L));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			log.info("");
		};
	}

}
