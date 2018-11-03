package com.albertobay.paymentapp.interactor;

import com.albertobay.paymentapp.data.api.client.MeliService;
import com.albertobay.paymentapp.data.model.Issuer;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class BankInteractor {
    private MeliService meliService;

    public BankInteractor(MeliService meliService) {
        this.meliService = meliService;
    }
    /**
     * Se instancia el observable que espera la respuesta de la llamada a la API
     */
    public Observable<List<Issuer>> getAvailableBanks(String publicApiKey, String pm) {
        return meliService.getAvailableBanks(publicApiKey, pm);
    }
}
