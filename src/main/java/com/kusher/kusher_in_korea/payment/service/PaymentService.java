package com.kusher.kusher_in_korea.payment.service;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.payment.Repository.PaymentRepository;
import com.kusher.kusher_in_korea.payment.dto.PaymentDto;
import com.kusher.kusher_in_korea.util.exception.PaymentException;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    //결제정보 저장 savePayment
    public String savePayment(Payment payment, Long userId) throws PaymentException {
        log.trace("savePayment({}, {}) invoked", payment, userId);

        PaymentDto paymentDto = new PaymentDto(payment);

        try {
            if(this.paymentRepository.insertPaymentInfo(paymentDto.getId(),paymentDto.getUserId(),paymentDto.getPayUid(),
                    paymentDto.getPayAmount(),paymentDto.getPayMethod(),paymentDto.getPayCardName()).size() != 1) return "fail:01";

            return "SUCCESS";
        } catch (UncategorizedSQLException e) {
            throw e;
        } catch (Exception e) {
            log.info("\t+ Transfer Failure");

            throw new PaymentException(e);
        }
    }

    //결제정보 조회 getPaymentInfo
    // 클래스 이름 중복으로 인한 자료형 경로설정
    public com.kusher.kusher_in_korea.payment.domain.Payment getPaymentInfo(String payment_id) throws PaymentException {
        try{return this.paymentRepository.selectPaymentInfo(payment_id);}
        catch (Exception e) {throw new PaymentException(e);}
    }

    //결제 유저정보 조회 getPayUserInfo
    public User getPayUserInfo(Long userId) throws PaymentException {
        try {return this.paymentRepository.selectUserInfo(userId); }
        catch (Exception e) { throw new PaymentException(e); }
    }
}
