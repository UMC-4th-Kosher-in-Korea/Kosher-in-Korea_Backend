package com.kusher.kusher_in_korea.ingredient.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "delivery")
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Orders orders;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

    // 생성 메서드
    public static Delivery createDelivery(Address address) {
        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);
        return delivery;
    }
}

