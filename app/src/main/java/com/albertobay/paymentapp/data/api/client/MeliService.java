package com.albertobay.paymentapp.data.api.client;

import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.data.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public interface MeliService {
    Observable<List<PaymentMethod>> getPaymentMethods(String publicApiKey);

    Observable<List<Issuer>> getAvailableBanks(String publicApiKey, String pm);

    Observable<List<Installment>> getInstallments(String publicApiKey, String amount, String pm, String bank);
}
