package com.tolmachevroman.paymentapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentMethod {

    @SerializedName("id")
    String id;

    @SerializedName("payment_type_id")
    String paymentTypeId;

    @SerializedName("status")
    String status;

    @SerializedName("thumbnail")
    String thumbnail;

    @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }
}
