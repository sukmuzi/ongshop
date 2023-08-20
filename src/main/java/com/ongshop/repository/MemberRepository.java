package com.ongshop.repository;

import com.ongshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
    boolean existsByEmail(String email);
}
