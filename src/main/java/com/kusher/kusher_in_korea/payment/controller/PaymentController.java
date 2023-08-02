package com.kusher.kusher_in_korea.payment.controller;

import com.kusher.kusher_in_korea.auth.service.UserService;
import com.kusher.kusher_in_korea.ingredient.repository.OrdersRepository;
import com.kusher.kusher_in_korea.ingredient.service.OrdersService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@ConfigurationProperties(prefix = "pgmodule")
@Slf4j
@RequiredArgsConstructor
@RestController
public class PaymentController { // 결제 API 연동을 위한 Controller
    private final IamportClient iamportClient;
    private final UserService userService;
    private final OrdersService ordersService;
    private final OrdersRepository ordersRepository;

    @Value("${pgmodule.app-id")
    private String apiKey;
    @Value("${pgmodule.secret-key}")
    private String apiSecret;

    @Autowired
    public PaymentController(UserService userService, OrdersService ordersService, OrdersRepository ordersRepository) {
        this.iamportClient = new IamportClient(apiKey,apiSecret);
        this.userService = userService;
        this.ordersService =ordersService;
        this.ordersRepository = ordersRepository;
    }

    @PostMapping("/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid, HttpServletRequest request) throws IamportResponseException, IOException {
        log.info("paymentByImpUid 진입");
        IamportResponse<Payment> paymentIamportResponse = iamportClient.paymentByImpUid(imp_uid);
        Payment payment = paymentIamportResponse.getResponse();
        HttpSession session = request.getSession(false);
        session.setAttribute("payment",payment);
        session.setMaxInactiveInterval(60);
        return paymentIamportResponse;
    }

}
