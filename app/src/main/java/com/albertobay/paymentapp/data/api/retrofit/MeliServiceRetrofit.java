package com.albertobay.paymentapp.data.api.retrofit;

import com.albertobay.paymentapp.BuildConfig;
import com.albertobay.paymentapp.Constants;
import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.data.model.PaymentMethod;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public interface MeliServiceRetrofit {
    @GET(Constants.Endpoint.PAYMENT_METHOD_SEARCH) Observable<List<PaymentMethod>> getPaymentMethods(
        @Query(Constants.Params.PUBLIC_KEY) String apiKey);

    @GET(Constants.Endpoint.BANK_SEARCH) Observable<List<Issuer>> getAvailableBanks(
            @Query(Constants.Params.PUBLIC_KEY) String apiKey,
            @Query(Constants.Params.PAYMENT_METHODS) String pm);
}
