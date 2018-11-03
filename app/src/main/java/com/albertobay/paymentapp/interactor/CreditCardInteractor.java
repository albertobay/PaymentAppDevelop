package com.albertobay.paymentapp.interactor;

import com.albertobay.paymentapp.data.api.client.MeliService;
import com.albertobay.paymentapp.data.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alberto Bay on 31,October,2018
 */
public class CreditCardInteractor {

    private MeliService meliService;

    public CreditCardInteractor(MeliService meliService) {
        this.meliService = meliService;
    }

    /**
     * Se instancia el observable que espera la respuesta de la llamada a la API
     */
    public Observable<List<PaymentMethod>> loadPaymentMethods(String publicApiKey) {
        return meliService.getPaymentMethods(publicApiKey);
    }
}
