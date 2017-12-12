package com.tolmachevroman.paymentapp.models.paymentmethods;

import com.tolmachevroman.paymentapp.datasources.WebService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Singleton
public class PaymentMethodsRepository {

    private WebService webService;

    @Inject
    public PaymentMethodsRepository(WebService webService) {
        this.webService = webService;
    }

//    public LiveData<List<PaymentMethod>> getPaymentMethods() {
//
//    }
}
