package com.tolmachevroman.paymentapp.dagger.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.tolmachevroman.paymentapp.viewmodels.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
