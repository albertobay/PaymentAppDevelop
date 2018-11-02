package com.albertobay.paymentapp;

/**
 * Created by Alberto Bay on 30,October,2018
 */


public class Constants {

    public static final String MELI_API = "https://api.mercadopago.com";

    public static final class Endpoint {

        public static final String PAYMENT_METHOD_SEARCH = "/v1/payment_methods";
        public static final String BANK_SEARCH = "/v1/payment_methods/card_issuers";
        public static final String INSTALLMENTS_SEARCH = "/v1/payment_methods/installments";

    }

    public static final class Params {
        public static final String PAYMENT_METHODS = "payment_method_id";
        public static final String PUBLIC_KEY = "public_key";
        public static final String CARD_ISSUER = "card_issuer";
        public static final String AMOUNT = "amount";
        public static final String ID_ISSUER = "issuer.id";
    }

    public static final class SharedPreferences {

        public static final String STORE_NAME = "com.albertobay.paymentapp.storage";

        public static final String COMPLETE_FLOW = "com.albertobay.paymentapp.storage.completeFlow";

        public static final String AMOUNT_SELECTED = "com.albertobay.paymentapp.storage.amountSelected";

        public static final String CREDIT_CARD_SELECTED = "com.albertobay.paymentapp.storage.creditcardSelected";

        public static final String CREDIT_CARD_ID_SELECTED = "com.albertobay.paymentapp.storage.creditcardIdSelected";

        public static final String URL_IMAGE_CD_SELECTED = "com.albertobay.paymentapp.storage.urlSelected";

        public static final String BANK_ID_SELECTED = "com.albertobay.paymentapp.storage.bankIdSelected";

        public static final String BANK_SELECTED = "com.albertobay.paymentapp.storage.bankSelected";

        public static final String URL_IMAGE_BANK_SELECTED = "com.albertobay.paymentapp.storage.urlBankSelected";

        public static final String RECOMENDED_MESSAGE = "com.albertobay.paymentapp.storage.recomendedMessage";

    }
}

