package com.tolmachevroman.paymentapp.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.tolmachevroman.paymentapp.models.installments.Installment;
import com.tolmachevroman.paymentapp.models.installments.PayerCost;
import com.tolmachevroman.paymentapp.utils.Constants;
import com.tolmachevroman.paymentapp.viewmodels.PaymentSharesViewModel;
import com.tolmachevroman.paymentapp.views.adapters.InstallmentsAdapter;
import com.tolmachevroman.paymentapp.views.adapters.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PaymentSharesActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.installmentsRcl)
    RecyclerView installmentsView;

    private List<PayerCost> payerCosts;
    private InstallmentsAdapter installmentsAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_shares);
        ButterKnife.bind(this);

        payerCosts = new ArrayList<>();
        installmentsAdapter = new InstallmentsAdapter(payerCosts, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle args = getIntent().getExtras();
                if(args != null) {
                    args.putString(Constants.INSTALLMENT, payerCosts.get(position).getRecommendedMessage());
                    intent.putExtras(args);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        installmentsView.setLayoutManager(new LinearLayoutManager(this));
        installmentsView.setAdapter(installmentsAdapter);

        PaymentSharesViewModel paymentSharesViewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentSharesViewModel.class);
        paymentSharesViewModel.installments.observe(this, new Observer<Resource<List<Installment>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Installment>> resource) {

                if (resource != null) {
                    switch (resource.status) {

                        case SUCCESS:
                            hideLoading();
                            if (resource.data != null && !resource.data.isEmpty())
                                showData(resource.data.get(0).getPayerCosts());
                            break;
                        case ERROR:
                            hideLoading();
                            if (resource.error != null)
                                showError(resource.error);
                            break;
                        case LOADING:
                            showLoading();
                            break;
                    }
                }

            }
        });

        //reuse previously created ViewModel
        if (paymentSharesViewModel.getParameters() == null) {
            String methodPaymentId = getIntent().getStringExtra(Constants.PAYMENT_METHOD_ID);
            String amount = getIntent().getStringExtra(Constants.AMOUNT);
            String issuerId = getIntent().getStringExtra(Constants.ISSUER_ID);
            paymentSharesViewModel.setParameters(new PaymentSharesViewModel.Parameters(methodPaymentId, amount, issuerId));
        }
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

    private void showData(List<PayerCost> data) {
        payerCosts.clear();
        payerCosts.addAll(data);
        installmentsAdapter.notifyDataSetChanged();
    }
}
