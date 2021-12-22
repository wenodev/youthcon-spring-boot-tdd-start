package com.youthcon.start;

import com.youthcon.start.application.ReviewService;
import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.DuplicateSendGiftException;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.errors.SendGiftInternalException;
import com.youthcon.start.infra.GiftApi;
import com.youthcon.start.infra.ReviewRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {

    private GiftApi giftApi = mock(GiftApi.class);
    private ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private ReviewService reviewService = new ReviewService(reviewRepository, giftApi);

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

    @Test
    void 선물하기_성공(){
        // 준비
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id, content, phoneNumber, false)));
        given(giftApi.send(phoneNumber)).willReturn(true);
        given(reviewRepository.save(any(Review.class))).willReturn(new Review(id, content, phoneNumber, true));

        //실행
        Review review = reviewService.sendGift(id);

        // 검증
        assertThat(review.getId()).isEqualTo(id);
        assertThat(review.getSent()).isEqualTo(true);
    }

    @Test
    void 선물하기_중복_지급_실패(){
        // 준비
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id, content, phoneNumber, true)));

        assertThatThrownBy(() ->
                // 실행
                reviewService.sendGift(id))
                // 검증
                .isInstanceOf(DuplicateSendGiftException.class);
    }

    @Test
    void 선물하기_외부_요청_실패(){
        // 준비
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id, content, phoneNumber, false)));
        given(giftApi.send(phoneNumber)).willReturn(false);

        assertThatThrownBy(() ->
                // 실행
                reviewService.sendGift(id))
                // 검증
                .isInstanceOf(SendGiftInternalException.class);
    }

}
