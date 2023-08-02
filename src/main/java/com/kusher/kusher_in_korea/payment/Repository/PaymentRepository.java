package com.kusher.kusher_in_korea.payment.Repository;

import com.kusher.kusher_in_korea.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    String insertPayment = "INSERT INTO Payment (payment_id, user_id,pay_uid, pay_amount, pay_method,pay_card_name) " +
            "VALUES (:payment_id, :user_id, :pay_uid, :pay_amount, :pay_method, :pay_card_name)";

    //결제정보 저장
    @Modifying
    @Query(value = insertPayment, nativeQuery = true)
    List<Payment> insertPaymentInfo(
            @Param("id") Long id,
            @Param("userId") Long userId,
            @Param("payUid") String payUid,
            @Param("payAmount") Integer payAmount,
            @Param("payMethod") String payMethod,
            @Param("payCardName") String payCardName
    );
}
