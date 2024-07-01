package com.examplechallengebinarc4.challengebinarc4.Repository;

import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    @Query(value = "select p from Product p")
    public List<Product> getAllPage();

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    public Product getByIdProduct(@Param("id") UUID id);

    @Query("FROM Product p")
    public Page<Product> getAllDataPage(Pageable pageable);
}
