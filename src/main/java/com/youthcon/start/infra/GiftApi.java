package com.youthcon.start.infra;

import com.youthcon.start.dto.SendGiftRequestDto;
import com.youthcon.start.dto.SendGiftResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GiftApi {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://youthcon.seok2.dev/apis/v1/send";

    private final Integer AMOUNT = 1000;

    public Boolean send(String phoneNumber) {
        ResponseEntity<SendGiftResponseDto> response = restTemplate.postForEntity(URL, SendGiftRequestDto.of(phoneNumber, AMOUNT), SendGiftResponseDto.class);
        return response.getStatusCode().is2xxSuccessful() ? true : false;
    }
}
