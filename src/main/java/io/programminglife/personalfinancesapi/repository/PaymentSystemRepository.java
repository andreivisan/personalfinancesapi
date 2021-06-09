package io.programminglife.personalfinancesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;

public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

}
