package io.programminglife.personalfinancesapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.PaymentSystemRepository;

@Service
public class PaymentSystemServiceImpl implements PaymentSystemService {

    @Autowired
    private PaymentSystemRepository paymentSystemRepository;

    @Override
    public List<PaymentSystem> findAll() {
        return paymentSystemRepository.findAll();
    }

    @Override
    public PaymentSystem findPaymentSystemById(Long paymentSystemId) throws MyFinancesException {
        return paymentSystemRepository.findById(paymentSystemId).orElseThrow(() -> new MyFinancesException(
                String.format("Payment System with id %s was not found", paymentSystemId)));
    }

    @Override
    public PaymentSystem savePaymentSystem(PaymentSystem paymentSystem) {
        return paymentSystemRepository.save(paymentSystem);
    }

    @Override
    public void deletePaymentSystem(Long paymentSystemId) {
        paymentSystemRepository.deleteById(paymentSystemId);
    }

}
