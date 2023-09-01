package com.ongshop.api.repository;

import com.ongshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartApiRepository extends JpaRepository<Cart, Long> {


}
