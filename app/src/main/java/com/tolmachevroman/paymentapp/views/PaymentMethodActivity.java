package com.tolmachevroman.paymentapp.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tolmachevroman.paymentapp.R;
import com.tolmachevroman.paymentapp.datasources.Error;
import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.models.paymentmethods.PaymentMethod;
import com.tolmachevroman.paymentapp.viewmodels.PaymentMethodViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PaymentMethodActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.paymentMethodsRcl)
    RecyclerView paymentMethodsView;

    private List<PaymentMethod> paymentMethods;
    private PaymentMethodsAdapter paymentMethodsAdapter;

    private PaymentMethodViewModel paymentMethodViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);

        paymentMethods = new ArrayList<>();
        paymentMethodsAdapter = new PaymentMethodsAdapter(paymentMethods, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                System.out.println("Clicked: " + paymentMethods.get(position).getName());
                Bundle args = new Bundle(getIntent().getExtras());
                //TODO start new activity with args
            }
        });

        paymentMethodsView.setLayoutManager(new LinearLayoutManager(this));
        paymentMethodsView.setAdapter(paymentMethodsAdapter);

        paymentMethodViewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentMethodViewModel.class);
        paymentMethodViewModel.getPaymentMethods().observe(this, new Observer<Resource<List<PaymentMethod>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<PaymentMethod>> resource) {

                if(resource != null) {

                    System.out.println("STATUS is: " + resource.status);

                    switch (resource.status) {

                        case SUCCESS:
                            hideLoading();
                            if(resource.data != null && !resource.data.isEmpty())
                                showData(resource.data);
                            break;
                        case ERROR:
                            hideLoading();
                            if(resource.error != null)
                                showError(resource.error);
                            break;
                        case LOADING:
                            showLoading();
                            break;
                    }

                }

            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void showError(Error error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    private void showData(List<PaymentMethod> data) {
        paymentMethods.clear();
        paymentMethods.addAll(data);
        paymentMethodsAdapter.notifyDataSetChanged();
    }
}
