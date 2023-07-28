package com.kusher.kusher_in_korea.ingredient.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusher.kusher_in_korea.auth.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Orders { // 주문은 유저와 일대다 관계
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 주문한 유저

    private OrderStatus status; // 주문 상태

    private LocalDateTime orderDateTime; // 주문 날짜 및 시간

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery; // 주문과 배송은 일대일 관계

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersIngredient> ordersIngredientList = new ArrayList<>(); // 주문과 주문상품은 일대다 관계

    // 생성 메서드
    public static Orders createOrders(User user, Delivery delivery, OrderStatus status) {
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setDelivery(delivery);
        orders.setStatus(status);
        orders.setOrderDateTime(LocalDateTime.now());
        return orders;
    }

    public void update(Orders orders) {
        this.delivery = orders.getDelivery();
    }

    // 배송지 설정
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }

    // 주문 취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        // 이후 OrdersService에서 OrdersIngredientRepository를 통해 주문 상품을 취소한다. 재고를 따지기 않기에 중간테이블 클래스에서 cancel은 없다.
    }

    // 주문 전체 가격
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrdersIngredient ordersIngredient : ordersIngredientList) {
            totalPrice += ordersIngredient.getTotalPrice();
        }
        return totalPrice;
    }

}
