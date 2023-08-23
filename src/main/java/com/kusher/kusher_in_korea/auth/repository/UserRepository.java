package com.kusher.kusher_in_korea.auth.repository;

import com.kusher.kusher_in_korea.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 id와 고객인지 점주인지 확인
    List<User> findByIdAndUserType(Long id, String userType);
    
    // 중복 회원가입 방지
    boolean existsByUserEmail(String userEmail);

    // 유저 이메일로 유저 찾기
    Optional<User> findByUserEmail(String userEmail);
}
