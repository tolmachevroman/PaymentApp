package com.tolmachevroman.paymentapp.datasources;

import com.tolmachevroman.paymentapp.models.PaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by romantolmachev on 12/12/2017.
 */

interface WebService {
    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods();
}
