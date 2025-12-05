package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Product;
import com.techlab.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        System.out.println("Producto ingresado" + product);
        return this.productRepository.save(product);
    }

    public Product getProductById(Long id){
        Optional<Product> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()){
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        return productOptional.get();
    }

    public List<Product> findAllProducts(String name, String category){
        if (!name.isEmpty() && !category.isEmpty()){
            return this.productRepository.findByNameContainingAndCategoryContainingIgnoreCase(name, category);
        }

        if (!name.isEmpty()){
            return this.productRepository.findByNameContainingIgnoreCase(name);
        }

        if (!category.isEmpty()){
            return this.productRepository.findByCategoryContainingIgnoreCase(category);
        }
        return this.productRepository.findAll();
    }

    public Product editProductById(Long id, Product productData){
        Product product = this.getProductById(id);

        if (!(productData.getName() == null || productData.getName().isBlank())) {
            product.setName(productData.getName());
        }
        return this.productRepository.save(product);
    }

    public Product deleteProductById(Long id){
        Product product = this.getProductById(id);

        this.productRepository.deleteById(id);

        return product;
    }
}
