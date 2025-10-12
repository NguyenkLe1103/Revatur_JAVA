package com.test.shop.Product.repo;

import com.test.shop.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    boolean existsByNameIgnoreCase(String name);
}