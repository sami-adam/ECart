package com.ecart.repository;

import com.ecart.entity.Order;
import com.ecart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findByReference(String reference);
    public List<Order> findByUser(User user);
}
