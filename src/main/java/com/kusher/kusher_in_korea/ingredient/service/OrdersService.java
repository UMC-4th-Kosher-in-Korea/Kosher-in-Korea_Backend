package com.kusher.kusher_in_korea.ingredient.service;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.auth.repository.UserRepository;
import com.kusher.kusher_in_korea.ingredient.domain.*;
import com.kusher.kusher_in_korea.ingredient.dto.request.AddCartIngredientDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.ChangeAddressDto;
import com.kusher.kusher_in_korea.ingredient.dto.request.CreateOrdersDto;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.repository.CartIngredientRepository;
import com.kusher.kusher_in_korea.ingredient.repository.CartRepository;
import com.kusher.kusher_in_korea.ingredient.repository.IngredientRepository;
import com.kusher.kusher_in_korea.ingredient.repository.OrdersRepository;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartIngredientRepository cartIngredientRepository;
    private final IngredientRepository ingredientRepository;

    // 장바구니 조회 기능은 UserService에 이미 존재

    // 유저의 장바구니 가격 조회
    public int getCartPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CustomException(ResponseCode.CART_NOT_FOUND));
        return cart.getTotalPrice();
    }

    // 장바구니 내부에 특정 상품 추가 (장바구니 담기)
    @Transactional
    public Long addCartIngredient(AddCartIngredientDto addCartIngredientDto) {
        Cart cart = cartRepository.findById(addCartIngredientDto.getCartId()).orElseThrow(() -> new CustomException(ResponseCode.CART_NOT_FOUND));
        CartIngredient cartIngredient = CartIngredient.createCartIngredient(cart, addCartIngredientDto.getCount(), ingredientRepository.findById(addCartIngredientDto.getIngredientId()).orElseThrow(() -> new CustomException(ResponseCode.INGREDIENT_NOT_FOUND)));
        return cartIngredientRepository.save(cartIngredient).getId();
    }

    // 장바구니 내부 특정 상품 개수 증가
    @Transactional
    public void increaseCartIngredient(Long cartIngredientId) {
        CartIngredient cartIngredient = cartIngredientRepository.findById(cartIngredientId).orElseThrow(() -> new CustomException(ResponseCode.CART_INGREDIENT_NOT_FOUND));
        cartIngredient.addCount();
        cartIngredientRepository.save(cartIngredient);
    }

    // 장바구니 내부 특정 상품 개수 감소
    @Transactional
    public void decreaseCartIngredient(Long cartIngredientId) {
        CartIngredient cartIngredient = cartIngredientRepository.findById(cartIngredientId).orElseThrow(() -> new CustomException(ResponseCode.CART_INGREDIENT_NOT_FOUND));
        cartIngredient.subtractCount();
        cartIngredientRepository.save(cartIngredient);
    }

    // 장바구니 내부 특정 상품 삭제
    @Transactional
    public void deleteCartIngredient(Long cartIngredientId) {
        CartIngredient cartIngredient = cartIngredientRepository.findById(cartIngredientId).orElseThrow(() -> new CustomException(ResponseCode.CART_INGREDIENT_NOT_FOUND));
        cartIngredientRepository.delete(cartIngredient);
    }

    // 주문 생성
    @Transactional
    public Long createOrder(CreateOrdersDto createOrdersDto) {
        User user = userRepository.findById(createOrdersDto.getUserId()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Delivery delivery = Delivery.createDelivery(createOrdersDto.getAddress());
        Orders orders = Orders.createOrders(user, createOrdersDto.getOrderStatus(), delivery);
        delivery.setOrders(orders);

        // 장바구니_상품_리스트 -> 주문_상품_리스트로 전환
        List<OrdersIngredient> ordersIngredients = createOrdersDto.getCartIngredients().stream().map(cartIngredientDto -> {
            CartIngredient cartIngredient = cartIngredientRepository.findById(cartIngredientDto.getCartIngredientId()).orElseThrow(() -> new CustomException(ResponseCode.CART_INGREDIENT_NOT_FOUND));
            return OrdersIngredient.createOrderIngredient(orders, cartIngredient.getIngredient(), cartIngredient.getCount());
        }).collect(Collectors.toList());
        orders.setOrdersIngredientList(ordersIngredients);
        return ordersRepository.save(orders).getId();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Orders order = getOrderById(orderId);
        order.cancel();
    }

    // 주문 수정(배송지 수정)
    @Transactional
    public Long updateOrder(Long orderId, ChangeAddressDto changeAddressDto) {
        Orders order = getOrderById(orderId);
        if (!Objects.equals(order.getUser().getId(), changeAddressDto.getUserId()))
            throw new CustomException(ResponseCode.USER_NOT_FOUND);
        order.getDelivery().setAddress(changeAddressDto.getAddress());
        return order.getId();
    }

    // user의 주문 조회
    public List<OrdersDto> getUserOrders(Long userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        return orders.stream().map(OrdersDto::new).collect(Collectors.toList());
    }

    // 특정 주문 조회
    public OrdersDto getOrder(Long orderId) {
        Orders order = getOrderById(orderId);
        return new OrdersDto(order);
    }

    // 전체 주문 조회: 관리자용 메서드
    public List<OrdersDto> getOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(OrdersDto::new).collect(Collectors.toList());
    }

    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElseThrow(() -> new CustomException(ResponseCode.ORDERS_NOT_FOUND));
    }

    public CartIngredient getCartIngredientById(Long cartIngredientId) {
        return cartIngredientRepository.findById(cartIngredientId).orElseThrow(() -> new CustomException(ResponseCode.CART_INGREDIENT_NOT_FOUND));
    }
}
