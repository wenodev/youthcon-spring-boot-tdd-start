package com.youthcon.start.application;

import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.infra.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review id : " + id));
    }

    @Transactional
    public Review sendGift(Long id) {
        return null;
    }
}
