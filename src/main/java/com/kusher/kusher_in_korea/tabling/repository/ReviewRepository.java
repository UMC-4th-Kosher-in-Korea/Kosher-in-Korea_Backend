package com.kusher.kusher_in_korea.tabling.repository;

import com.kusher.kusher_in_korea.tabling.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
