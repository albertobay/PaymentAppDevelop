package com.albertobay.paymentapp;

/**
 * Created by Alberto Bay on 30,October,2018
 */


public class Constants {

    public static final String MELI_API = "https://api.mercadopago.com";
    public static final String API_KEY = "Authorization";

    //This access token shouldn't be exposed, use it like a professional developer
    public static final String ACCESS_TOKEN = "Bearer BQA7baqjgds6Z2vn_nWe4E8FCMqzsturlVeauK4az0"
            + "l-AK8t0BZUc5hyGJ-p7R-FJxPmXv8_8m1wPYkyKSB7eL6Wjtn2xe9eQmtDS83GtOADrrXaJ0Z-jZ1vugpSsz42v11lz1NZ1HxT0B2B";

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

    public static final class Serialized {

        public static final String NAME = "name";
        public static final String IMAGES = "images";
        public static final String FOLLOWERS = "followers";
        public static final String HREF = "href";
        public static final String ID = "id";
        public static final String POPULARITY = "popularity";
        public static final String HEIGHT = "height";
        public static final String URL = "url";
        public static final String WIDTH = "width";
        public static final String TOTAL = "total";
        public static final String PREVIEW_URL = "preview_url";
        public static final String TRACK_NUMBER = "track_number";
        public static final String ALBUM = "album";


    }

    public static final class Deserializer {

        public static final String PUBLI = "artists";
        public static final String ITEMS = "items";
        public static final String TRACKS = "tracks";
    }
}

