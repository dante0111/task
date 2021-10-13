package com.kapitalbank.task.repository;

import com.kapitalbank.task.dto.CutomersLastOrdersDTO;
import com.kapitalbank.task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select *\n " +
            "from customer c\n" +
            "         left join cust_order co on c.id = co.cust_id\n" +
            "where extract(year from co.date) <> '2016' or cust_id is null"
            , nativeQuery = true)
    List<Customer> getCustomersWithoutOrders();

    @Query(name = "CutomersLastOrdersDTO",
    nativeQuery = true)
    List<CutomersLastOrdersDTO> getCustomersLastOrders();
}
