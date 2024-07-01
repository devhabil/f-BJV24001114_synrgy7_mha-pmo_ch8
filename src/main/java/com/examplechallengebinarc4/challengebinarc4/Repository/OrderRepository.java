package com.examplechallengebinarc4.challengebinarc4.Repository;


import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order> {

    @Query(value = "select e from Order e")
    public List<Order> getAllPage();

    @Query(value = "SELECT m FROM Order m WHERE m.id = :id")
    public Order getByIdOrder(@Param("id") UUID id);

    @Query("FROM Order u")
    public Page<Order> getAllDataPage(Pageable pageable);
}
