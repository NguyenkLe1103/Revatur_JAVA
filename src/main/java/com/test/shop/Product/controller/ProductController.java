package com.test.shop.Product.controller;

import com.test.shop.Product.Service.ProductService;

import jakarta.validation.Valid;

import com.test.shop.Product.Request.ProductRequest;
import com.test.shop.Product.product;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // List all or by category
    @GetMapping
    public List<product> list(@RequestParam(required = false) Integer categoryId) {
        return (categoryId == null)
                ? productService.listAll()
                : productService.listByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public product get(@PathVariable int id) {
        return productService.getOrThrow(id);
    }

    @PostMapping
    public ResponseEntity<product> create(@Valid @RequestBody ProductRequest req) {
        product created = productService.create(req);
        return ResponseEntity
                .created(URI.create("/api/products/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public product update(@PathVariable int id, @Valid @RequestBody ProductRequest req) {
        return productService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}