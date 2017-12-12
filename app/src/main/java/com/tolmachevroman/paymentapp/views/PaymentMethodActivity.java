package com.tolmachevroman.paymentapp.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tolmachevroman.paymentapp.R;
import com.tolmachevroman.paymentapp.viewmodels.PaymentMethodViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class PaymentMethodActivity extends AppCompatActivity {

    private PaymentMethodViewModel paymentMethodViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        paymentMethodViewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentMethodViewModel.class);
    }
}
