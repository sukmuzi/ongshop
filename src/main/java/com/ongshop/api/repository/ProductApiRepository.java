package com.ongshop.api.repository;

import com.ongshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductApiRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p where p.no > ?1 order by p.no")
    Page<Product> findByProductNoBiggerThanOrderByNo(Long lastProductNo, PageRequest pageRequest);
}
