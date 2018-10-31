package com.albertobay.paymentapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alberto Bay on 30,October,2018
 */
public class CardNumber {

    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("validation")
    @Expose
    private String validation;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

}