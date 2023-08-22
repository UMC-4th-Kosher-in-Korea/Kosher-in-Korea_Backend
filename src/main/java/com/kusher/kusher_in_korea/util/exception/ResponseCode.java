package com.kusher.kusher_in_korea.util.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    /**
     * 400 Bad Request
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "Bad Request"),
    USER_LOGIN_FAILED(HttpStatus.BAD_REQUEST, false,"Login Failed"),
    NOT_RESTAURANT_OWNER(HttpStatus.BAD_REQUEST, false,"Not Restaurant Owner"),
    ALREADY_REVIEWED(HttpStatus.BAD_REQUEST, false,"Already Reviewed"),
    ALREADY_DELIVERED(HttpStatus.BAD_REQUEST, false,"Already Delivered"),
    ALREADY_CANCELED(HttpStatus.BAD_REQUEST, false,"Already Canceled"),

    /**
     * 404 NOT FOUND
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false,"User Not Found"),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Cart Not Found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Category Not Found"),
    CART_INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Cart Ingredient Not Found"),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Restaurant Not Found"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Reservation Not Found"),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Payment Not Found"),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Review Not Found"),
    ORDERS_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Orders Not Found"),
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Ingredient Not Found"),

    /**
     * 409 CONFLICT
     */
    DUPLICATED_USER(HttpStatus.CONFLICT, false,"Duplicated User"),
    DUPLICATED_RESTAURANT(HttpStatus.CONFLICT, false,"Duplicated Restaurant"),
    DUPLICATED_RESERVATION(HttpStatus.CONFLICT, false,"Duplicated Reservation"),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false,"Server Error"),

    /**
     * 200 OK
     */
    LOGIN_SUCCESS(HttpStatus.OK, true, "Login Success"),
    USER_READ_SUCCESS(HttpStatus.OK, true, "User Read Success"),
    RESTAURANT_READ_SUCCESS(HttpStatus.OK, true, "Restaurant Read Success"),
    INGREDIENT_READ_SUCCESS(HttpStatus.OK, true, "Ingredient Read Success"),
    CATEGORY_READ_SUCCESS(HttpStatus.OK, true, "Category Read Success"),
    RESTAURANT_MENU_READ_SUCCESS(HttpStatus.OK, true, "Restaurant Menu Read Success"),
    RESERVATION_READ_SUCCESS(HttpStatus.OK, true, "Reservation Read Success"),
    REVIEW_READ_SUCCESS(HttpStatus.OK, true, "Review Read Success"),
    ORDERS_READ_SUCCESS(HttpStatus.OK, true, "Orders Read Success"),
    CART_READ_SUCCESS(HttpStatus.OK, true, "Cart Read Success"),
    FEEDBACK_READ_SUCCESS(HttpStatus.OK, true, "Feedback Read Success"),

    USER_UPDATE_SUCCESS(HttpStatus.OK, true, "User Update Success"),
    RESTAURANT_UPDATE_SUCCESS(HttpStatus.OK, true, "Restaurant Update Success"),
    RESERVATION_UPDATE_SUCCESS(HttpStatus.OK, true, "Reservation Update Success"),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, true, "Review Update Success"),
    ORDERS_UPDATE_SUCCESS(HttpStatus.OK, true, "Orders Update Success"),
    CART_UPDATE_SUCCESS(HttpStatus.OK, true, "Cart Update Success"),
    RESTAURANT_MENU_UPDATE_SUCCESS(HttpStatus.OK, true, "Restaurant Menu Update Success"),
    CART_INGREDIENT_UPDATE_SUCCESS(HttpStatus.OK, true, "Cart Ingredient Update Success"),
    INGREDIENT_UPDATE_SUCCESS(HttpStatus.OK, true, "Ingredient Update Success"),
    CATEGORY_UPDATE_SUCCESS(HttpStatus.OK, true, "Category Update Success"),

    RESERVATION_CANCEL_SUCCESS(HttpStatus.OK, true, "Reservation Cancel Success"),
    ORDERS_CANCEL_SUCCESS(HttpStatus.OK, true, "Orders Cancel Success"),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, true, "Review Delete Success"),
    RESTAURANT_MENU_DELETE_SUCCESS(HttpStatus.OK, true, "Restaurant Menu Delete Success"),
    CART_INGREDIENT_DELETE_SUCCESS(HttpStatus.OK, true, "Cart Ingredient Delete Success"),
    INGREDIENT_DELETE_SUCCESS(HttpStatus.OK, true, "Ingredient Delete Success"),
    CATEGORY_DELETE_SUCCESS(HttpStatus.OK, true, "Category Delete Success"),

    /**
     *  201 Created
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, true, "Signup Success"),
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "User Create Success"),
    RESTAURANT_CREATE_SUCCESS(HttpStatus.CREATED, true, "Restaurant Create Success"),
    RESTAURANT_MENU_CREATE_SUCCESS(HttpStatus.CREATED, true, "Restaurant Menu Create Success"),
    RESERVATION_CREATE_SUCCESS(HttpStatus.CREATED, true, "Reservation Create Success"),
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED, true, "Review Create Success"),
    FEEDBACK_CREATE_SUCCESS(HttpStatus.CREATED, true, "Feedback Create Success"),
    ORDERS_CREATE_SUCCESS(HttpStatus.CREATED, true, "Orders Create Success"),
    CART_INGREDIENT_CREATE_SUCCESS(HttpStatus.CREATED, true, "Cart Ingredient Create Success"),
    INGREDIENT_CREATE_SUCCESS(HttpStatus.CREATED, true, "Ingredient Create Success"),
    CATEGORY_CREATE_SUCCESS(HttpStatus.CREATED, true, "Category Create Success"),

    /**
     * 204 No Content
     */
    LIST_EMPTY(HttpStatus.NO_CONTENT, true, "리스트가 비어있습니다.");

    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
