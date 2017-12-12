package com.tolmachevroman.paymentapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tolmachevroman.paymentapp.R;

import dagger.android.AndroidInjection;

public class PaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
    }
}
