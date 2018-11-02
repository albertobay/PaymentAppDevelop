package com.albertobay.paymentapp.data.api.client;

import com.albertobay.paymentapp.data.api.retrofit.MeliClientRetrofit;
import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.data.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public class MeliClient extends MeliClientRetrofit implements MeliService {

    @Override
    public Observable<List<PaymentMethod>> getPaymentMethods(String publicApiKey) {
        return getMeliService().getPaymentMethods(publicApiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Issuer>> getAvailableBanks(String publicApiKey, String pm) {
        return getMeliService().getAvailableBanks(publicApiKey, pm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
