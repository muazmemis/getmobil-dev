package dev.muazmemis.getmobil_dev.controller;

import dev.muazmemis.getmobil_dev.dto.ProductResponseDto;
import dev.muazmemis.getmobil_dev.dto.ProductSaveDto;
import dev.muazmemis.getmobil_dev.entity.Product;
import dev.muazmemis.getmobil_dev.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponseDto save(@Valid @RequestBody ProductSaveDto product) {
        return productService.save(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

}
