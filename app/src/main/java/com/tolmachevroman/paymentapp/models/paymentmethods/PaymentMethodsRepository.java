package com.tolmachevroman.paymentapp.models.paymentmethods;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tolmachevroman.paymentapp.BuildConfig;
import com.tolmachevroman.paymentapp.datasources.Error;
import com.tolmachevroman.paymentapp.datasources.Resource;
import com.tolmachevroman.paymentapp.datasources.WebService;
import com.tolmachevroman.paymentapp.utils.Utils;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by romantolmachev on 12/12/2017.
 * <p>
 * Payment process implies internet connection, so this and other repos don't store data in database.
 */

@Singleton
public class PaymentMethodsRepository {

    private WebService webService;
    private Utils utils;

    @Inject
    public PaymentMethodsRepository(WebService webService, Utils utils) {
        this.webService = webService;
        this.utils = utils;
    }

    public LiveData<Resource<List<PaymentMethod>>> getPaymentMethods() {

        final MutableLiveData<Resource<List<PaymentMethod>>> result = new MutableLiveData<>();

        if (utils.hasConnection()) {
            result.setValue(Resource.<List<PaymentMethod>>loading(null));
            Call<List<PaymentMethod>> call = webService.getPaymentMethods(BuildConfig.PUBLIC_KEY);
            call.enqueue(new Callback<List<PaymentMethod>>() {
                @Override
                public void onResponse(Call<List<PaymentMethod>> call, Response<List<PaymentMethod>> response) {

                    if (response.isSuccessful()) {

                        if (response.body() != null) {
                            Iterator<PaymentMethod> iterator = response.body().iterator();
                            while (iterator.hasNext()) {
                                PaymentMethod item = iterator.next();
                                if (item.getPaymentTypeId() == null || !(item.getPaymentTypeId().equals("credit_card"))) {
                                    iterator.remove();
                                }
                            }
                        }

                        result.setValue(Resource.success(response.body()));
                    } else {
                        result.setValue(Resource.<List<PaymentMethod>>error(new Error(response.code(), response.message()), null));
                    }

                }

                @Override
                public void onFailure(Call<List<PaymentMethod>> call, Throwable t) {
                    result.setValue(Resource.<List<PaymentMethod>>error(null, null));
                }
            });
        } else {
            result.setValue(Resource.<List<PaymentMethod>>error(null, null));
        }

        return result;
    }
}
