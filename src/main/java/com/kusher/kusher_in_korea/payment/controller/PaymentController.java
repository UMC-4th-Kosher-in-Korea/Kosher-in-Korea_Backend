package com.kusher.kusher_in_korea.payment.controller;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.ingredient.dto.response.OrdersDto;
import com.kusher.kusher_in_korea.ingredient.service.OrdersService;
import com.kusher.kusher_in_korea.payment.payApiResponse;
import com.kusher.kusher_in_korea.payment.service.PaymentService;
import com.kusher.kusher_in_korea.util.exception.ControllerException;
import com.kusher.kusher_in_korea.util.exception.ServiceException;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/pay")
@ConfigurationProperties(prefix = "pgmodule")
@RequiredArgsConstructor
public class PaymentController { // 결제 API 연동을 위한 Controller

    private final IamportClient api;
    private final PaymentService paymentService;
    private final OrdersService ordersService;

    @Value("${pgmodule.app-id}")
    private String apiKey;
    @Value("${pgmodule.secret-key}")
    private String apiSecret;

    @Autowired
    public PaymentController(PaymentService paymentService,OrdersService ordersService) {
        this.api = new IamportClient(apiKey,apiSecret);
        this.paymentService = paymentService;
        this.ordersService = ordersService;
    }

    // 구매 창 GET
    @GetMapping("/payment")
    public String paymentPage(Long orderId,HttpSession session, Model model) throws ControllerException {
        log.trace("paymentPage() invoked");

        try{
            OrdersDto order = this.ordersService.getOrder(orderId);

            User owner = (User) session.getAttribute("__LOGIN__");

            if(owner != null) {
                User user = this.paymentService.getPayUserInfo(owner.getId());
                model.addAttribute("__USER__", user);
            }

            Objects.requireNonNull(order);
            model.addAttribute("__ORDER__",order);
        } catch (Exception e) {
            throw new ControllerException(e);
        }

        return "/api/pay/payment";
    }

    //결제 검증
    @ResponseBody
    @PostMapping("/verify/{imp_uid}")
    public payApiResponse paymentByImpUid(
            @PathVariable(value = "imp_uid") String imp_uid,
            String orderId,
            Long userId) throws IamportResponseException, IOException, ControllerException {
        log.trace("paymentByImpUid({},{},{}) invoked.",imp_uid,orderId,userId);

        payApiResponse response = new payApiResponse();
        String result = "";
        Payment payment = this.api.paymentByImpUid(imp_uid).getResponse(); //검증처리

        //결제 완료 후 DB조작 메서드 실행
        try {
            switch (payment.getStatus()) {
                case "paid" :
                    result = this.paymentService.savePayment(payment,userId);

                    break;
                case "failed" :
                    result = "FAIL:04";

                    break;
            }

            response.add("result", result);
            response.add("payment_id", payment.getMerchantUid());
        }catch (ServiceException e) {
            throw new ControllerException(e);
        }

        return response;
    }

    //환불요청(사용자 요청)
    @PostMapping("/refund/req")
    public String refundRequest() {
        log.trace("refundRequest() invoked.");

        return "redirect:/mypage/pay/detail";
    }

    //환불(관리자 요청)
    @ResponseBody
    @PostMapping("/refund/{imp_uid}")
    public IamportResponse<Payment> cancelPaymentByImpUid(
            @PathVariable(value = "imp_uid")String imp_uid,
            CancelData cancelData) throws ControllerException {
        log.trace("cancelPaymentByImpUid({},{}) invoked.", imp_uid,cancelData);

        return null;
    }

    //구매완료창 GET
    @GetMapping("/succeeded/{payment_id}")
    public String paySucceeded(
            @PathVariable("payment_id") String payment_id,
            Model model) throws ControllerException {
        log.trace("paySucceeded() invoked.");

        try {
            model.addAttribute("__PAYINFO__", this.paymentService.getPaymentInfo(payment_id));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        return "/pay/paymentSucceeded";
    }
}
