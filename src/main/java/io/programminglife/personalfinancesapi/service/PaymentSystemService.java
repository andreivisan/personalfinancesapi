package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface PaymentSystemService {

    List<PaymentSystem> findAll();

    PaymentSystem findPaymentSystemById(Long paymentSystemId) throws MyFinancesException;

    PaymentSystem savePaymentSystem(PaymentSystem paymentSystem);

    void deletePaymentSystem(Long paymentSystemId);

}
