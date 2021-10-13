package com.kapitalbank.task.repository;

import com.kapitalbank.task.dto.BulkProductsDTO;
import com.kapitalbank.task.dto.HighDemandProductsDTO;
import com.kapitalbank.task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(name = "HighDemandProductsDTO",
    nativeQuery = true)
    List<HighDemandProductsDTO> getHighDemandProducts();

    @Query(name = "BulkProductsDTO",
            nativeQuery = true)
    List<BulkProductsDTO> getBulkProducts();

}
