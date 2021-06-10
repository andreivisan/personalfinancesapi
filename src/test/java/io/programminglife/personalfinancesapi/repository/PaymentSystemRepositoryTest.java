package io.programminglife.personalfinancesapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;

@DataJpaTest
public class PaymentSystemRepositoryTest {

    @Autowired
    private PaymentSystemRepository paymentSystemRepository;

    @Test
    public void test_no_payment_systems_if_repository_is_empty() {
        List<PaymentSystem> paymentSystems = paymentSystemRepository.findAll();

        assertEquals(0, paymentSystems.size());
    }

    @Test
    public void test_save_payment_system() {
        PaymentSystem testPaymentSystem = paymentSystemRepository.save(new PaymentSystem("PayPal"));

        assertNotNull(testPaymentSystem);
        assertEquals("PayPal", testPaymentSystem.getLabel());
    }

    @Test
    public void test_get_payment_system_by_id() {
        PaymentSystem testPaymentSystem = paymentSystemRepository.save(new PaymentSystem("PayPal"));

        PaymentSystem paymentSystem = paymentSystemRepository.findById(1L).orElse(null);

        assertNotNull(paymentSystem);
        assertEquals(testPaymentSystem.getLabel(), paymentSystem.getLabel());
    }

    @Test
    public void test_get_all_payment_systems() {
        paymentSystemRepository.save(new PaymentSystem("PayPal"));
        paymentSystemRepository.save(new PaymentSystem("iDeal"));

        List<PaymentSystem> paymentSystems = paymentSystemRepository.findAll();

        assertNotNull(paymentSystems);
        assertEquals(2, paymentSystems.size());
    }

    @Test
    public void test_delete_payment_system_by_id() {
        PaymentSystem testPaymentSystem = paymentSystemRepository.save(new PaymentSystem("PayPal"));

        paymentSystemRepository.deleteById(testPaymentSystem.getId());

        List<PaymentSystem> paymentSystems = paymentSystemRepository.findAll();

        assertEquals(0, paymentSystems.size());
    }

}
