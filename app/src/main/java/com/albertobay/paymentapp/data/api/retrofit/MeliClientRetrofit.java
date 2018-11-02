package com.albertobay.paymentapp.data.api.retrofit;

import com.albertobay.paymentapp.Constants;
import com.albertobay.paymentapp.data.api.retrofit.deserializer.InstallmentDeserializer;
import com.albertobay.paymentapp.data.api.retrofit.deserializer.IssuerDeserializer;
import com.albertobay.paymentapp.data.api.retrofit.deserializer.PaymentMethodDeserializer;
import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.data.model.PaymentMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public class MeliClientRetrofit {

    private MeliServiceRetrofit mMeliServiceRetrofit;

    public MeliClientRetrofit() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        mMeliServiceRetrofit = retrofit.create(getMeliServiceClass());
    }

    private Retrofit retrofitBuilder() {
        return new Retrofit.Builder().baseUrl(Constants.MELI_API)
                .addConverterFactory(GsonConverterFactory.create(getMeliDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(getOkHttpClient())
                .build();
    }

   /* private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        ApiInterceptor apiInterceptor = new ApiInterceptor();
        client.addInterceptor(apiInterceptor);
        return client.build();
    }*/


    private Class<MeliServiceRetrofit> getMeliServiceClass() {
        return MeliServiceRetrofit.class;
    }

    private Gson getMeliDeserializer() {
        return new GsonBuilder().registerTypeAdapter(new TypeToken<List<PaymentMethod>>() {
        }.getType(), new PaymentMethodDeserializer<>())
                .registerTypeAdapter(new TypeToken<List<Issuer>>() {
                }.getType(), new IssuerDeserializer<>())
                .registerTypeAdapter(new TypeToken<List<Installment>>() {
                }.getType(), new InstallmentDeserializer<Installment>())
                .create();
    }

    protected MeliServiceRetrofit getMeliService() {
        return mMeliServiceRetrofit;
    }
}
