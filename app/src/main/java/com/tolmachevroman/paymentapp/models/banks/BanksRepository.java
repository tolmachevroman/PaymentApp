package com.tolmachevroman.paymentapp.models.banks;

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
public class BanksRepository {

    private WebService webService;
    private Utils utils;

    @Inject
    public BanksRepository(WebService webService, Utils utils) {
        this.webService = webService;
        this.utils = utils;
    }

    /**
     * Loads Banks from web service. In a more complicated app, it would use generic class like
     * NetworkBoundResource from Addendum in new architecture components
     *
     */
    public LiveData<Resource<List<Bank>>> getBanks(String paymentMethodId) {

        final MutableLiveData<Resource<List<Bank>>> result = new MutableLiveData<>();

        if (utils.hasConnection()) {
            result.setValue(Resource.<List<Bank>>loading(null));
            Call<List<Bank>> call = webService.getBanks(BuildConfig.PUBLIC_KEY, paymentMethodId);
            call.enqueue(new Callback<List<Bank>>() {
                @Override
                public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {

                    if (response.isSuccessful()) {
                        result.setValue(Resource.success(response.body()));
                    } else {
                        result.setValue(Resource.<List<Bank>>error(new Error(response.code(), response.message()), null));
                    }

                }

                @Override
                public void onFailure(Call<List<Bank>> call, Throwable t) {
                    result.setValue(Resource.<List<Bank>>error(new Error(0, t.getMessage()), null));
                }
            });
        } else {
            result.setValue(Resource.<List<Bank>>error(null, null));
        }

        return result;
    }
}
