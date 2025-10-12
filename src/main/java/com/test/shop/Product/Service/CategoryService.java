package com.test.shop.Product.Service;

import com.test.shop.Product.Exception.BadRequestException;
import com.test.shop.Product.Exception.ResourceNotFoundException;
import com.test.shop.Product.Category;
import com.test.shop.Product.Request.CategoryRequest;
import com.test.shop.Product.repo.CategoryRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category getOrThrow(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category %d not found".formatted(id)));
    }

    @Transactional
    public Category create(CategoryRequest req) {
        if (categoryRepository.existsByNameIgnoreCase(req.name())) {
            throw new BadRequestException("Category name already exists");
        }
        Category c = new Category();
        c.setName(req.name());
        c.setDescription(req.description());
        return categoryRepository.save(c);
    }

    @Transactional
    public Category update(Integer id, CategoryRequest req) {
        Category existing = getOrThrow(id);
        existing.setName(req.name());
        existing.setDescription(req.description());
        return categoryRepository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Category existing = getOrThrow(id);
        categoryRepository.delete(existing);
    }
}
