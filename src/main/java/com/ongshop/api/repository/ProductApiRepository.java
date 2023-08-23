package com.ongshop.api.repository;

import com.ongshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductApiRepository extends JpaRepository<Product, Long> {

}
