package com.tolmachevroman.paymentapp.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethodsRepository;

import javax.inject.Inject;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentMethodViewModel extends ViewModel {

    private PaymentMethodsRepository paymentMethodsRepository;

    @Inject
    public PaymentMethodViewModel(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

//    LiveData<Resource<List<PaymentMethod>>> getPaymentMethods() {
//        return paymentMethodsRepository.getPaymentMethods();
//    }
}
