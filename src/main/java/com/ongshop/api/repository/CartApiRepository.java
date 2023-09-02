package com.ongshop.api.repository;

import com.ongshop.api.request.cart.CartRequest;
import com.ongshop.api.response.cart.CartResponse;
import com.ongshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartApiRepository extends JpaRepository<Cart, Long> {

    // product_no 기준으로 imgUrl, price, title
    // member_no
    // option, quantity 는 요청에서 처리

//    @Query(value = "insert ")
//    void insert(CartRequest cartRequest);
    @Query(value = "select new com.ongshop.api.response.cart.CartResponse(c.no, p.no, p.title, p.imgUrl, c.option, p.price, c.quantity) " +
            "from Cart c " +
            "left join Member m on c.member.no = m.no " +
            "left join Product p on c.product.no = p.no " +
            "where c.member.no = :memberNo")
    List<CartResponse> findAllByMemberNo(Long memberNo);

    Cart findByProductNoAndMemberNo(Long productNo, Long memberNo);
}
