package com.kapitalbank.task.service;

import com.kapitalbank.task.dto.BulkProductsDTO;
import com.kapitalbank.task.dto.HighDemandProductsDTO;
import com.kapitalbank.task.entity.Payment;
import com.kapitalbank.task.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<Product>> getAllProducts();

    ResponseEntity<Product> getProductById(int id);

    ResponseEntity<Product> createProduct(Product product);

    ResponseEntity<Product> updateProduct(int id, Product product);

    ResponseEntity<HttpStatus> deleteProduct(int id);

    ResponseEntity<List<HighDemandProductsDTO>> getHighDemandProducts();

    ResponseEntity<List<BulkProductsDTO>> getBulkProducts();
}
