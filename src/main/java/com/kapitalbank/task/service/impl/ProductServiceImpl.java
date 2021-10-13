package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.dto.BulkProductsDTO;
import com.kapitalbank.task.dto.DetailDTO;
import com.kapitalbank.task.dto.HighDemandProductsDTO;
import com.kapitalbank.task.entity.Product;
import com.kapitalbank.task.repository.ProductRepository;
import com.kapitalbank.task.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();

            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);


        if(product.isPresent())
        {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<DetailDTO>> getProductDetails(int id) {

        try {
            List<DetailDTO> details = productRepository.getProductDetails(id);

            return new ResponseEntity<>(details, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        try {
            Product product1 = productRepository
                    .save(new Product(product.getDescription(), product.getName(), product.getPhoto(), product.getPrice()));
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> updateProduct(int id, Product product) {
        Optional<Product> product1 = productRepository.findById(id);

        if (product1.isPresent()) {
            Product product2 = product1.get();
            product2.setDescription(product.getDescription());
            product2.setPhoto(product.getPhoto());
            product2.setName(product.getName());
            product2.setPrice(product.getPrice());
            return new ResponseEntity<>(productRepository.save(product2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<HighDemandProductsDTO>> getHighDemandProducts() {
        try {
            List<HighDemandProductsDTO> highDemandProducts = productRepository.getHighDemandProducts();

            return new ResponseEntity<>(highDemandProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<BulkProductsDTO>> getBulkProducts() {
        try {
            List<BulkProductsDTO> bulkProductsDTOS = productRepository.getBulkProducts();

            return new ResponseEntity<>(bulkProductsDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
