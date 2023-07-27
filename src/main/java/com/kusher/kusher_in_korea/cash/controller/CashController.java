package com.kusher.kusher_in_korea.cash.controller;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.auth.service.UserService;
import com.kusher.kusher_in_korea.tabling.domain.Reservation;
import com.kusher.kusher_in_korea.tabling.repository.ReservationRepository;
import com.kusher.kusher_in_korea.tabling.service.ReservationService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
@ConfigurationProperties(prefix = "pgmodule")
@Slf4j
@RequiredArgsConstructor
@RestController
public class CashController { // 결제 API 연동을 위한 Controller
    private final IamportClient iamportClient;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @Value("${pgmodule.app-id")
    private String apiKey;
    @Value("${pgmodule.secret-key}")
    private String apiSecret;

    @Autowired
    public CashController(UserService userService, ReservationService reservationService,ReservationRepository reservationRepository) {
        this.iamportClient = new IamportClient(apiKey,apiSecret);
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
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

    @PostMapping("/reservation/{reservationId}/payment")
    public ResponseEntity<?> savePayment(@PathVariable Long id, HttpServletRequest request, @RequestBody paymentReqDto paymentReqDto) throws IamportResponseException, IOException {
        HashMap<String, String> respone = new HashMap<>();
        HttpSession session = request.getSession(false);
        CancelData cancelData = new CancelData(paymentReqDto.getImp_uid(), true);
        Optional<Reservation> findreservation = reservationRepository.findById(id);
    }
}
