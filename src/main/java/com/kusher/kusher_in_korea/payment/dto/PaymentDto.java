package com.kusher.kusher_in_korea.payment.dto;

import com.kusher.kusher_in_korea.payment.domain.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    private Long id; //결제번호
    private Long userId; //예약 유저 번호
    private String payUid; //결제한 곳 uid
    private Integer payAmount; //금액
    private String payMethod; //결제방법
    private String payCardName; //카드이름

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.userId = payment.getUserId();
        this.payUid = payment.getPayUid();
        this.payAmount = payment.getPayAmount();
        this.payMethod = payment.getPayMethod();
        this.payCardName = payment.getPayCardName();
    }
}
