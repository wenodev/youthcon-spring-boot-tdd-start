package com.youthcon.start.dto;

public class SendGiftRequestDto {
    private String number;
    private Integer amount;

    public String getPhoneNumber() {
        return number;
    }

    public Integer getAmount() {
        return amount;
    }

    public SendGiftRequestDto() {
    }

    public SendGiftRequestDto(String phoneNumber, Integer amount) {
        this.number = phoneNumber;
        this.amount = amount;
    }

    public static SendGiftRequestDto of(String phoneNumber, Integer amount){
        return new SendGiftRequestDto(phoneNumber, amount);
    }

    @Override
    public String toString() {
        return "SendGiftDto{" +
                "phoneNumber='" + number + '\'' +
                ", amount=" + amount +
                '}';
    }

}
