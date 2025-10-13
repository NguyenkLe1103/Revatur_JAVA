package com.test.shop.Product.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com. test.shop.Product.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    
    List<Product> findByName(String name);
    List<Product> findByPriceLessThanEqual(Integer price);
    List<Product> findByQuantityGreaterThanEqual(Integer quantity);
    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
}

