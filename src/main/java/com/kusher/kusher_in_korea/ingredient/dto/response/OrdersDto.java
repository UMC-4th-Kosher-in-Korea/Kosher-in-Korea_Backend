package com.kusher.kusher_in_korea.ingredient.dto.response;

import com.kusher.kusher_in_korea.ingredient.domain.Orders;
import com.kusher.kusher_in_korea.ingredient.domain.OrdersIngredient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrdersDto { // 주문 조회에 대한 응답
    private Long orderId;
    private String orderStatus;
    private DeliveryDto delivery;
    private LocalDateTime orderDateTime;
    private List<OrdersIngredientDto> ordersIngredientList = new ArrayList<>();

    public OrdersDto(Orders orders) {
        this.orderId = orders.getId();
        this.orderStatus = orders.getStatus().toString();
        this.delivery = new DeliveryDto(orders.getDelivery());
        this.orderDateTime = orders.getOrderDateTime();
        for (OrdersIngredient ordersIngredient : orders.getOrdersIngredientList()) {
            this.ordersIngredientList.add(new OrdersIngredientDto(ordersIngredient));
        }
    }
}
