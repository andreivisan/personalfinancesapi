package io.programminglife.personalfinancesapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;

public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

    @Query(value = "SELECT * FROM payment_system WHERE label = :label", nativeQuery = true)
    Optional<PaymentSystem> findPaymentSystemByLabel(@Param("label") String label);

}
