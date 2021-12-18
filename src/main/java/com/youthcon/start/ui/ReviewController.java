package com.youthcon.start.ui;

import com.youthcon.start.application.ReviewService;
import com.youthcon.start.domain.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/{id}")
    public Review getById(@PathVariable Long id){
        return reviewService.getById(id);
    }

    @PutMapping("/reviews/{id}")
    public Review sendGift(@PathVariable Long id){
        return reviewService.sendGift(id);
    }
}
