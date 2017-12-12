package com.tolmachevroman.paymentapp.dagger.components;

import com.tolmachevroman.paymentapp.dagger.PaymentApp;
import com.tolmachevroman.paymentapp.dagger.modules.AppModule;
import com.tolmachevroman.paymentapp.dagger.modules.BuildersModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class})
public interface AppComponent {
    void inject(PaymentApp app);
}
