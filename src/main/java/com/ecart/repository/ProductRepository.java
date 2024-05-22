package com.ecart.repository;

import com.ecart.entity.Category;
import com.ecart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:name%")
    List<Product> search(String name);
    Product findByTitle(String title);
}
