package com.tolmachevroman.paymentapp.dagger.modules;

import com.tolmachevroman.paymentapp.views.PaymentMethodActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by romantolmachev on 12/12/2017.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract PaymentMethodActivity contributePaymentMethodActivity();
}
