package com.albertobay.paymentapp.data.api.retrofit.deserializer;

import com.google.gson.JsonDeserializer;

import java.util.List;

/**
 * Created by Alberto Bay on 01,November,2018
 */
public interface ListDeserializer<T> extends JsonDeserializer<List<T>> {
}
