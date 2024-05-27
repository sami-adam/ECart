package com.ecart.repository;

import com.ecart.entity.Order;
import com.ecart.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    public List<OrderLine> getOrderLineByOrder(Order order);
}
