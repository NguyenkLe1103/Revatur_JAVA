package com.test.shop.Product.Service;

import com.test.shop.Product.Category;
import com.test.shop.Product.Exception.ResourceNotFoundException;
import com.test.shop.Product.Product;
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

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public List<Product> listByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product getOrThrow(int id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Product %d not found".formatted(id)));
    }

    @Transactional
    public Product create(ProductRequest req) {
        // 👇 This is the key link to Category
        Category cat = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category %d not found".formatted(req.categoryId())));

        Product p = Product.builder()
        .name(req.name())
        .description(req.description())
        .price(req.price())
        .category(cat)
        .build();

    return productRepository.save(p);
}

    @Transactional
    public Product update(int id, ProductRequest req) {
        Product existing = getOrThrow(id);
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
        Product existing = getOrThrow(id);
        productRepository.delete(existing);
    }
}
