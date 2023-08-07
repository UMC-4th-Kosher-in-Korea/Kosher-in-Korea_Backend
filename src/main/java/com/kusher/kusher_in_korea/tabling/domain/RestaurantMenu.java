package com.kusher.kusher_in_korea.tabling.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "restaurant_menu")
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_menu_id")
    private Long id; // 메뉴번호

    @ManyToOne(fetch = FetchType.LAZY) // 메뉴와 식당은 다대일 관계
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant; // 식당

    private String menuName; // 메뉴이름

    private int Price; // 메뉴가격

    private String menuDescription; // 메뉴설명
}
