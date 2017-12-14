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
import com.tolmachevroman.paymentapp.models.banks.Bank;
import com.tolmachevroman.paymentapp.utils.Constants;
import com.tolmachevroman.paymentapp.viewmodels.BanksViewModel;
import com.tolmachevroman.paymentapp.views.adapters.BanksAdapter;
import com.tolmachevroman.paymentapp.views.adapters.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class BanksActivity extends AppCompatActivity {

    static final int INSTALLMENTS_REQUEST = 102;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.banksRcl)
    RecyclerView banksView;

    private List<Bank> banks;
    private BanksAdapter banksAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == INSTALLMENTS_REQUEST && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtras(data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);
        ButterKnife.bind(this);

        banks = new ArrayList<>();
        banksAdapter = new BanksAdapter(banks, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String bankId = banks.get(position).getId();
                if (bankId != null) {
                    Bundle args = new Bundle(getIntent().getExtras());
                    args.putString(Constants.ISSUER_ID, bankId);

                    Intent intent = new Intent(BanksActivity.this, PaymentSharesActivity.class);
                    intent.putExtras(args);
                    startActivityForResult(intent, INSTALLMENTS_REQUEST);
                }
            }
        });

        banksView.setLayoutManager(new LinearLayoutManager(this));
        banksView.setAdapter(banksAdapter);

        BanksViewModel banksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BanksViewModel.class);
        banksViewModel.banks.observe(this, new Observer<Resource<List<Bank>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Bank>> resource) {

                if (resource != null) {
                    switch (resource.status) {

                        case SUCCESS:
                            hideLoading();
                            if (resource.data != null && !resource.data.isEmpty())
                                showData(resource.data);
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
        if (banksViewModel.getPaymentMethodId() == null) {
            String methodPaymentId = getIntent().getStringExtra(Constants.PAYMENT_METHOD_ID);
            banksViewModel.setPaymentMethodId(methodPaymentId);
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

    private void showData(List<Bank> data) {
        banks.clear();
        banks.addAll(data);
        banksAdapter.notifyDataSetChanged();
    }
}
