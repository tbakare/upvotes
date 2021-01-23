package com.upvotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upvotes.domain.Product;
import com.upvotes.domain.User;

public interface ProductRepository extends JpaRepository <Product,Integer>{
	
	@Query("select p from Product p" + " join fetch p.user" + " where p.id = :id")
	Optional<Product> findByIdWithUser(Integer id);
	List<Product> findByUser(User user);
	Optional<Product> findByName(String name);

}
