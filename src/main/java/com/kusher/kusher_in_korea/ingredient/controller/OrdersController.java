package com.kusher.kusher_in_korea.ingredient.controller;

import com.kusher.kusher_in_korea.ingredient.domain.Orders;
import com.kusher.kusher_in_korea.ingredient.dto.request.AddCartIngredientDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.CreateOrdersDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.service.OrdersService;
import com.kusher.kusher_in_korea.util.api.ApiResponse;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // 장바구니 총 가격 조회
    @GetMapping("/api/carts/{cartId}/price")
    public ApiResponse<Integer> getCartPrice(@PathVariable Long cartId) {
        return ApiResponse.success(ordersService.getCartPrice(cartId), ResponseCode.CART_READ_SUCCESS.getMessage());
    }

    // 장바구니 내부에 특정 상품 추가 (장바구니 담기)
    @PostMapping("/api/carts/{cartId}/ingredients")
    public ApiResponse<Long> addCartIngredient(@PathVariable Long cartId, @RequestBody AddCartIngredientDto addCartIngredientDto) {
        addCartIngredientDto.setCartId(cartId);
        return ApiResponse.success(ordersService.addCartIngredient(addCartIngredientDto), ResponseCode.CART_INGREDIENT_CREATE_SUCCESS.getMessage());
    }

    // 장바구니 내부에 특정 상품 개수 증가
    @PutMapping("/api/carts/{cartId}/ingredients/{cartIngredientId}/increase")
    public ApiResponse<Void> increaseCartIngredient(@PathVariable Long cartId, @PathVariable Long cartIngredientId) {
        ordersService.increaseCartIngredient(cartIngredientId);
        return ApiResponse.success(null, ResponseCode.CART_INGREDIENT_UPDATE_SUCCESS.getMessage());
    }

    // 장바구니 내부에 특정 상품 개수 감소
    @PutMapping("/api/carts/{cartId}/ingredients/{cartIngredientId}/decrease")
    public ApiResponse<Void> decreaseCartIngredient(@PathVariable Long cartId, @PathVariable Long cartIngredientId) {
        ordersService.decreaseCartIngredient(cartIngredientId);
        return ApiResponse.success(null, ResponseCode.CART_INGREDIENT_UPDATE_SUCCESS.getMessage());
    }

    // 장바구니 내부에 특정 상품 삭제
    @DeleteMapping("/api/carts/{cartId}/ingredients/{cartIngredientId}")
    public ApiResponse<Void> deleteCartIngredient(@PathVariable Long cartId, @PathVariable Long cartIngredientId) {
        ordersService.deleteCartIngredient(cartIngredientId);
        return ApiResponse.success(null, ResponseCode.CART_INGREDIENT_DELETE_SUCCESS.getMessage());
    }

    // 주문 생성
    @PostMapping("/api/orders")
    public ApiResponse<Long> createOrder(CreateOrdersDto orders) {
        Long orderId = ordersService.createOrder(orders);
        return ApiResponse.success(orderId, ResponseCode.ORDERS_CREATE_SUCCESS.getMessage());
    }

    // 주문 수정(배송지 변경)
    @PutMapping("/api/orders/{orderId}")
    public ApiResponse<Long> updateOrder(@PathVariable Long orderId, Orders orders) {
        Long updatedOrderId = ordersService.updateOrder(orderId, orders);
        return ApiResponse.success(updatedOrderId, ResponseCode.ORDERS_UPDATE_SUCCESS.getMessage());
    }

    // 주문 취소
    @PostMapping("/api/orders/{orderId}/cancel")
    public ApiResponse<Void> cancelOrder(@PathVariable Long orderId) {
        ordersService.cancelOrder(orderId);
        return ApiResponse.success(null, ResponseCode.ORDERS_CANCEL_SUCCESS.getMessage());
    }

    // 특정 주문 조회
    @GetMapping("/api/orders/{orderId}")
    public ApiResponse<OrdersDto> getOrder(@PathVariable Long orderId) {
        return ApiResponse.success(ordersService.getOrder(orderId), ResponseCode.ORDERS_READ_SUCCESS.getMessage());
    }

    // 전체 주문 조회: 관리자용 메서드
    @GetMapping("/api/orders")
    public ApiResponse<List<OrdersDto>> getOrders() {
        return ApiResponse.success(ordersService.getOrders(), ResponseCode.ORDERS_READ_SUCCESS.getMessage());
    }

}
