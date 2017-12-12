package com.tolmachevroman.paymentapp.dagger;

import android.app.Activity;
import android.app.Application;

import com.tolmachevroman.paymentapp.dagger.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by romantolmachev on 12/12/2017.
 */

public class PaymentApp extends Application implements HasActivityInjector {

    private DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
