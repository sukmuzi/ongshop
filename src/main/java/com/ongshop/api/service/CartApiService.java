package com.ongshop.api.service;

import com.ongshop.api.repository.CartApiRepository;
import com.ongshop.api.request.cart.CartRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartApiService {

    private final CartApiRepository cartApiRepository;

    public void insert(CartRequest cartRequest) {

    }
}
