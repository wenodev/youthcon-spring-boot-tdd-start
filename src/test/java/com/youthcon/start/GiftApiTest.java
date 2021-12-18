package com.youthcon.start;

import com.youthcon.start.infra.GiftApi;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiftApiTest {
    GiftApi giftApi = new GiftApi();

    @Test
    void 선물하기_보내기_통신_성공(){
        assertThat(giftApi.send("010-1111-2222")).isTrue();
    }
}
