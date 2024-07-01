package com.examplechallengebinarc4.challengebinarc4.Repository;

import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
import com.examplechallengebinarc4.challengebinarc4.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    @Query(value = "select u from User u")
    public List<User> getAllPage();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getByIdUser(@Param("id") UUID id);
    @Query("FROM User u")
    public Page<User> getAllDataPage(Pageable pageable);
}
