package com.test.shop.Product.Service;

import com.test.shop.Product.Category;
import com.test.shop.Product.Exception.ResourceNotFoundException;
import com.test.shop.Product.product;
import com.test.shop.Product.Request.ProductRequest;
import com.test.shop.Product.repo.CategoryRepo;
import com.test.shop.Product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepo categoryRepository;

    public List<product> listAll() {
        return productRepository.findAll();
    }

    public List<product> listByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public product getOrThrow(int id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Product %d not found".formatted(id)));
    }

    @Transactional
    public product create(ProductRequest req) {
        // 👇 This is the key link to Category
        Category cat = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category %d not found".formatted(req.categoryId())));

        product p = new product();
        p.setName(req.name());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setCategory(cat);
        return productRepository.save(p);
    }

    @Transactional
    public product update(int id, ProductRequest req) {
        product existing = getOrThrow(id);
        Category cat = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category %d not found".formatted(req.categoryId())));

        existing.setName(req.name());
        existing.setDescription(req.description());
        existing.setPrice(req.price());
        existing.setCategory(cat);
        return productRepository.save(existing);
    }

    @Transactional
    public void delete(int id) {
        product existing = getOrThrow(id);
        productRepository.delete(existing);
    }
}
