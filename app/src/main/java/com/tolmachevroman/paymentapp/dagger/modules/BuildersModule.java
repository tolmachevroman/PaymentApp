package com.tolmachevroman.paymentapp.dagger.modules;

import com.tolmachevroman.paymentapp.views.activities.BanksActivity;
import com.tolmachevroman.paymentapp.views.activities.PaymentMethodActivity;
import com.tolmachevroman.paymentapp.views.activities.PaymentSharesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by romantolmachev on 12/12/2017.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract PaymentMethodActivity contributePaymentMethodActivity();

    @ContributesAndroidInjector
    abstract BanksActivity contributeBanksActivity();

    @ContributesAndroidInjector
    abstract PaymentSharesActivity contributePaymentSharesActivity();
}
