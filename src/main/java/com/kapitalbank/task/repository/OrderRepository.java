package com.kapitalbank.task.repository;

import com.kapitalbank.task.dto.NumberOfProductsInYearDTO;
import com.kapitalbank.task.dto.OrderDetailsAndProductName;
import com.kapitalbank.task.dto.OrdersWithoutInvoicesDTO;
import com.kapitalbank.task.entity.Order;
import com.kapitalbank.task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * " +
            "from cust_order co " +
            "left join detail d on co.id = d.ord_id " +
            "where co.date < ? and d.ord_id is null",
    nativeQuery = true)
    List<Order> ordersWithoutDetails(Date date);


    @Query(name = "NumberOfProductsInYearDTO",
            nativeQuery = true)
    List<NumberOfProductsInYearDTO> getNumberOfOrdersInOneYear();

    @Query(name = "OrdersWithoutInvoicesDTO",
            nativeQuery = true)
    List<OrdersWithoutInvoicesDTO> getOrdersWithoutInvoices();
}
