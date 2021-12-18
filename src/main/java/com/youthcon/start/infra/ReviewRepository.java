package com.youthcon.start.infra;

import com.youthcon.start.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Override
    Optional<Review> findById(Long id);
}
