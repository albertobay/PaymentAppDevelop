package com.albertobay.paymentapp.interactor;

import com.albertobay.paymentapp.data.api.client.MeliService;
import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.Issuer;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class InstallmentInteractor {
    private MeliService meliService;

    public InstallmentInteractor(MeliService meliService) {
        this.meliService = meliService;
    }

    /**
     * Se instancia el observable que espera la respuesta de la llamada a la API
     */
    public Observable<List<Installment>> getInstallments(String publicApiKey, String amount, String pm, String bank) {
        return meliService.getInstallments(publicApiKey, amount, pm, bank);
    }
}