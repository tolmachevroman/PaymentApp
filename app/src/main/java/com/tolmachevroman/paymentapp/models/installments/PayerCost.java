package com.tolmachevroman.paymentapp.models.installments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class PayerCost {

    @SerializedName("recommended_message")
    private String recommendedMessage;

    public String getRecommendedMessage() {
        return recommendedMessage;
    }
}
