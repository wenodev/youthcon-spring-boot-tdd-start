package com.youthcon.start;

import com.youthcon.start.infra.GiftApi;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;

class GiftApiTest {


    @Test
    void 선물하기_보내기_통신_성공(){
        RestTemplate template = mock(RestTemplate.class);
        GiftApi giftApi = new GiftApi(template);
        assertThat(giftApi.send("010-1111-2222")).isTrue();
    }

}
