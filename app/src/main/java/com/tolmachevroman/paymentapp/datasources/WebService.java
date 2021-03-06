package com.tolmachevroman.paymentapp.datasources;

import com.tolmachevroman.paymentapp.models.banks.Bank;
import com.tolmachevroman.paymentapp.models.installments.Installment;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;

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

    @GET("payment_methods/card_issuers")
    Call<List<Bank>> getBanks(@Query("public_key") String publicKey,
                              @Query("payment_method_id") String paymentMethodId);

    @GET("payment_methods/installments")
    Call<List<Installment>> getInstallments(@Query("public_key") String publicKey,
                                            @Query("amount") String amount,
                                            @Query("payment_method_id") String paymentMethodId,
                                            @Query("issuer.id") String issuerId);
}
