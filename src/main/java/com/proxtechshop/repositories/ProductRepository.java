package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxtechshop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	
	List<Product> findTop6ByOrderByCreatedDateDesc();
	
	@Query("SELECT p, SUM(o.quantity) AS q "
		+ "FROM Product p "
		+ "LEFT JOIN OrderDetail o "
			+ "ON p.id = o.productId "
		+ "GROUP BY p.id "
		+ "ORDER BY q DESC")
	List<Product> getTopSellingProduct(Pageable pageable);
	
	@Query("SELECT COUNT(p) FROM Product p")
	long count();
	
	@Query(value = "select p from Product p")
	Page<Product> getPaging(Pageable pageable);
	
	List<Product> findAllByNameOrIdContains(String name,String id);
}
