package com.tolmachevroman.paymentapp.models.installments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tolmachevroman.paymentapp.BuildConfig;
import com.tolmachevroman.paymentapp.datasources.Error;
import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.datasources.WebService;
import com.tolmachevroman.paymentapp.utils.Utils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by romantolmachev on 13/12/2017.
 */

@Singleton
public class InstallmentsRepository {

    private WebService webService;
    private Utils utils;

    @Inject
    public InstallmentsRepository(WebService webService, Utils utils) {
        this.webService = webService;
        this.utils = utils;
    }

    public LiveData<Resource<List<Installment>>> getInstallments(String amount, String paymentMethodId, String issuerId) {

        final MutableLiveData<Resource<List<Installment>>> result = new MutableLiveData<>();

        if (utils.hasConnection()) {
            result.setValue(Resource.<List<Installment>>loading(null));
            Call<List<Installment>> call = webService.getInstallments(BuildConfig.PUBLIC_KEY, amount, paymentMethodId, issuerId);
            call.enqueue(new Callback<List<Installment>>() {
                @Override
                public void onResponse(Call<List<Installment>> call, Response<List<Installment>> response) {

                    if (response.isSuccessful()) {
                        result.setValue(Resource.success(response.body()));
                    } else {
                        result.setValue(Resource.<List<Installment>>error(new Error(response.code(), response.message()), null));
                    }

                }

                @Override
                public void onFailure(Call<List<Installment>> call, Throwable t) {
                    result.setValue(Resource.<List<Installment>>error(null, null));
                }
            });
        } else {
            result.setValue(Resource.<List<Installment>>error(null, null));
        }

        return result;
    }
}
