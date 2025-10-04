package com.ecommerce.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);
}
