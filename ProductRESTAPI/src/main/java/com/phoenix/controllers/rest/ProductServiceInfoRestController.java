package com.phoenix.controllers.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.entities.Product;
import com.phoenix.repositories.ProductRepository;
import com.phoenix.utilities.ProductServiceInfo;

@RequestMapping("/product-info")
@RestController
public class ProductServiceInfoRestController {
	
	@Value("${server.port}")
	private int serverPort;
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/product/{id}")
	public ProductServiceInfo getServiceInfo(@PathVariable int id) {
		Optional<Product> op = repo.findById(id);
		Product product = op.get();
		return new ProductServiceInfo(product, serverPort);
	}

}
