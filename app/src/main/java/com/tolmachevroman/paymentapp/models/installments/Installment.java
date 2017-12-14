package com.tolmachevroman.paymentapp.models.installments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class Installment {

    @SerializedName("payer_costs")
    private List<PayerCost> payerCosts;

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }
}
