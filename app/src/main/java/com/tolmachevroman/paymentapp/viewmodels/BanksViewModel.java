package com.tolmachevroman.paymentapp.viewmodels;

import android.arch.lifecycle.LiveData;
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

    private LiveData<Resource<List<Bank>>> banks;

    @Inject
    public BanksViewModel(BanksRepository repository) {
        //TODO make as Transformation
        this.banks = repository.getBanks("");
    }

    public LiveData<Resource<List<Bank>>> getBanks() {
        return banks;
    }
}
