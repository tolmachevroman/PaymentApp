package com.tolmachevroman.paymentapp.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.tolmachevroman.paymentapp.dagger.ViewModelKey;
import com.tolmachevroman.paymentapp.viewmodels.PaymentMethodViewModel;
import com.tolmachevroman.paymentapp.viewmodels.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaymentMethodViewModel.class)
    abstract ViewModel bindPaymentMethodViewModel(PaymentMethodViewModel paymentMethodViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
