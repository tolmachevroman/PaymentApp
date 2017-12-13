package com.tolmachevroman.paymentapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethodsRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentMethodViewModel extends ViewModel {

    private LiveData<Resource<List<PaymentMethod>>> paymentMethods;

    @Inject
    public PaymentMethodViewModel(PaymentMethodsRepository paymentMethodsRepository) {
        paymentMethods = paymentMethodsRepository.getPaymentMethods();
    }

    public LiveData<Resource<List<PaymentMethod>>> getPaymentMethods() {
        return paymentMethods;
    }
}
