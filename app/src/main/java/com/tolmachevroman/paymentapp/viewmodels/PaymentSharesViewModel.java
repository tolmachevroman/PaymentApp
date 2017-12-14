package com.tolmachevroman.paymentapp.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.models.installments.Installment;
import com.tolmachevroman.paymentapp.models.installments.InstallmentsRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class PaymentSharesViewModel extends ViewModel {

    private InstallmentsRepository repository;

    private MutableLiveData<Parameters> parameters = new MutableLiveData<>();

    @Inject
    public PaymentSharesViewModel(InstallmentsRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetches banks from repository, based on the parameters
     */
    public LiveData<Resource<List<Installment>>> installments = Transformations.switchMap(parameters, new Function<Parameters, LiveData<Resource<List<Installment>>>>() {
        @Override
        public LiveData<Resource<List<Installment>>> apply(Parameters input) {
            return repository.getInstallments(input.amount, input.paymentMethodId, input.issuerId);
        }
    });

    public Parameters getParameters() {
        return parameters.getValue();
    }

    public void setParameters(Parameters parameters) {
        this.parameters.setValue(parameters);
    }

    /**
     * Wrapper for parameters used in repository.getInstallments()
     */
    public static class Parameters {
        String paymentMethodId;
        String amount;
        String issuerId;

        public Parameters(String paymentMethodId, String amount, String issuerId) {
            this.paymentMethodId = paymentMethodId;
            this.amount = amount;
            this.issuerId = issuerId;
        }

        public String getPaymentMethodId() {
            return paymentMethodId;
        }

        public String getAmount() {
            return amount;
        }

        public String getIssuerId() {
            return issuerId;
        }
    }
}
