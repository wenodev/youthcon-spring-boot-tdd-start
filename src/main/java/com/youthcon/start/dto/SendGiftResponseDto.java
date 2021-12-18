package com.youthcon.start.dto;

public class SendGiftResponseDto {
    private String id;
    private Integer amount;

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public SendGiftResponseDto() {
    }

    public SendGiftResponseDto(String id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SendGiftResponseDto{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
