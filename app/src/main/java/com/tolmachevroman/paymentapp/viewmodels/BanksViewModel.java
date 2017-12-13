package com.tolmachevroman.paymentapp.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.models.banks.Bank;
import com.tolmachevroman.paymentapp.models.banks.BanksRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by romantolmachev on 13/12/2017.
 */

public class BanksViewModel extends ViewModel {

    private BanksRepository repository;

    private MutableLiveData<String> paymentMethodId = new MutableLiveData<>();

    @Inject
    public BanksViewModel(BanksRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<Bank>>> getBanks() {
        return Transformations.switchMap(paymentMethodId, new Function<String, LiveData<Resource<List<Bank>>>>() {
            @Override
            public LiveData<Resource<List<Bank>>> apply(String input) {
                return repository.getBanks(input);
            }
        });
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId.setValue(paymentMethodId);
    }
}
