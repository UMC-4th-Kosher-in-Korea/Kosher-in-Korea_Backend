package com.kusher.kusher_in_korea.tabling.repository;

import com.kusher.kusher_in_korea.tabling.domain.User;
import org.hibernate.usertype.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //유저 id와 고객인지 점주인지 확인
    List<User> findByIdAndUserType(Long id, UserType userType);
    
    //유저이름 중복 방지를 위한 유저 이름 조회
    List<User> findByUserName(String userName);
}
