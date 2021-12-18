package com.youthcon.start;

import com.youthcon.start.application.ReviewService;
import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.ui.ReviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private Long id = 1L;
    private String  content = "재밌어요";
    private String phoneNumber = "010-1111-2222";

    @Test
    void 후기조회는_id가_존재한다면_저장된_id와_200을_리턴한다() throws Exception {
        // 준비
        given(reviewService.getById(id)).willReturn(new Review(id, content, phoneNumber));

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/" + id));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("content").value(content))
                .andExpect(jsonPath("phoneNumber").value(phoneNumber));
    }

    @Test
    void 후기조회는_id가_존재하지_않는다면_404를_리턴한다() throws Exception {
        // 준비
        given(reviewService.getById(1000L)).willThrow(new ReviewNotFoundException("no review id : " + id));

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/" + 1000));

        // 검증
        perform.andExpect(status().isNotFound());
    }

    @Test
    void 선물하기() throws Exception {
        // 준비
        given(reviewService.sendGift(id)).willReturn(new Review(id, content, phoneNumber, true));

        // 실행
        ResultActions perform = mockMvc.perform(put("/reviews/" + id));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("isSent").value(true));
    }

}
