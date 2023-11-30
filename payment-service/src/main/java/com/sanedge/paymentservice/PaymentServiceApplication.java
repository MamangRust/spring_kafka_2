package com.sanedge.paymentservice;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.sanedge.base.domain.Order;
import com.sanedge.paymentservice.domain.Customer;
import com.sanedge.paymentservice.repository.CustomerRepository;
import com.sanedge.paymentservice.service.OrderManageService;

import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;

@SpringBootApplication
public class PaymentServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Autowired
	OrderManageService orderManageService;

	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
	public void onEvent(Order o) {
		LOG.info("Received: {}", o);
		if (o.getStatus().equals("NEW"))
			orderManageService.reserve(o);
		else
			orderManageService.confirm(o);
	}

	@Autowired
	private CustomerRepository repository;

	@PostConstruct
	public void generateData() {
		Random r = new Random();
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			int count = r.nextInt(100, 1000);
			Customer c = new Customer(null, faker.name().fullName(), count, 0);
			repository.save(c);
		}
	}

}
