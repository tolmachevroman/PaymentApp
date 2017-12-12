package com.tolmachevroman.paymentapp.models.paymentmethods;

import com.tolmachevroman.paymentapp.datasources.WebService;
import com.tolmachevroman.paymentapp.utils.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Singleton
public class PaymentMethodsRepository {

    private WebService webService;
    private Utils utils;

    @Inject
    public PaymentMethodsRepository(WebService webService, Utils utils) {
        this.webService = webService;
        this.utils = utils;
    }

//    public LiveData<Resource<List<PaymentMethod>>> getPaymentMethods() {
//
//    }
}
