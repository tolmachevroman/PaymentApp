package com.tolmachevroman.paymentapp.models.paymentmethods;

import com.google.gson.annotations.SerializedName;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentMethod {

    @SerializedName("id")
    private String id;

    @SerializedName("payment_type_id")
    private String paymentTypeId;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }
}
