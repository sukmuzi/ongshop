package com.ongshop.api.repository;

import com.ongshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberApiRepository extends JpaRepository<Member, Long> {

    Member findById(String id);
    boolean existsById(String id);
}
