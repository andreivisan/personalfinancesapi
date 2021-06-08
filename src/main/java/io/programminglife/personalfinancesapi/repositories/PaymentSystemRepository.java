package io.programminglife.personalfinancesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entities.PaymentSystem;

public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

}
