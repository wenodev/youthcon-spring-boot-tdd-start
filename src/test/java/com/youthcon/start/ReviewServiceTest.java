package com.youthcon.start;

import com.youthcon.start.application.ReviewService;
import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.infra.ReviewRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {


    private ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private ReviewService reviewService = new ReviewService(reviewRepository);

    private Long id = 1L;
    private String  content = "재밌어요";
    private String phoneNumber = "010-1111-2222";

    @Test
    void 후기_조회_성공(){
        // 준비
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id, content, phoneNumber)));

        // 실행
        Review review = reviewService.getById(id);

        // 검증
        assertThat(review.getId()).isEqualTo(id);
        assertThat(review.getContent()).isEqualTo(content);
        assertThat(review.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void 후기_조회_실패(){
        // 준비
        given(reviewRepository.findById(1000L)).willReturn(Optional.empty());

        assertThatThrownBy(() ->
                // 실행
                reviewService.getById(1000L))
                // 검증
                .isInstanceOf(ReviewNotFoundException.class);
    }

}
