package com.kusher.kusher_in_korea.ingredient.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 값 타입
@Getter
public class Address { // 주소

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
