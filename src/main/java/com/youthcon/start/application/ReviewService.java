package com.youthcon.start.application;

import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.DuplicateSendGiftException;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.errors.SendGiftInternalException;
import com.youthcon.start.infra.GiftApi;
import com.youthcon.start.infra.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GiftApi giftApi;

    public ReviewService(ReviewRepository reviewRepository, GiftApi giftApi) {
        this.reviewRepository = reviewRepository;
        this.giftApi = giftApi;
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review id : " + id));
    }

    @Transactional
    public Review sendGift(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review id : " + id));

        if (review.getSent()){
            throw new DuplicateSendGiftException("duplicate review id : " + id);
        }

        if (!giftApi.send(review.getPhoneNumber())){
            throw new SendGiftInternalException("internal exception");
        }

        review.makeTrue();

        return review;
    }
}
