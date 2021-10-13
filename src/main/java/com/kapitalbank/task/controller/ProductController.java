package com.kapitalbank.task.controller;

import com.kapitalbank.task.dto.BulkProductsDTO;
import com.kapitalbank.task.dto.DetailDTO;
import com.kapitalbank.task.dto.HighDemandProductsDTO;
import com.kapitalbank.task.entity.Detail;
import com.kapitalbank.task.entity.Payment;
import com.kapitalbank.task.entity.Product;
import com.kapitalbank.task.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id)
    {
        return productService.getProductById(id);
    }

    @GetMapping("/product/details/{product_id}")
    public ResponseEntity<List<DetailDTO>> getProductDetails(@PathVariable int product_id)
    {
        return productService.getProductDetails(product_id);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product)
    {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id)
    {
        return productService.deleteProduct(id);
    }

    @GetMapping("/high_demand_products")
    public ResponseEntity<List<HighDemandProductsDTO>> getHighDemandProducts()
    {
        return productService.getHighDemandProducts();
    }

    @GetMapping("/bulk_products")
    public ResponseEntity<List<BulkProductsDTO>> getBulkProducts()
    {
        return productService.getBulkProducts();
    }
}
