package io.programminglife.personalfinancesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.service.PaymentSystemService;

@RestController
@RequestMapping("api/v1/paymentsystems")
public class PaymentSystemController {

    @Autowired
    private PaymentSystemService paymentSystemService;

    @PostMapping("/save")
    public ResponseEntity<PaymentSystem> save(@RequestBody PaymentSystem paymentSystem) {
        return ResponseEntity.ok().body(paymentSystemService.savePaymentSystem(paymentSystem));
    }

    @GetMapping
    public ResponseEntity<List<PaymentSystem>> findAll() {
        return ResponseEntity.ok().body(paymentSystemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentSystem> findPaymentSystemById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(paymentSystemService.findPaymentSystemById(id));
        } catch (MyFinancesException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        paymentSystemService.deletePaymentSystem(id);
    }

}
