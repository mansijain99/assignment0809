package com.phoenix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phoenix.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	//custom finder method
	List<Product> findByName(String name);
	List<Product> findByBrand(String brand);
	List<Product> findByPrice(float price);
	List<Product> findByPriceBetween(float minprice, float maxPrice);
	List<Product> findByNameAndBrand(String name,String brand);
	List<Product> findByNameAndPrice(String name,float price);
	List<Product> findByNameLike(String namePattern);
	
	
	List<Product> findByNameOrderByPrice(String name);
	List<Product> findByNameOrderByPriceDesc(String name);
	
	
	List<Product> OrderByBrand();
	List<Product> getProductsByName(String name);
	/*
	 * @Query("select * from product order by name") List<Product>
	 * sortByName();//not working
	 */	
	@Query("select p from Product p where p.name = ?1 and p.price = ?2")
	List<Product> getProductsByNameAndPrice(String name,float price);
	
	/*
	 * @Modifying
	 * 
	 * @Query("update product set price = price + :pr where brand =:br")
	 * List<Product> updatePriceByBrand(@Param("br")String brand,@Param("pr")float
	 * price);
	 * 
	 */
	/*
	 * @Modifying
	 * 
	 * @Query("delete p from Product p where p.name = ?1") List<Product>
	 * deleteProductByName(String name);
	 */
	 
	

}
