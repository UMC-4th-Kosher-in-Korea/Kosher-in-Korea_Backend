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

    /**
     * 404 NOT FOUND
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false,"User Not Found"),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Restaurant Not Found"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Reservation Not Found"),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Payment Not Found"),
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
    RESERVATION_READ_SUCCESS(HttpStatus.OK, true, "Reservation Read Success"),

    /**
     *  201 Created
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, true, "회원가입 성공"),
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "User Create Success"),
    RESTAURANT_CREATE_SUCCESS(HttpStatus.CREATED, true, "Restaurant Create Success"),
    RESERVATION_CREATE_SUCCESS(HttpStatus.CREATED, true, "Reservation Create Success"),

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
