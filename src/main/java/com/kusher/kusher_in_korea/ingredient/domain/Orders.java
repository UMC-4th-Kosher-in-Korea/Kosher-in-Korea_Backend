package com.kusher.kusher_in_korea.ingredient.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
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

    public void update(Delivery delivery) {
        this.delivery = delivery;
    }

    // 생성 메서드
    public static Orders createOrders(User user, OrderStatus status, Delivery delivery) {
        Orders orders = new Orders();
        orders.user = user;
        orders.status = status;
        orders.orderDateTime = LocalDateTime.now();
        orders.delivery = delivery;
        return orders;
    }

    public void setOrdersIngredientList(List<OrdersIngredient> ordersIngredientList) {
        this.ordersIngredientList = ordersIngredientList;
    }

    // 주문 취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new CustomException(ResponseCode.ALREADY_DELIVERED);
        }
        this.status = OrderStatus.CANCEL;
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
