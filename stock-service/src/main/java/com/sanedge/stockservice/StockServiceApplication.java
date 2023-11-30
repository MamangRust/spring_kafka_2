package com.sanedge.stockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.sanedge.base.domain.Order;
import com.sanedge.stockservice.domain.Product;
import com.sanedge.stockservice.repository.ProductRepository;
import com.sanedge.stockservice.service.OrderManageService;
import java.util.Random;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableKafka
public class StockServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(StockServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

	@Autowired
	OrderManageService orderManageService;

	@KafkaListener(id = "orders", topics = "orders", groupId = "stock")
	public void onEvent(Order o) {
		LOG.info("Received: {}", o);
		if (o.getStatus().equals("NEW"))
			orderManageService.reserve(o);
		else
			orderManageService.confirm(o);
	}

	@Autowired
	private ProductRepository repository;

	@PostConstruct
	public void generateData() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int count = r.nextInt(10, 1000);
			Product p = new Product(null, "Product" + i, count, 0);
			repository.save(p);
		}
	}

}
