package com.kusher.kusher_in_korea.payment.service;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.payment.Repository.PaymentRepository;
import com.kusher.kusher_in_korea.payment.dto.PaymentDto;
import com.kusher.kusher_in_korea.util.exception.CustomException;
import com.kusher.kusher_in_korea.util.exception.ResponseCode;
import com.kusher.kusher_in_korea.util.exception.ServiceException;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    //결제정보 저장 savePayment
    public String savPayment(Payment payment, Long userId) throws ServiceException{
        log.trace("savePayment({}, {}) invoked", payment, userId);

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId(payment.getMerchantUid());
        paymentDto.setUserId(userId);
        paymentDto.setPayUid(payment.getImpUid());
        paymentDto.setPayAmount(payment.getAmount().intValue());
        paymentDto.setPayMethod(payment.getPayMethod());
        paymentDto.setPayCardName(payment.getCardName());

        try {
            if(this.paymentRepository.insertPaymentInfo(paymentDto.getId(),paymentDto.getUserId(),paymentDto.getPayUid(),
                    paymentDto.getPayAmount(),paymentDto.getPayMethod(),paymentDto.getPayCardName()).size() != 1) return "fail:01";

            return "SUCCESS";
        } catch (UncategorizedSQLException e) {
            throw e;
        } catch (Exception e) {
            log.info("\t+ Transfer Failure");

            throw new ServiceException(e);
        }
    }

    //결제정보 조회 getPayInfo
    public com.kusher.kusher_in_korea.payment.domain.Payment getPaymentInfo(String payment_id) throws ServiceException {
        try{return this.paymentRepository.selectPaymentInfo(payment_id);}
        catch (Exception e) {throw new ServiceException(e);}
    }

    //결제 유저정보 조회 getPayUserInfo
    public User getPayUserInfo(Long userId) throws ServiceException {
        try {return this.paymentRepository.selectUserInfo(userId); }
        catch (Exception e) { throw new ServiceException(e); }
    }
}
