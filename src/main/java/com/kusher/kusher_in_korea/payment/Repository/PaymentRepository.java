package com.kusher.kusher_in_korea.payment.Repository;

import com.kusher.kusher_in_korea.auth.domain.User;
import com.kusher.kusher_in_korea.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    //결제정보 저장
    @Modifying
    @Query(value ="INSERT INTO payment (payment_id, user_id, pay_uid, pay_amount, pay_method,pay_card_name) " +
            "VALUES (:payment_id, :user_id, :pay_uid, :pay_amount, :pay_method, :pay_card_name)", nativeQuery = true)
    List<Payment> insertPaymentInfo(
            @Param("payment_id") String id,
            @Param("user_id") Long user_id,
            @Param("pay_uid") String payUid,
            @Param("pay_amount") Integer payAmount,
            @Param("pay_method") String payMethod,
            @Param("pay_card_name") String payCardName
    );

    //결제정보 조회
    @Query(value = "select * FROM payment p WHERE p.payment_id = :payment_id",nativeQuery = true)
    Payment selectPaymentInfo(
            @Param("payment_id") String id
    );

    //결제유저정보 조회
    @Query(value = "SELECT * FROM user u WHERE u.user_id = :user_id",nativeQuery = true)
    User selectUserInfo(
            @Param("user_id") Long id
    );
}
