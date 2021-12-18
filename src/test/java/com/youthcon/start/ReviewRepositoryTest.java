package com.youthcon.start;

import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.infra.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void 후기_조회_성공(){
        // 실행
        Review review = reviewRepository.findById(1L)
                .orElseThrow(RuntimeException::new);

        // 검증
        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getContent()).isEqualTo("재밌어요");
        assertThat(review.getPhoneNumber()).isEqualTo("010-1111-2222");
    }

    @Test
    void 후기_조회_실패(){
        assertThatThrownBy(() ->
                // 실행
                reviewRepository.findById(1000L)
                        .orElseThrow(() -> new ReviewNotFoundException("no review id :" + 1000L)))

                // 검증
                .isInstanceOf(ReviewNotFoundException.class);
    }
}
