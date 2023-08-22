package com.kusher.kusher_in_korea.payment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long order_id; //결제 개수

    @Column(name = "payment_id")
    private String id; //결제번호(결제고객코드)

    private Long userId; //예약 유저 번호

    private String payUid; //결제한 곳 uid

    private Integer payAmount; //금액

    private String payMethod; //결제방법

    private String payCardName; //카드이름
}
