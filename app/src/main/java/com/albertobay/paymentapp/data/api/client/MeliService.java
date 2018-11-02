package com.albertobay.paymentapp.data.api.client;

import com.albertobay.paymentapp.data.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public interface MeliService {
    Observable<List<PaymentMethod>> getPaymentMethods(String publicApiKey);
}
