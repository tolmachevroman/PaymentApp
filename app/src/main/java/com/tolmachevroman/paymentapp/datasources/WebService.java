package com.tolmachevroman.paymentapp.datasources;

import com.tolmachevroman.paymentapp.models.PaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public interface WebService {
    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods(@Query("public_key") String publicKey);
}
