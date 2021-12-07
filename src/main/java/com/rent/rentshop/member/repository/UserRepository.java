package com.rent.rentshop.member.repository;

import com.rent.rentshop.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일을 중복체크 합니다.
     * @param email 검사 할 이메일
     * @return 중복이면 true | 중복이 아니면 false
     */
    boolean existsByEmail(String email);

    /**
     * 사용자를 조회합니다.
     * @param email 조회할 사용자 이메일
     * @return 조회된 사용자
     */
    Optional<User> findByEmail(String email);

}
