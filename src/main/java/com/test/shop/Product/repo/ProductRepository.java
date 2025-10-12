package com.test.shop.Product.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com. test.shop.Product.product;
@Repository
public interface ProductRepository extends JpaRepository<product, Integer> {
    
    
    List<product> findByName(String name);
    List<product> findByPriceLessThanEqual(Integer price);
    List<product> findByQuantityGreaterThanEqual(Integer quantity);
    List<product> findByCategoryId(Integer categoryId);
    List<product> findByNameContainingIgnoreCase(String name);
}

