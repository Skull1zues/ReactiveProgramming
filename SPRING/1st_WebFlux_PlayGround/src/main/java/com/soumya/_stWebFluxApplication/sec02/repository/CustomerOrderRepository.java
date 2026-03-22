package com.soumya._stWebFluxApplication.sec02.repository;

import com.soumya._stWebFluxApplication.sec02.dto.OrderDetails;
import com.soumya._stWebFluxApplication.sec02.entity.CustomerOrder;
import com.soumya._stWebFluxApplication.sec02.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CustomerOrderRepository extends ReactiveCrudRepository<CustomerOrder, Integer> {
    @Query("""
            SELECT p.* FROM customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON co.product_id = p.id
            where
            c.name = :name
            """)
    Flux<CustomerOrder> getProductOrderedByCustomer(String name);

    @Query("""
            SELECT
            co.order_id,
            c.name as customer_name,
            p.description as product_name,
            co.amount,
            co.order_date
            FROM
            customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON co.product_id = p.id
            WHERE
            p.description = :description
            ORDER BY co.amount DESC
           """)
    Flux<OrderDetails> getOrderDetailsByProduct(String description);
}
