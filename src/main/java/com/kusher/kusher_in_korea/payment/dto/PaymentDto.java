package com.kusher.kusher_in_korea.payment.dto;

import com.siot.IamportRestClient.response.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    private String id; //결제번호
    private Long userId; //예약 유저 번호
    private String payUid; //결제한 곳 uid
    private Integer payAmount; //금액
    private String payMethod; //결제방법
    private String payCardName; //카드이름
}
