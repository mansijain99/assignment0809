package com.phoenix.controllers.rest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
 * @author mansi.jain@stltech.in
 * @version 1.0
 * @creation_date 02-aug-2021
 * @copyright Sterlite Technologies Ltd.
 * @description RESTful Web Service for Product Resource using Spring Web MVC
 */
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.entities.Product;
import com.phoenix.repositories.ProductRepository;

@RequestMapping("/products")
@RestController
public class ProductRestController {
	
	//if we are autowiring then in mean class we have create Bean
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	@GetMapping("/{pid}")
	public Object getById(@PathVariable("pid") int id) {
		Optional<Product> op = productRepo.findById(id);
		if(op.isPresent())
			return op.get();
		else
			return "Product is not found";
	}
	
	//insert new product if new id or update existing product
	@PutMapping //using http method PUT
	public String addProduct(@RequestBody Product product) {
		productRepo.save(product);
		return "Product" +product.getId()+" is added successfully";
	}
	
	//update existing product
	@PostMapping
	public String updateProduct(@RequestBody Product product) {
		Optional<Product> op = productRepo.findById(product.getId());
		if(op.isPresent()) {
			product = productRepo.save(product);
			return "New Product" +product.getId()+" is updated successfully";
		}else {
				return "Sorry! product is not found";
			}
	}
	
	@DeleteMapping
	public String deleteProduct(@RequestBody Product product) {
		Optional<Product> op = productRepo.findById(product.getId());
		if(op.isPresent()) {
			productRepo.delete(product);
			return "New Product" +product.getId()+" is deleted successfully";
		}else {
				return "Sorry! product is not found";
			}
	}
	
	@PostMapping("/add-form")
	public String addFormData(@RequestParam("nm") String name,@RequestParam("brand")  String brand,@RequestParam("price")  float price) {
		Product p = new Product();
		p.setName(name);
		p.setBrand(brand);
		p.setPrice(price);
		p = productRepo.save(p);
		//return "Form data with product: "+p.getId()+" is added successfully";
		return "redirect:/success"; //doesn't work for RestController
	}
	
	@GetMapping("/{nm}/{br}")
	public List<Product> getProductsByNameAndBrand(@PathVariable("nm") String name,@PathVariable("brand") String brand){
		return productRepo.findByNameAndBrand(name, brand);
	}
	
	@GetMapping("/product-name/{nm}")
	public List<Product> getProductsByName(@PathVariable("nm") String name){
		return productRepo.findByName(name);
	}
	
	public ResponseEntity<String> getSuccessMessage(){
		return ResponseEntity.ok("Congratulation! Product is saved duccessfully");
	}

}
