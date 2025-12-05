package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.entity.Product;
import com.techlab.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok().body(this.productService.createProduct(product));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(this.productService.getProductById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "") String category){
        return this.productService.findAllProducts(name, category);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> editProductById(@PathVariable Long id, @RequestBody Product productData){
        try {
            return ResponseEntity.ok().body(this.productService.editProductById(id, productData));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(this.productService.deleteProductById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
