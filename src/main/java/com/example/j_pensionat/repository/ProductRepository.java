package com.example.j_pensionat.repository;

import com.example.j_pensionat.model.Product;
import com.example.j_pensionat.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
